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

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception{
KeyPair keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.ENCRYPT_MODE, keys.getPublic());
byte[] encrypted = cipher.doFinal("Hello, World!".getBytes());
System.out.println(new String(encrypted));
cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
	byte[] plain = cipher.doFinal(encrypted);
	System.out.println(new String(plain));
	System.out.println(keys.getPublic());
	}


}
