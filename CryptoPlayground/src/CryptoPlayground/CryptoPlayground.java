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

	public static String hash(String s) {
		try {
			String r = "";
			String toHash = s;
			MessageDigest hash = java.security.MessageDigest.getInstance("SHA-512");
			byte[] stuff = hash.digest(toHash.getBytes(StandardCharsets.UTF_8));
			for (byte b : stuff) {
				int a = (b & 15 << 4) >> 4;
				int c = (b & 15);
				String m = "0123456789abcdef";
				r += (m.charAt(a));
				r += (m.charAt(c));
			}
			return r;
		} catch (Exception ex) {
			System.err.println("The hash function called does not exist");
			System.exit(1);
			return "THIS IS NOT A HASH OF A STRING! The algorithm broke.";
		}
	}
	public static KeyPair keys=null;
	public static Cipher cipher=null;
	
	static{
		try {
			keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();
			cipher = Cipher.getInstance("RSA");
		} catch (Exception ex) {
			Logger.getLogger(CryptoPlayground.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static byte[] encrypt(byte[] b){
try{
		cipher.init(Cipher.ENCRYPT_MODE, keys.getPublic());
byte[] encrypted = cipher.doFinal(b);
return encrypted;
}catch(Exception ex){
	System.err.println(ex);
	System.exit(1);
	return null;
}
}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception{
		byte[] b;
		System.out.println(new String(b = encrypt("TEST!!".getBytes())));
		System.out.println(new String(decrypt(b)));
	}
	
public static byte[] decrypt(byte[] b){
	try{
		cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
	byte[] plain = cipher.doFinal(b);
	return plain;
	}catch(Exception ex){
		System.err.println(ex);
		System.exit(1);
		return null;
	}
}

}
