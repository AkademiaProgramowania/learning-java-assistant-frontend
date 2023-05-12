package akademia.assistant.front.view;

import akademia.assistant.front.controller.MainWindowController;
import akademia.assistant.front.controller.ProblemWindowController;
import akademia.assistant.front.controller.RegisterWindowController;
import akademia.assistant.front.controller.Controller;
import akademia.assistant.front.model.User;
import akademia.assistant.front.service.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewFactory {

    private Service service = new Service();
    private Map<Controller, Stage> stages = new HashMap<>();

    public void showRegisterWindow() {
        Controller controller = new RegisterWindowController(service, this);
        showWindow(controller);
    }

    public void showMainWindow() {
        Controller controller = new MainWindowController(service, this);
        showWindow(controller);
    }

    public void showProblemWindow() {
        Controller controller = new ProblemWindowController(service, this);
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
        stages.put(controller, stage);
    }

    public void closeWindow(Controller controller) {
        Stage stage = stages.remove(controller);
        stage.close();
    }
}
