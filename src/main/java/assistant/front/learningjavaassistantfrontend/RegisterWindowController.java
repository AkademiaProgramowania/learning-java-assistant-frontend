package assistant.front.learningjavaassistantfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterWindowController implements Controller {
    private Service service = new Service();
    private ViewFactory viewFactory;

    RegisterWindowController(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label textLabel;

    @FXML
    private TextField usernameField;

    private String FXMLName = "register-window.fxml";

    @FXML
    void onLoginClick() {
        try {
            service.login(usernameField.getText(), passwordField.getText());
            ((Stage) errorLabel.getScene().getWindow()).close();
            viewFactory.showMainWindow();
        } catch (AuthenticationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    void onRegisterClick() {
        try {
            service.register(usernameField.getText(), passwordField.getText());
            ((Stage) errorLabel.getScene().getWindow()).close();
            viewFactory.showMainWindow();
        } catch (AuthenticationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public String getFXMLName() {
        return FXMLName;
    }
}
