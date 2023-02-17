package assistant.front.learningjavaassistantfrontend;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String categoryName = "";
    private List<Problem> problems;

    Category(String categoryName, List<Problem> problems) {
        this.categoryName = categoryName;
        this.problems = problems;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Problem> getProblems() {
        return problems;
    }
}
