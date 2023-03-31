package assistant.front.learningjavaassistantfrontend;

import javafx.application.Application;

import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showRegisterWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}