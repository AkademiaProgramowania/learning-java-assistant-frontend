package akademia.assistant.front.controller;

import akademia.assistant.front.exception.ProblemSubmitException;
import akademia.assistant.front.model.Category;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProblemWindowController extends Controller {

    @FXML
    private TextField problemTitleField;
    @FXML
    private TextField problemDetailsField;
    @FXML
    private Label errorLabel;

    private String FXMLName = "problem-window.fxml";

    public ProblemWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
    }

    @FXML
    void onPublishProblemButtonClick() {
        try {

        } catch (ProblemSubmitException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @Override
    public String getFXMLName() {
        return null;
    }
}
