package assistant.front.learningjavaassistantfrontend.controller;

import assistant.front.learningjavaassistantfrontend.service.Service;
import assistant.front.learningjavaassistantfrontend.view.ViewFactory;
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
