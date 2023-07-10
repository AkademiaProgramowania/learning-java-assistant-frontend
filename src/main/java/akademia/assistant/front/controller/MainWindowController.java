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
    private TableView<Comment> listOfAnswers;

    @FXML
    private TableColumn<Comment, String> lOAUser;

    @FXML
    private TableColumn<Comment, String> lOADate;

    @FXML
    private TableColumn<Comment, String> lOAAnswer;

    @FXML
    private TableColumn<Comment, String> lOALikes;

    @FXML
    private TableColumn<Comment, Button> lOALikeButtons;

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
            listOfAnswers.setVisible(true);
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
        listOfAnswers.setVisible(false);
        lOAUser.setCellFactory(new CentreCellFactory());
        lOADate.setCellFactory(new CentreCellFactory());
        lOAAnswer.setCellFactory(new TextAreaCellFactory());
        lOALikeButtons.setCellFactory(new ButtonCellFactory());
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
        lOAUser.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getSender().getUsername()));
        lOADate.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getDate()));
        lOAAnswer.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getAnswer()));
        lOALikes.setCellValueFactory(commentCell -> new SimpleStringProperty(commentCell.getValue().getLikes()));
        ObservableList<Comment> data = FXCollections.observableArrayList(problem.getComments());
        listOfAnswers.setItems(data);
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

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    likeButton.setMinSize(25, 25);
                    likeButton.setOnAction(event -> rateComment());
                    if (empty) {
                        setGraphic(null);
                    } else {
                        likeButton.setGraphic(imageView);
                        likeButton.setDisable(false);
                        setGraphic(likeButton);
                    }
                }
                private void rateComment() {
                    Comment comment = getTableView().getItems().get(getIndex());
                    comment.increaseLikes();
                    listOfAnswers.refresh();
                }
            };
        }
    }
}
