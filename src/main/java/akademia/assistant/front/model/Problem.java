package akademia.assistant.front.model;

import akademia.assistant.front.jackson.ObjectToJson;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private String title = "";
    private String question = "";
    private List<Comment> comments;
    private ObjectToJson objectToJson = new ObjectToJson();

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
        objectToJson.convertObjectToJson(comments);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return title;
    }
}
