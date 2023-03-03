package assistant.front.learningjavaassistantfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onRegisterClick() {
        welcomeText.setText("Register");
    }

    @FXML
    protected void onLoginClick() {
        welcomeText.setText("Register");
    }
}