package assistant.front.learningjavaassistantfrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static assistant.front.learningjavaassistantfrontend.GUIParameters.REGISTER_HEIGHT;
import static assistant.front.learningjavaassistantfrontend.GUIParameters.REGISTER_WIDTH;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), REGISTER_WIDTH, REGISTER_HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showRegisterWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}