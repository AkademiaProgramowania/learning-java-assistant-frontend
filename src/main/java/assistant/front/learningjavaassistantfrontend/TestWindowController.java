package assistant.front.learningjavaassistantfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestWindowController {

    @FXML
    private Label textLabel;

    @FXML
    void onButtonClick(ActionEvent event) {
        System.out.println("Click!");
    }

}
