/**
 * Author: Sarais Bone
 * File Name: ReadWriteFile.java
 * Assignment: Login Inception
 * Created: March 27, 2024
 *
 * Description: This class reads and writes to the user database file.
 *              When the file is reading the .txt file, then saves each username and and password
 *              as a hashmap (key-value pair). This prevents duplicate usernames, thus allowing for UUID.
 *              When the file is writing to the .txt file, it prints out the whole database, which includes
 *              the user's new reset password.
 */

package uwf.LoginInceptionMod;


import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadWriteFile{
	private FileInputStream fileByteStream;
    private Scanner inFS;
    //private HashMap<String, String> userAndPass;
    private String extractedText ;
    private String[] userAndPass;
    String fileName = "SSDDatabase.txt";
    
    public ReadWriteFile() throws IOException {
    	fileByteStream = null;
	    inFS = null;
	    
	    try {
			// Attempts to open the file
		    fileByteStream = new FileInputStream(fileName);
		  
		    inFS = new Scanner(fileByteStream);
			inFS.useDelimiter(",|\n");
			readingFile();
			fileByteStream.close();
		}
		catch(IOException e) {
			System.out.println("Failed to open file");
			System.exit(0);
		
        }
    }
    
    public void readingFile(){
    	StringBuilder sb = new StringBuilder();
        while (inFS.hasNextLine()) {
            sb.append(inFS.nextLine()).append("\n");
        }
        extractedText = sb.toString().trim();
        String[] lines = extractedText.split("[\\n,]");
        
        userAndPass = lines;
    }

    public String[] getUserAndPass() {
		return userAndPass;
	}
	
  //replaces the password in the hashmap
	public void replacePassword(String user, String pass){
		for (int i = 0; i < userAndPass.length; i++) {
			if((userAndPass[i] != null) && ((userAndPass[i]).equals(user)) && (i % 2 == 0)) {
				userAndPass[i + 1] = pass;
                try {
        			writeToFile();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
				return;
			}
		}
        System.out.println("This user was not able to be found.");
	}

	public void writeToFile() throws IOException {
			FileWriter fileWriter = new FileWriter(fileName);
			PrintWriter printWriter = new PrintWriter(fileWriter);
		try {
			if(((userAndPass.length) % 2 == 0) && (userAndPass != null)) {


		        //loops through the pairs of the usernames and passwords
				for(int i = 0; i < userAndPass.length ; i += 2) {
					if(i == userAndPass.length - 2) {// Check if this is the last entry
						printWriter.print(userAndPass[i] + "," + userAndPass[i + 1]);

					}else {//Adds a new line to all the pairs that are not the last pair
						printWriter.println(userAndPass[i] + "," + userAndPass[i + 1]);
					}
				}
			}else {
		        System.out.println("The array is either null or has an odd length.");
			}
	        printWriter.flush();
		} finally {
            // Close the PrintWriter and FileWriter
            if (printWriter != null) {
                printWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
	    
	}
    
    
}
