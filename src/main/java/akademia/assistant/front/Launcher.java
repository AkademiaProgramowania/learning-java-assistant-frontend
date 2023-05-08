package akademia.assistant.front;

import akademia.assistant.front.view.ViewFactory;
import javafx.application.Application;

import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showRegisterWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}