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
import java.util.Random;
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
        if (chosenProblem.isEmpty() || answerArea.getText().isEmpty()) {
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
            addComment(chosenProblem.getSelectedItem());
            answerArea.clear();
            chosenProblem.getSelectedItem().setWasAnswered(true);
//            showCommentsOfProblem(chosenProblem.getSelectedItem());
        }
    }

    private void addComment(Problem problem) {
        problem.addComment(new Comment(service.getCurrentUser(), answerArea.getText()));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lOAAnswer.setCellFactory(new TextAreaCellFactory());
        chosenCategory = listOfCategoriesCb.getSelectionModel();
        chosenProblem = listOfProblemsCb.getSelectionModel();
        listOfCategoriesCb.setItems(categoriesObservableList);
        addProblemButton.setDisable(true);
        errorLabel.setVisible(false);
        answerArea.setWrapText(true);
        chosenCategory.selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> showListOfProblems(newValue));
        chosenProblem.selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> showDescriptionOfProblem(newValue));
        chosenProblem.selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> showListOfComments(newValue));
    }

    private void showDescriptionOfProblem(Number selectedProblem) {
        if (chosenProblem.isEmpty()) {
            descriptionProblem.setVisible(false);
        } else {
            descriptionProblem.setVisible(true);
            descriptionProblem.setText(problemsObservableList.get(selectedProblem.intValue()).getQuestion());
        }
    }
    
    private void showListOfProblems(Number selectedCategory) {
        problemsObservableList = FXCollections.observableList(categoriesObservableList.get(selectedCategory.intValue()).getProblems());
        listOfProblemsCb.setItems(problemsObservableList);
        addProblemButton.setDisable(false);
    }

    private void showListOfComments(Number selectedProblem) {
        if (!chosenProblem.isEmpty() && chosenProblem.getSelectedItem().isWasAnswered()) {
            System.out.println("----"); // TODO: 19.06.2023 dokończyć
        } else {
            System.out.println("QQQQ");
        }
    }


/*
* S - typ table View (w tej chwili TableFactory)
* T - typ table collumn w tym przypadku String
* */
    private static class TextAreaCellFactory implements Callback<TableColumn<TableFactory, String>, TableCell<TableFactory, String>> { // TODO: 19.06.2023 innego sposobu nie znalazłem

      /*  @Override
        public TableCell<S, T> call(TableColumn<S, T> param) {
            return new TableCell<>() {
                private final TextArea textArea = new TextArea();

                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        textArea.setText(item.toString());
                        textArea.setWrapText(true);
                        textArea.setEditable(false);
                        setGraphic(textArea);
                    }
                }
            };
        }*/

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
