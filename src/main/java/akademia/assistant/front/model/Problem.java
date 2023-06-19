package akademia.assistant.front.model;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private String title = "";
    private String question = "";
    private List<Comment> comments;
    private boolean wasAnswered; // TODO: 19.06.2023 chyba źle? sprawdzanie czy odpowiedzi udzielono, powinno znajdować się gdzie indziej?

    public Problem(String title, String question) {
        this(title,question,new ArrayList<>());
    }

    public Problem(String title, String question, List<Comment> comments) {
        this.title = title;
        this.question = question;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public boolean isWasAnswered() {
        return wasAnswered;
    }

    public void setWasAnswered(boolean wasAnswered) {
        this.wasAnswered = wasAnswered;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return title;
    }
}
