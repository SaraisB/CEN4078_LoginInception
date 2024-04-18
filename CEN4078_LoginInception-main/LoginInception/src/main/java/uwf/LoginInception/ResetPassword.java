package uwf.LoginInception;

import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ResetPassword {
	Label resetLabel;
	Scene scene;
	GridPane gridPane;
	Button  confirmButton;
    PasswordField passTextField;
    ReadWriteFile rwf;
    HashGenerator hg;
	
	
	public ResetPassword(Stage stage, ReadWriteFile rwf, String username) {
		
		resetLabel = new Label( "Please reset your password.");
		
        confirmButton = new Button ("Confirm");

        passTextField = new PasswordField();
        
        hg = new HashGenerator();
        
        this.rwf = rwf;
        
		
		gridPane = new GridPane(); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(resetLabel, 0, 0);
        gridPane.add(passTextField, 0, 1);
        gridPane.add(confirmButton, 0, 2);
        
        scene = new Scene(new StackPane(gridPane), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Reset Password" );
        
        
        passTextField.setOnAction(event -> {
        	confirmButton.fire();
        });
        
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String newPassword = passTextField.getText(); 
            	if(!(newPassword.equals(""))) {
	            	passwordChanged(stage);
	                System.out.println("Confirm button clicked!"); 
	                try {
						rwf.replacePassword(hg.generateSHAHash("4078" + username), hg.generateSHAHash(newPassword));
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
            	}else {
            		System.out.println("You have entered nothing. Please enter a valid username and password.");
            	}
            }
        });
        
	}

	public Scene getScene() {
		return scene;
	}
	
	public void passwordChanged(Stage stage) {
		Label resetCompleteLabel = new Label( "Your password was reset. Please close the program to login again.");
        scene = new Scene(new StackPane(resetCompleteLabel), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Password Reset Complete" );
	}
	
}
