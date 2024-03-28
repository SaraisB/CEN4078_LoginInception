package uwf.LoginInception;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

	
	public String generateSHAHash(final String str)throws NoSuchAlgorithmException{
		String strToBeHashed = null;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		byte[] messageDigest = md.digest(str.getBytes(StandardCharsets.UTF_8));

		strToBeHashed = convertToHex(messageDigest);
	    return strToBeHashed;
	}
	
	private String convertToHex(final byte[] messageDigest) {
	      BigInteger bigint = new BigInteger(1, messageDigest);
	      String hexText = bigint.toString(16);
	      while (hexText.length() < 32) {
	         hexText = "0".concat(hexText);
	      }
	      return hexText;
	   }
	
	
	
}
