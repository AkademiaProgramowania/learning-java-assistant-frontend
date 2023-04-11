package akademia.assistant.front.controller;

import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends Controller implements Initializable {
    @FXML
    private TextField answerField;

    @FXML
    private Label descriptionProblem;

    @FXML
    private Label listOfAnswers;

    @FXML
    private ChoiceBox<Category> listOfCategoriesCb;

    @FXML
    private ChoiceBox<Problem> listOfProblemsCb;

    private final String FXMLName = "main-window.fxml";
    private final ObservableList<Category> categoriesObservableList;
    private ObservableList<Problem> problemsObservableList;

    public MainWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
        categoriesObservableList = FXCollections.observableList(service.getCategories());
    }

    @Override
    public String getFXMLName() {
        return FXMLName;
    }

    @FXML
    void confirmAnswer() {
        System.out.println("answer confirmed");// TODO: 11.04.2023 initialize add answer button
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfAnswers.setText("Lista odpowiedzi na pytanie"); // TODO: 11.04.2023 to remove
        listOfCategoriesCb.setItems(categoriesObservableList);
        listOfCategoriesCb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                problemsObservableList = FXCollections.observableList(categoriesObservableList.get(newValue.intValue()).getProblems());
                listOfProblemsCb.setItems(problemsObservableList);
            }
        });
        listOfProblemsCb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                descriptionProblem.setText(problemsObservableList.get(newValue.intValue()).getQuestion());
                // TODO: 11.04.2023 bugs
            }
        });
    }
}