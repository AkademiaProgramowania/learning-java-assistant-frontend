package akademia.assistant.front.controller;

import akademia.assistant.front.exception.AuthenticationException;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterWindowController extends Controller {

    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label textLabel;
    @FXML
    private TextField usernameField;
    private String FXMLName = "register-window.fxml";

    public RegisterWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
    }

    @FXML
    void onLoginClick() {
        try {
            service.login(usernameField.getText(), passwordField.getText());
            closeWindow();
            viewFactory.showMainWindow();
        } catch (AuthenticationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    void onRegisterClick() {
        try {
            service.register(usernameField.getText(), passwordField.getText());
            closeWindow();
            viewFactory.showMainWindow();
        } catch (AuthenticationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public String getFXMLName() {
        return FXMLName;
    }
}
