import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util. *;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class crack {
	public static String key;
	
	
	//Runs through every combination of seven character words capitalizing 
	//the first word and adding one digit to the end.
	public static String rule1 (String dict) {
		String hash;
		
		try {
			Scanner scnrr = new Scanner (new File(dict));
			while (scnrr.hasNext()){
				String text = scnrr.next();
				if (text.length() == 7) {
					text= text.substring(0,1).toUpperCase() + text.substring(1);
					for (int i = 0; i < 10; i++) {
						String text1 = text + i;
						hash = getSha(text1);
						if (hash.equals(key)) {
							scnrr.close();
							return text1;
						}
				
						}
				}
			}
			scnrr.close();
		}
		catch(IOException e) {
			System.out.println("Word List not found: " + e);
			return null;
		}
		return null;
		
	}//end rule1
	
	//Cracks a password with a four digit number
	//along with one of the following
	//characters at the beginning: *, ~, !, #
	public static String rule2() {
		String test;
		String text2 = "";
		String hash;
		
		for (int i=0; i < 10; i++) {
			for (int j =0; j<10; j++) {
				for (int k=0; k<10; k++) {
					for (int l=0; l<10; l++) {
						text2 = "" + i + j + k + l;
						
						//Test with symbols in front
						test = '*' + text2;
						hash = getSha(test);
						if (hash.equals(key)) {
							return test;
						}
						test = '!' + text2;
						hash = getSha(test);
						if (hash.equals(key)) {
							return test;
						}
						test = '~' + text2;
						hash = getSha(test);
						if (hash.equals(key)) {
							return test;
						}
						test = '#' + text2;
						hash = getSha(test);
						if (hash.equals(key)) {
							return test;
						}
						
					}
				}
			}
		}
		return null;
	}//end rule 2
	
	/* takes a 5 character password from dictionary
	 * and replaces a with @ and l with numeral 1
	 */
	public static String rule3(String dict) {
		String hash;
		
		try {
			Scanner scan3 = new Scanner(new File(dict));
			
			while (scan3.hasNext()) {
				String text3 = scan3.next();
				if (text3.length() == 5) {
					text3 = text3.replace('a', '@');
					text3 = text3.replace('l', '1');
					hash = getSha(text3);
					if (hash.equals(key)) {
						scan3.close();
						return text3;
					}
				}
			}
			scan3.close();
		}
		catch (IOException e) {
			System.out.println("File not found: " + e);
			return null;
		}
		return null;
	}//end rule3
	
	/* Tries every combination of 
	 * a 6 digit password
	 */
	public static String rule4() {
		String text4 = "";
		String hash;
		
		for (int i=0; i< 10; i++) {
			for (int j=0; j<10; j++) {
				for (int k=0; k<10; k++) {
					for (int l=0; l<10; l++) {
						for (int m=0; m<10; m++) {
							for (int n=0; n<10; n++) {
								text4 = "" + i + j + k + l + m + n;
								hash = getSha(text4);
								if (hash.equals(key)) {
									return text4;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}//end rule4
	
	//tries every word in given dictionary
	public static String rule5(String dict) {
		String hash;
		
		try {
			Scanner scan5 = new Scanner(new File(dict));
			
			while (scan5.hasNext()) {
				String text5 = scan5.next();
				hash = getSha(text5);
				if (hash.equals(key)) {
					scan5.close();
					return text5;
				}
			}
			scan5.close();
		}
		catch(IOException e) {
			System.out.print("File not found" + e);
			return null;
		}
		return null;
	}
	
	//Referenced from GeeksforGeeks
	//Hash the given text
	public static String getSha(String pass) {
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			byte [] messageDigest = md.digest(pass.getBytes());
			
			BigInteger convert = new BigInteger(1, messageDigest);
			
			String hashText = convert.toString(16);
			
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			
			return hashText;
		}
		
		catch (NoSuchAlgorithmException e) {
			System.out.println ("Incorrect Algorithm: " + e);
			
			return null;
		}
		
		
	}
	
	public static void main (String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);
		FileWriter writer = new FileWriter("cracked_passwds.txt");
		PrintWriter pw = new PrintWriter(writer);
		String passwd;
		String input;
		String dict;
		String user;
		
		System.out.println("Enter the file containing hashed passwords : ");
		input = scnr.next();
		System.out.println("Enter the dictionary you would like to use: ");
		dict =scnr.next();
		
		/*Read in hashed passwords one at a time
		 * Once Cracked it outputs password to terminal
		 * and writes it to cracked_passwds.txt
		 */
		Scanner keyScan = new Scanner(new File(input));
		while (keyScan.hasNextLine()) {
			keyScan.useDelimiter(":");
			String line = keyScan.next();
			line = line.trim();
			if (line.length() == 64) {
				key = line;
			
				
			
			passwd = rule1(dict);
			if (passwd != null) {
				System.out.println("Password: " + passwd);
				pw.print(key + "-> " + passwd + "\n");
				
			}
			passwd = rule2();
			if (passwd != null) {
				System.out.println("Password: " + passwd);
				pw.print(key + "-> " + passwd + "\n");
				
			}
			passwd = rule3(dict);
			if (passwd != null) {
				System.out.println("Password: " + passwd);
				pw.print(key + "-> " + passwd + "\n");
				
			}
			passwd = rule4();
			if (passwd != null) {
				System.out.println("Password: " + passwd);
				pw.print(key + "-> " + passwd + "\n");
				
			}
			passwd = rule5(dict);
			if (passwd != null) {
				System.out.println("Password: " + passwd);
				pw.print(key + "-> " + passwd + "\n");
				
			}
			
		}
		}
		
		
		
		keyScan.close();
		pw.close();
		scnr.close();
	
		
	}
}
