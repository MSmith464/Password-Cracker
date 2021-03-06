import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

// Java program to calculate MD5 hash value 
public class crackMD5 { 
	public static String getMd5(String input) 
	{ 
		try { 

			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD2"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	} 

	// Driver code 
	public static void main(String args[]) throws NoSuchAlgorithmException 
	{ 
		String hash = "f4245e8c9ea674871c34bf04494db211";
		String s = "SKY-PTNM-";
		String t;
		for(int i = 0; i< 10000; i++) {
			t = s + i;
			if (hash.equals(getMd5(t))) {
				System.out.println(t);
			}
		}
		System.out.print(getMd5("h0lyw33k"));
		//System.out.println("Your HashCode Generated by MD5 is: " + getMd5(s)); 
	} 
} 
