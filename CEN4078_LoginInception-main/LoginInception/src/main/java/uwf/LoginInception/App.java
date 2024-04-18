/**
 * Author: Sarais Bone
 * File Name: App.java
 * Assignment: Login Inception
 * Created: March 27, 2024
 *
 * Description: This class provides the the initial login page of the user.
 * 				The user is prompted to enter their username and password.
 * 				If the user and password that they entered matches one that is
 * 				in the user database, the login will be successful. If it is not
 * 				successful, then the user will not be allowed to login. and can try again.
 */

package uwf.LoginInception;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class App extends Application {
	private ReadWriteFile rwf ;
	private UserAuthentication userAuth;
	private Label usernameLabel;
	private Label loginLabel;
	private TextField UserTextField;
	private Label passwordLabel;
	private PasswordField passTextField;
	private Button  loginButton;
	private GridPane gridPane;
	private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

    	initializeDependencies();
    	setupUI(stage);

    }
    
    

    public void initializeDependencies() throws IOException {
        rwf = new ReadWriteFile();
        userAuth = new UserAuthentication(rwf);
    }

    public void setupUI(Stage stage) {
    	loginLabel = new Label("Welcome to the NGEN system.\nPlease Login. ");

        usernameLabel = new Label("Username: ");
        UserTextField = new TextField();

        passwordLabel = new Label("Password: ");
        passTextField = new PasswordField();

        loginButton = new Button ("Login");

        gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(loginLabel, 2, 0);
        gridPane.add(usernameLabel, 1, 1);
        gridPane.add(UserTextField,2, 1);
        gridPane.add(passwordLabel, 1, 2);
        gridPane.add(passTextField, 2, 2);
        gridPane.add(loginButton, 2, 3);

        scene = new Scene(new StackPane(gridPane), 640, 480);
        stage.setScene(scene);
        stage.setTitle("NGEN Login");
        stage.show();



        //Allows the user to press Enter within the text field box
        UserTextField.setOnAction(event -> {
        	loginButton.fire();
        });
        passTextField.setOnAction(event -> {
        	loginButton.fire();
        });


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Gets the username and password
                String user = UserTextField.getText();
                String pass = passTextField.getText();
                if(!(user.equals("")) || !(pass.equals(""))) {

                	//check if it is in the user database
	                if(userAuth.authenticateUser(user, pass)) {

	                    System.out.println("The login was successful!");


	                    LoginWelcome lw = new LoginWelcome(stage, rwf, user);

	                }else {
	                	loginLabel.setText("Welcome to the NGEN system. Please Login."
	                			+ "\nThe login was NOT successful!"
	                			+ "\nPlease try again.");
	                    System.out.println("The login was NOT successful!");
	                }
                }else {
                	loginLabel.setText("You have entered nothing.\nPlease enter a valid username and password.");
                	System.out.println("You have entered nothing. Please enter a valid username and password.");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}