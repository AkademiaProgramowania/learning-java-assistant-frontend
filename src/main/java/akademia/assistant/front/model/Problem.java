package akademia.assistant.front.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Problem implements Serializable {
    private String title = "";
    private String question = "";
    private List<Comment> comments;

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

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}
