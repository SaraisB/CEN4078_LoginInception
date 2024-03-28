package uwf.LoginInception;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadWriteFile{
	private FileInputStream fileByteStream;
    private Scanner inFS;
    private HashMap<String, String> userAndPass;
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
    	String user = "";
    	String pass = "";
    	userAndPass = new HashMap<String, String>();
    	while (inFS.hasNextLine()) {
    		user =  inFS.next().trim();
    		pass = inFS.next().trim();
            userAndPass.put(user, pass);
        }
    }

	public HashMap<String, String> getUserAndPass() {
		return userAndPass;
	}
	
	public void replacePassword(String user, String pass){
		userAndPass.put(user, pass);
		System.out.println(userAndPass);
		try {
			writeToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile() throws IOException {
			FileWriter fileWriter = new FileWriter(fileName);
			PrintWriter printWriter = new PrintWriter(fileWriter);
		try {
			Iterator<HashMap.Entry<String, String>> iterator = userAndPass.entrySet().iterator();

	        // Iterate through the entries
	        while (iterator.hasNext()) {
	        	HashMap.Entry<String, String> pair = iterator.next();

	            // Check if this is the last entry
	            if (!iterator.hasNext()) {
	                // Perform action on the last entry
			    	printWriter.print(pair.getKey() + "," + pair.getValue());
	            } else {
	                // Perform action on all other entries
			    	printWriter.println(pair.getKey() + "," + pair.getValue());
	            }
			
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
