/**
 * Author: Sarais Bone
 * File Name: LoginWelcome.java
 * Assignment: Login Inception
 * Created: March 27, 2024
 *
 * Description: This class provides the login welcome interface for the user.
 *              The page only presents itself when the suer has successfully
 *              logged in to the system.
 */

package uwf.LoginInception;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginWelcome {
	private Label welcomeLabel;
	private Scene scene;
	private GridPane gridPane;
	private Button  resetPasswordButton;
	
	public LoginWelcome(Stage stage, ReadWriteFile rwf, String username) {

		welcomeLabel = new Label("Login Successful!\n\nWelcome " + username + "!\n\nWould you like to reset your password?");

        resetPasswordButton = new Button ("Password Reset");

		gridPane = new GridPane(); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(welcomeLabel, 0, 0);
        gridPane.add(resetPasswordButton, 0, 1);
        
        scene = new Scene(new StackPane(gridPane), 640, 480);
        
        stage.setScene(scene);
        stage.setTitle("Welcome " + username);

        //Button actions if the user want to rest their password
        resetPasswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Reset button clicked!"); 
                ResetPassword rp = new ResetPassword(stage, rwf, username);
                
            }
        });
	}

	public Scene getScene() {
		return scene;
	}
	
	
}
