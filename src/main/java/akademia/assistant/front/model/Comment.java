package akademia.assistant.front.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private final User sender;
    private final String answer;
    private final String date;
    private int likes = 0;
    private final List<User> usersWhoLiked = new ArrayList<>();

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

    public boolean userGaveLike(User user) {
        for (User u : usersWhoLiked) {
            if (u.equals(user)) {
                usersWhoLiked.remove(user);
                return true;
            }
        }
        usersWhoLiked.add(user);
        return false;
    }

    public boolean isOwnComment(User user) {
        return sender.getUsername().equals(user.getUsername());
    }
}
