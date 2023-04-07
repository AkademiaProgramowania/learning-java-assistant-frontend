package akademia.assistant.front.controller;

import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends Controller implements Initializable {
    @FXML
    private TextField answerField;

    @FXML
    private TextFlow descriptionProblemField;

    @FXML
    private TextFlow listOfAnswersField;

    @FXML
    private ChoiceBox<Category> listOfCategories;

    @FXML
    private ChoiceBox<Problem> listOfProblems;

    private final String FXMLName = "main-window.fxml";

    private ObservableList<Category> categoriesList = FXCollections.observableList(service.getCategories());

    public MainWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
    }

    @Override
    public String getFXMLName() {
        return FXMLName;
    }

    @FXML
    void confirmAnswer() {
        System.out.println("answer confirmed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfCategories.setItems(categoriesList);
    }
}