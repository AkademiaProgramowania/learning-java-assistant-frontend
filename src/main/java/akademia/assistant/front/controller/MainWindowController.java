package akademia.assistant.front.controller;

import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController extends Controller {

    public MainWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
    }

    private String FXMLName = "main-window.fxml";

    @FXML
    private Label title;

    public String getFXMLName() {
        return FXMLName;
    }
}
