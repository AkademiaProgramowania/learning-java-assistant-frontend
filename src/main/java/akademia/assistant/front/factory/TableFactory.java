package akademia.assistant.front.factory;

import akademia.assistant.front.model.Comment;

public class TableFactory {
    private final String user;
    private final String date;
    private final String comment;
    private final String likes;
    private final Comment commentObject;

    public TableFactory(String user, String date, String comment, String likes, Comment commentObject) {
        this.user = user;
        this.date = date;
        this.comment = comment;
        this.likes = likes;
        this.commentObject = commentObject;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getLikes() {
        return commentObject.getLikes();
    }

    public void increaseLikes() {
        commentObject.increaseLikes();
    }
}
