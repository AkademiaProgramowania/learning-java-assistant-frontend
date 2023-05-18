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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends Controller implements Initializable {

    @FXML
    private Button addProblemButton;

    @FXML
    private TextField answerField;

    @FXML
    private Label descriptionProblem;

    @FXML
    private Label listOfAnswers;

    @FXML
    private Label errorLabel;

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
    void onAddProblemButtonClick() {
        closeWindow();
        viewFactory.showProblemWindow(listOfCategoriesCb.getValue());
    }

    @FXML
    void confirmAnswer() {
        if (listOfProblemsCb.getSelectionModel().isEmpty() || answerField.getText().isEmpty()) {
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfAnswers.setText("Lista odpowiedzi na pytanie"); // TODO: 11.04.2023 to remove
        listOfCategoriesCb.setItems(categoriesObservableList);
        addProblemButton.setDisable(true);
        errorLabel.setVisible(false);
        listOfCategoriesCb.getSelectionModel().selectedIndexProperty().addListener(new CategoryListener());
        listOfProblemsCb.getSelectionModel().selectedIndexProperty().addListener(new ProblemListener());
    }

    class CategoryListener implements ChangeListener<Number> {// CHECK: 18.05.2023

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {// CHECK: 18.05.2023 czemu tu jest pytajnik? Czy może być Category?
            problemsObservableList = FXCollections.observableList(categoriesObservableList.get(newValue.intValue()).getProblems());
            listOfProblemsCb.setItems(problemsObservableList);
            addProblemButton.setDisable(false);
        }
    }

    class ProblemListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                descriptionProblem.setText(problemsObservableList.get(newValue.intValue()).getQuestion());
        }
    }
}