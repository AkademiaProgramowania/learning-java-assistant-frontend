package assistant.front.learningjavaassistantfrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static assistant.front.learningjavaassistantfrontend.GUIParameters.REGISTER_HEIGHT;
import static assistant.front.learningjavaassistantfrontend.GUIParameters.REGISTER_WIDTH;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), REGISTER_WIDTH, REGISTER_HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}