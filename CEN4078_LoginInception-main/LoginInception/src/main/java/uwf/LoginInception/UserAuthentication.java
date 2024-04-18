package uwf.LoginInception;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class UserAuthentication {

	String username = "";
	String password = "";
	HashGenerator hg;
	ReadWriteFile readFile;
	private String[] tempMap;
	
	public UserAuthentication(ReadWriteFile rwf) {
		hg = new HashGenerator();
		readFile = rwf;
		tempMap = readFile.getUserAndPass(); 
	}
	
	
	public boolean authenticateUser(String user, String pass) {
		//added the salt to the user inputed username
				username = hg.addSalt(user);

				//Hashes the password and the new username
				try {
					username = hg.generateSHAHash(username);
					password = hg.generateSHAHash(pass);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				

				if(((tempMap.length) % 2 == 0) && (tempMap != null)) {

			        // Loops through the pairs of the usernames and passwords
					// Compares the results with what is in the database
					for(int i = 0; i < tempMap.length ; i += 2) {
						
						if((i % 2 == 0) && (tempMap[i].equals(username)) && (tempMap[i + 1].equals(password))) {
							return true;
						}
					}
				}else {
			        System.out.println("The array is either null or has an odd length.");
				}
				return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HashGenerator getHg() {
		return hg;
	}

	public void setHg(HashGenerator hg) {
		this.hg = hg;
	}
	
	
	
}
