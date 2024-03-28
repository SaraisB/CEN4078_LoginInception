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
	Label welcomeLabel;
	Scene scene;
	GridPane gridPane;
	Button  resetPasswordButton;
	
	public LoginWelcome(Stage stage, ReadWriteFile rwf, String username) {
		
		welcomeLabel = new Label("Welcome " + username + "!");
		
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
