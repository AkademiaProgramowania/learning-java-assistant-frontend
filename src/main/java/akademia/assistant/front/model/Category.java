package akademia.assistant.front.model;

import java.util.List;

public final class Category {
    private String categoryName = "";
    private List<Problem> problems;

    public Category(String categoryName, List<Problem> problems) {
        this.categoryName = categoryName;
        this.problems = problems;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void addProblem(Problem problem) {
        problems.add(problem);
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
