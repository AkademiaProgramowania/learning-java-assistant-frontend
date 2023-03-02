package assistant.front.learningjavaassistantfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TestWindowController {
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
    void onLoginClick(ActionEvent event) {
        boolean doesUserExist = service.doesUserExist(usernameField.getText(), passwordField.getText());
        if (doesUserExist) {
            User user = new User(usernameField.getText(), passwordField.getText());
            service.setCurrentUser(user);
            System.out.println("Witaj ponownie, " + usernameField.getText() + ".");
        } else {
            System.out.println("Użytkownik nie istnieje. Spróbuj jeszcze raz.");
        }
    }

    @FXML
    void onRegisterClick(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            System.out.println("Niepoprawna nazwa użytkownika lub hasło.");
        } else if (service.doesNicknameExist(usernameField.getText())) {
            System.out.println("Użytkownik o tym nicku już istnieje.");
        } else {
            User newUser = new User(usernameField.getText(), passwordField.getText());
            service.addUserToUserList(newUser);
            service.setCurrentUser(newUser);
            System.out.println("Witaj, " + newUser.getUsername() + ".");
        }
    }

}
