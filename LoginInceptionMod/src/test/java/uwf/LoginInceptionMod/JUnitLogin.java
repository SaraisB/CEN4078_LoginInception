package uwf.LoginInceptionMod;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class JUnitLogin {


	@Test
	void SR1_HashPassword_SHA256() throws NoSuchAlgorithmException {
		// SR1: The software shall hash passwords using SHA256.

		/* The program uses a class called HashGenerator that hashes all the
		 * string variables that it receives
		 * Using this site --> https://emn178.github.io/online-tools/sha256.html
		 * you can verify if the input has been hashed properly
		 */

		HashGenerator hashGen = new HashGenerator();

		//You can change the userInput and expectedHash to whatever string you want to check
		String userInput = "testingTheSHA256";
		String expectedHash = "a9fa7e9c951df08a0168d5667c8f1016a5658581b6b640336bc779424eacc87e";

		String testInput = hashGen.generateSHAHash(userInput);

		//assertEquals(expected, actual);
		assertEquals(expectedHash, testInput, "Generated SHA-256 hash should match expected value");
	}
	
	@Test
	void SR2_HashPassword_SHA256_W_4078() throws NoSuchAlgorithmException {
		// SR2: The software shall create a UUID which is a SHA256 hash of the username salted with the text “4078”
		// 

		/* The program uses a class called HashGenerator that hashes all the
		 * string variables that it receives
		 * Using this site --> https://emn178.github.io/online-tools/sha256.html
		 * you can verify if the input has been hashed properly
		 * add 4078 to any phrase that you use
		 */

		HashGenerator hashGen = new HashGenerator();

		//You can change the userInput and expectedHash to whatever string you want to check
		String userInput = "testingTheSHA256";
		String expectedHash = "accb23d95dea1e3ca24482dea6a2f5a94feee54183f8b4271df3af104d9b7502";

		//adding the Salt
		userInput = hashGen.addSalt(userInput);

		String testInput = hashGen.generateSHAHash(userInput);

		//assertEquals(expected, actual);
		assertEquals(expectedHash, testInput, "Generated SHA-256 hash should match expected value");
	}
	
	@Test
	void SR3_User_and_Pass_Storage() throws NoSuchAlgorithmException, IOException {
		// SR3: The hashed username and password combinations shall be stored in an array.


		/* The program uses a class called ReadWriteFile that reads the "SSDDatabse.txt" file
		 * and puts the data in an array (String[]).
		 */

		ReadWriteFile readWriteFile = new ReadWriteFile();
		
		String[] arrayUserPassTemp = readWriteFile.getUserAndPass();
		
		String typeOfVariable = arrayUserPassTemp.getClass().getSimpleName();
		
		
		//Checking to see if the stored array has an even number of element
		//Even pairs mean there is at least a Username and a Password
		int lengthOfArray = arrayUserPassTemp.length;
		boolean isEven = (lengthOfArray % 2 == 0);
		
		//assertEquals(expected, actual);
		//Checks data type
		assertEquals(typeOfVariable, "String[]", "Generated variable type should match expected value");
		
		assertTrue(isEven);
	}
	
	@Test
	void SR4_Test_Secure_Login() throws NoSuchAlgorithmException, IOException {
		// SR3: The hashed username and password combinations shall be stored in an array.


		/* The program will call the App class and automate selections for the test
		 */
		
		App app = new App();
		app.initializeDependencies();

		
	}
}
