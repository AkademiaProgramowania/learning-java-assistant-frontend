package assistant.front.learningjavaassistantfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterWindowController {
    private Service service = new Service();

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label textLabel;

    @FXML
    private TextField usernameField;

    @FXML
    void onLoginClick() {
        try {
            service.login(usernameField.getText(), passwordField.getText());
            System.out.println("Zalogowano pomyślnie, ciąg dalszy scenariusza.");
        } catch (AuthenticationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    void onRegisterClick() {
        try {
            service.register(usernameField.getText(), passwordField.getText());
            System.out.println("Zarejestrowano pomyślnie, ciąg dalszy scenariusza.");
        } catch (AuthenticationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
