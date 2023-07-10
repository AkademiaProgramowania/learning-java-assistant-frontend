package akademia.assistant.front.model;

import java.time.LocalDate;

public class Comment {
    private final User sender;
    private final String answer;
    private final String date;
    private int likes = 0;

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

    public String getLikes() {
        return Integer.toString(likes);
    }

    public void increaseLikes() {
        likes++;
    }

    public void decreaseLikes() {
        if (likes != 0) {
            likes--;
        }
    }
}
