package akademia.assistant.front.controller;

import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

public class MainWindowController extends Controller {

    @FXML
    private TextField answerField;
    @FXML
    private Label answerLabel;
    @FXML
    private ListView<Category> categoriesListView;
    @FXML
    private Label categoryLabel;
    @FXML
    private TextFlow descriptionProblemField;
    @FXML
    private Label descriptionProblemLabel;
    @FXML
    private TextFlow listOfAnswersField;
    @FXML
    private Label listOfAnswersLabel;
    @FXML
    private Label problemLabel;
    @FXML
    private ListView<?> problemsListView;
    @FXML
    private Label titleLabel;
    private String FXMLName = "main-window.fxml";

    public MainWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
    }

    @FXML
    void confirmAnswer() {
        System.out.println("confirm answer");
    }

    @Override
    public String getFXMLName() {
        return FXMLName;
    }
}
