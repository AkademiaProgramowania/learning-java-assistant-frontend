package assistant.front.learningjavaassistantfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

public class MainWindowController {

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

    public MainWindowController() {
        List<Problem> problems = new ArrayList<>();
        problems.add(new Problem("Problem1", "Question1"));
        problems.add(new Problem("Problem2", "Question2"));
        problems.add(new Problem("Problem3", "Question3"));
        problems.add(new Problem("Problem4", "Question4"));
        this.categoriesListView = new ListView<>();
        categoriesListView.getItems().add(new Category("Category 1", problems));
    }

    @FXML
    void confirmAnswer() {
        System.out.println("confirm answer");
    }

}
