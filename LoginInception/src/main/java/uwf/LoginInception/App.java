package uwf.LoginInception;



import java.io.IOException;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.control.TextField; 
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.event.EventHandler;



/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    	
    	
    	
    	ReadWriteFile rwf = new ReadWriteFile();
		UserAuthentication userAuth = new UserAuthentication(rwf);

    	
        Label usernameLabel = new Label("Username: ");
        TextField UserTextField = new TextField();       
        
        Label passwordLabel = new Label("Password: ");
        PasswordField passTextField = new PasswordField();

        Button  loginButton = new Button ("Login");
        
        GridPane gridPane = new GridPane();      
        
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER); 
        

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(UserTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passTextField, 1, 1);  
        gridPane.add(loginButton, 1, 2); 
        
        Scene scene = new Scene(new StackPane(gridPane), 640, 480);
        stage.setScene(scene);
        stage.setTitle("NGEN Login");
        stage.show();
        
        UserTextField.setOnAction(event -> {
        	loginButton.fire();
        });
        
        passTextField.setOnAction(event -> {
        	loginButton.fire();
        });
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Action to perform when the button is clicked
                System.out.println("Login button clicked!");
                String user = UserTextField.getText();                
                String pass = passTextField.getText();   
                
                System.out.println("REMOVE: OLD Username: " + user);
                System.out.println("REMOVE: OLD Password: " + pass);
                
                
                
                
                if(!(user.equals("")) || !(pass.equals(""))) {
	                if(userAuth.authenticateUser(user, pass)) {
	
	                    System.out.println("The login was successful!");
	                    
	                    
	                    
	                    System.out.println("REMOVE: NEW Username: " + userAuth.getUsername());
	                    System.out.println("REMOVE: NEW Password: " + userAuth.getPassword());
	                
	                    
	                    LoginWelcome lw = new LoginWelcome(stage, rwf, user);

	                    
	                    System.out.println("Back here");
	                    
	                
	                }else {
	
	                    System.out.println("The login was NOT successful!");
	                }
                }else {
                	System.out.println("You have entered nothing. Please enter a valid username and password.");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}