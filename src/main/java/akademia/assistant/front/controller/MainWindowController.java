package akademia.assistant.front.controller;

import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Comment;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private SingleSelectionModel<Category> chosenCategory;
    private SingleSelectionModel<Problem> chosenProblem;

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
        if (chosenProblem.isEmpty() || answerField.getText().isEmpty()) {
            errorLabel.setVisible(true);
            System.out.println("nie dodano odpowiedzi"); // TODO: 19.05.2023 to remove
            listOfAnswers.setText("");
        } else {
            errorLabel.setVisible(false);
            System.out.println("odpowiedź dodana");// TODO: 19.05.2023 to remove
            chosenProblem.getSelectedItem().addComment(new Comment(service.getCurrentUser(), answerField.getText())); // TODO: 24.05.2023 w serwisie musiałem zmienić dostęp do metody getCurrentUser na public
            listOfAnswers.setText(showCommentsOfProblem());
        }
    }

    private String showCommentsOfProblem() {// TODO: 24.05.2023 to change??
        StringBuilder commentsOfProblem = new StringBuilder();
        for (int i = 0; i < chosenProblem.getSelectedItem().getComments().size(); i++) {
            Comment actualComment = chosenProblem.getSelectedItem().getComments().get(i);
            commentsOfProblem
                    .append("UŻYTKOWNIK: ").append(actualComment.getSender().getUsername())
                    .append("    ODPOWIEDŹ : ").append(actualComment.getAnswer()).append("\n");
        }
        return commentsOfProblem.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chosenCategory = listOfCategoriesCb.getSelectionModel();
        chosenProblem = listOfProblemsCb.getSelectionModel();
        listOfCategoriesCb.setItems(categoriesObservableList);
        addProblemButton.setDisable(true);
        errorLabel.setVisible(false);
        chosenCategory.selectedIndexProperty().addListener(new CategoryListener());
        chosenProblem.selectedIndexProperty().addListener(new ProblemListener());
    }

    class CategoryListener implements ChangeListener<Number> {// CHECK: 18.05.2023 czy przenieść tą klasę do nowego pakietu?

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