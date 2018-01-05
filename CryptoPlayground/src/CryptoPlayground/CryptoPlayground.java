/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CryptoPlayground;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author rohan
 */
public class CryptoPlayground {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String toHash = "Hello, World!";
		MessageDigest hash = java.security.MessageDigest.getInstance("SHA-512");
 byte[] stuff = hash.digest(toHash.getBytes(StandardCharsets.UTF_8));
System.out.println(Arrays.toString(stuff));
	for(byte b:stuff){
	int a = (b&15<<4)>>4;
	int c = (b&15);
String m = "0123456789abcdef";	
	System.out.print(m.charAt(a));
System.out.print(m.charAt(c));
	}
		
		
	}
	
}
