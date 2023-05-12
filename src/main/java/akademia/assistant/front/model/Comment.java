package akademia.assistant.front.model;

public class Comment {
    private User sender;
    private String answer = "";

    public Comment(User sender, String answer) {
        this.sender = sender;
        this.answer = answer;
    }

    public User getSender() {
        return sender;
    }

    public String getAnswer() {
        return answer;
    }
}
