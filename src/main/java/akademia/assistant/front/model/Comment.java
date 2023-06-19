package akademia.assistant.front.model;

import java.time.LocalDate;

public class Comment {
    private final User sender;
    private String answer = "";
    private String date;

    public Comment(User sender, String answer) {
        this.sender = sender;
        this.answer = answer;
        this.date = LocalDate.now().toString();
    }

    public User getSender() {
        return sender;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDate() {
        return date;
    }
}
