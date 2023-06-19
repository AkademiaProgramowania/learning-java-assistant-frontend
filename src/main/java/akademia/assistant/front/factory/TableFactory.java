package akademia.assistant.front.factory;

public class TableFactory {
    private final String user;
    private final String comment;
    private final String date;

    public TableFactory(String user, String comment, String date) {
        this.user = user;
        this.comment = comment;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }
}
