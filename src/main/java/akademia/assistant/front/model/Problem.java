package akademia.assistant.front.model;

import java.util.ArrayList;
import java.util.List;

public class Problem {
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

  public boolean wasAnsweredFor(User user){
      for (Comment comment : comments) {
          if (user.equals(comment.getSender())) {
              return true;
          }
      }
      return false;
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

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return title;
    }
}
