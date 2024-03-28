package uwf.LoginInception;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class UserAuthentication {

	String username = "";
	String password = "";
	HashGenerator hg;
	ReadWriteFile readFile;
	HashMap<String, String> tempMap;
	
	public UserAuthentication(ReadWriteFile rwf) {
		hg = new HashGenerator();
		readFile = rwf;
		tempMap = readFile.getUserAndPass(); 
	}
	
	
	public boolean authenticateUser(String user, String pass) {
		
		username = "4078" + user;
		
		try {
			username = hg.generateSHAHash(username);
			password = hg.generateSHAHash(pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return((tempMap.containsKey(username)) && (tempMap.get(username).equals(password)));
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
