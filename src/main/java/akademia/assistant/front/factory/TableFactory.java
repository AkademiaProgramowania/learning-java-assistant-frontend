package akademia.assistant.front.factory;

public class TableFactory {
    private final String user;
    private final String comment;

    public TableFactory(String user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }
}
