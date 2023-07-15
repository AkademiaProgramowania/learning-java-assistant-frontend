package akademia.assistant.front.controller;

import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Comment;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.net.URL;
import java.util.Objects;
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
    private TableView<Comment> listOfAnswersTable;

    @FXML
    private TableColumn<Comment, String> userColumn;

    @FXML
    private TableColumn<Comment, String> dateColumn;

    @FXML
    private TableColumn<Comment, String> answerColumn;

    @FXML
    private TableColumn<Comment, String> likesColumn;

    @FXML
    private TableColumn<Comment, Button> likeButtonsColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private ChoiceBox<Category> listOfCategoriesCb;

    @FXML
    private ChoiceBox<Problem> listOfProblemsCb;

    private final static String FXML_NAME = "main-window.fxml";
    private final ObservableList<Category> categoriesObservableList;
    private SingleSelectionModel<Category> categorySelectionModel;
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
            listOfAnswersTable.setVisible(true);
            showCommentsOfProblem(problemSelectionModel.getSelectedItem());
        }
    }

    private void addComment(Problem problem) {
        problem.addComment(new Comment(service.getCurrentUser(), answerArea.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfCategoriesCb.setItems(categoriesObservableList);
        categorySelectionModel = listOfCategoriesCb.getSelectionModel();
        problemSelectionModel = listOfProblemsCb.getSelectionModel();
        listOfAnswersTable.setVisible(false);
        userColumn.setCellFactory(new CentreCellFactory());
        dateColumn.setCellFactory(new CentreCellFactory());
        answerColumn.setCellFactory(new TextAreaCellFactory());
        likesColumn.setSortType(TableColumn.SortType.DESCENDING);
        likeButtonsColumn.setCellFactory(new ButtonCellFactory());
        addProblemButton.setDisable(true);
        errorLabel.setVisible(false);
        answerArea.setWrapText(true);
        addListeners();
    }

    private void addListeners() {
        categorySelectionModel.selectedItemProperty().addListener(
                (observable, oldCategory, newCategory) -> showListOfProblems(newCategory));
        problemSelectionModel.selectedItemProperty().addListener(
                (observable, oldProblem, newProblem) -> {
                    showDescriptionOfProblem(newProblem);
                    showListOfComments(newProblem);
                });
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
            listOfAnswersTable.setVisible(true);
        } else {
            listOfAnswersTable.setVisible(false);
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
        userColumn.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getSender().getUsername()));
        dateColumn.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getDate()));
        answerColumn.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getAnswer()));
        likesColumn.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getLikes()));
        ObservableList<Comment> data = FXCollections.observableArrayList(problem.getComments());
        listOfAnswersTable.setItems(data);
        sortTableByLikes();
    }
    private void sortTableByLikes() {
        listOfAnswersTable.getSortOrder().add(likesColumn);
        listOfAnswersTable.refresh();
    }

    private static class CentreCellFactory implements Callback<TableColumn<Comment, String>, TableCell<Comment, String>> {

        @Override
        public TableCell<Comment, String> call(TableColumn<Comment, String> param) {
            return new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        }
    }

    private static class TextAreaCellFactory implements Callback<TableColumn<Comment, String>, TableCell<Comment, String>> {

        @Override
        public TableCell<Comment, String> call(TableColumn<Comment, String> param) {
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

    private class ButtonCellFactory implements Callback<TableColumn<Comment, Button>, TableCell<Comment, Button>> {

        @Override
        public TableCell<Comment, Button> call(TableColumn<Comment, Button> param) {
            return new TableCell<>() {
                private final Button likeButton = new Button();
                private final Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/textures/like.png")));
                private final ImageView imageView = new ImageView(image);
                private Comment comment;

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    likeButton.setMinSize(25, 25);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        comment = getTableView().getItems().get(getIndex());
                        if (comment.isOwnComment(service.getCurrentUser())) {// CHECK: 14.07.2023 jakiś pomysł na połączenie zagnieżdżonych warunków?
                            setGraphic(null);
                        } else {
                            likeButton.setGraphic(imageView);
                            likeButton.setOnAction(event -> rateComment());
                            setGraphic(likeButton);
                        }
                    }
                }

                private void rateComment() {
                    if (comment.userGaveLike(service.getCurrentUser())) {
                        comment.decreaseLikes();
                    } else {
                        comment.increaseLikes();
                    }
                    sortTableByLikes();
                }
            };
        }
    }
}
