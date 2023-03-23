package assistant.front.learningjavaassistantfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController implements Controller {
    private Service service = new Service();
    private ViewFactory viewFactory;

    MainWindowController(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    private String FXMLName = "main-window.fxml";

    @FXML
    private Label title;

    public String getFXMLName() {
        return FXMLName;
    }
}
