/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CryptoPlayground;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author rohan
 */
public class CryptoPlayground {
	public static final String HEXABET = "0123456789abcdef";
public static String toHexString(byte[] bytes){
	String r = "";
for (byte b : bytes) {
				int a = (b & 15 << 4) >> 4;
				int c = (b & 15);
				r += (HEXABET.charAt(a));
				r += (HEXABET.charAt(c));
			}
return r;
}
	public static String hash(String s) {
		try {
			String toHash = s;
			MessageDigest hash = java.security.MessageDigest.getInstance("SHA-384");
			byte[] b = hash.digest(toHash.getBytes(StandardCharsets.UTF_8));
   String r = toHexString(b);
			return r;
		} catch (Exception ex) {
			System.err.println("The hash function called does not exist");
			System.exit(1);
			return "THIS IS NOT A HASH OF A STRING! The algorithm broke.";
		}
	}
	public static KeyPair keys = null;
	public static Cipher cipher = null;

	static {
		try {
			keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();
			cipher = Cipher.getInstance("RSA");
		} catch (Exception ex) {
			Logger.getLogger(CryptoPlayground.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static byte[] encrypt(byte[] b) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keys.getPublic());
			byte[] encrypted = cipher.doFinal(b);
			return encrypted;
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
			return null;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {

String		s = hash("Hello, World!");
System.out.println(s);
//System.out.println(toHexString(unSign(getSignature("Hello, World!").)));
System.out.println(toHexString(unSign(fromHexString(getSignature("Hello, World!")))));
	}

	public static String getSignature(String s){
		String hash = hash(s);
		return toHexString(sign(fromHexString(hash)));
	}
	
	
	public static byte[] decrypt(byte[] b) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
			byte[] plain = cipher.doFinal(b);
			return plain;
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
			return null;
		}
	}
	
	public static byte[] sign(byte[] b) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keys.getPrivate());
			byte[] plain = cipher.doFinal(b);
			return plain;
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
			return null;
		}
	}
		public static byte[] unSign(byte[] b) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, keys.getPublic());
			byte[] plain = cipher.doFinal(b);
			return plain;
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
			return null;
		}
	}
	public static boolean verifySignature(String message, String sig){
		String hash = hash(message);
		String s = toHexString(unSign(fromHexString(sig)));
		return s.equals(hash);
	}
	public static byte[] fromHexString(String hex){
		byte[] bytes = new byte[hex.length()/2];
		for(int i = 0;i<hex.length();i+=2){
			byte b = (byte) (HEXABET.indexOf(hex.charAt(i))*16+HEXABET.indexOf(hex.charAt(i+1)));
			bytes[i/2] = b;			
		}
		return bytes;
		}
	}


