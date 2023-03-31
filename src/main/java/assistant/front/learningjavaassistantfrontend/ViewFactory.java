package assistant.front.learningjavaassistantfrontend;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public void showRegisterWindow() {
        Controller controller = new RegisterWindowController(this);
        showWindow(controller);
    }

    public void showMainWindow() {
        Controller controller = new MainWindowController(this);
        showWindow(controller);
    }

    private void showWindow(Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFXMLName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
