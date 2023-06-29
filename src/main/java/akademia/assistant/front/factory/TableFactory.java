package akademia.assistant.front.factory;

public class TableFactory {
    private final String user;
    private final String date;
    private final String comment;
    private final String likes;

    public TableFactory(String user, String date, String comment, String likes) {
        this.user = user;
        this.date = date;
        this.comment = comment;
        this.likes = likes;
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
        return likes;
    }
}
