package akademia.assistant.front.controller;

import akademia.assistant.front.factory.TableFactory;
import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Comment;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindowController extends Controller implements Initializable {

    @FXML
    private Button addProblemButton;

    @FXML
    private TextArea answerArea;

    @FXML
    private Label descriptionProblem;

    @FXML
    private TableView<TableFactory> listOfAnswers;

    @FXML
    private TableColumn<TableFactory, String> lOAAnswer;

    @FXML
    private TableColumn<TableFactory, LocalDate> lOADate;

    @FXML
    private TableColumn<TableFactory, String> lOAUser;

    @FXML
    private Label errorLabel;

    @FXML
    private ChoiceBox<Category> listOfCategoriesCb;

    @FXML
    private ChoiceBox<Problem> listOfProblemsCb;

    private final static String FXML_NAME = "main-window.fxml";
    private final ObservableList<Category> categoriesObservableList;
    private SingleSelectionModel<Problem> problemSelectionModel;

    public MainWindowController(Service service, ViewFactory viewFactory) {
        super(service, viewFactory);
        categoriesObservableList = FXCollections.observableList(service.getCategories());
    }

    @Override
    public String getFXMLName() {
        return FXML_NAME;
    }

    @FXML
    void onAddProblemButtonClick() {
        closeWindow();
        viewFactory.showProblemWindow(listOfCategoriesCb.getValue());
    }

    @FXML
    void confirmAnswer() {
        if (problemSelectionModel.isEmpty() || answerArea.getText().isBlank()) {
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
            addComment(problemSelectionModel.getSelectedItem());
            answerArea.clear();
            listOfAnswers.setVisible(true);
            showCommentsOfProblem(problemSelectionModel.getSelectedItem());
        }
    }

    private void addComment(Problem problem) {
        problem.addComment(new Comment(service.getCurrentUser(), answerArea.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lOAAnswer.setCellFactory(new TextAreaCellFactory());
        SingleSelectionModel<Category> categorySelectionModel = listOfCategoriesCb.getSelectionModel();
        problemSelectionModel = listOfProblemsCb.getSelectionModel();
        listOfAnswers.setVisible(false);
        listOfCategoriesCb.setItems(categoriesObservableList);
        addProblemButton.setDisable(true);
        errorLabel.setVisible(false);
        answerArea.setWrapText(true);
        categorySelectionModel.selectedItemProperty().addListener(
                (observable, oldCategory, newCategory) -> showListOfProblems(newCategory));
        problemSelectionModel.selectedItemProperty().addListener(
                (observable, oldProblem, newProblem) -> showDescriptionOfProblem(newProblem));
        problemSelectionModel.selectedItemProperty().addListener(
                (observable, oldProblem, newProblem) -> showListOfComments(newProblem));
    }

    private void showListOfProblems(Category category) {
        ObservableList<Problem> problemsObservableList = FXCollections.observableList(category.getProblems());
        listOfProblemsCb.setItems(problemsObservableList);
        addProblemButton.setDisable(false);
    }

    private void showDescriptionOfProblem(Problem problem) {
        Optional<Problem> selectedProblem = getSelectedProblem();
        if (selectedProblem.isPresent()) {
            descriptionProblem.setVisible(true);
            descriptionProblem.setText(problem.getQuestion());
        } else {
            descriptionProblem.setVisible(false);
        }
    }

    private void showListOfComments(Problem problem) {
        Optional<Problem> selectedProblem = getSelectedProblem();
        if (selectedProblem.isPresent() && userAnsweredQuestion(problem)) {
            showCommentsOfProblem(problem);
            listOfAnswers.setVisible(true);
        } else {
            listOfAnswers.setVisible(false);
        }
    }

    private Optional<Problem> getSelectedProblem() {
        SingleSelectionModel<Problem> problemSelectionModel = listOfProblemsCb.getSelectionModel();
        Problem problem = problemSelectionModel.getSelectedItem();
        if (problem == null || problemSelectionModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(problem);
    }

    private boolean userAnsweredQuestion(Problem selectedProblem) {
        return selectedProblem.wasAnsweredFor(service.getCurrentUser());
    }

    private void showCommentsOfProblem(Problem problem) {
        lOAUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        lOAAnswer.setCellValueFactory(new PropertyValueFactory<>("comment"));
        lOADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<TableFactory> data = FXCollections.observableArrayList();
        for (Comment comment : problem.getComments()) {
            TableFactory tableFactory = new TableFactory(comment.getSender().getUsername(), comment.getAnswer(), comment.getDate());
            data.add(tableFactory);
        }
        listOfAnswers.setItems(data);
    }

    private static class TextAreaCellFactory implements Callback<TableColumn<TableFactory, String>, TableCell<TableFactory, String>> {

        @Override
        public TableCell<TableFactory, String> call(TableColumn<TableFactory, String> param) {
            return new TableCell<>() {
                private final TextArea textArea = new TextArea();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        textArea.setText(item);
                        textArea.setWrapText(true);
                        textArea.setEditable(false);
                        setGraphic(textArea);
                    }
                }
            };
        }
    }
}
