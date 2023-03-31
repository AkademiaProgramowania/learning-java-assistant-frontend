package akademia.assistant.front.controller;

import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController implements Controller {
    private Service service = new Service();
    private ViewFactory viewFactory;

    public MainWindowController(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    private String FXMLName = "main-window.fxml";

    @FXML
    private Label title;

    public String getFXMLName() {
        return FXMLName;
    }
}
