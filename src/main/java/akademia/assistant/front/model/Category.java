package akademia.assistant.front.model;

import akademia.assistant.front.exception.ProblemSubmitException;
import akademia.assistant.front.jackson.ObjectToJson;

import java.util.List;

public class Category {
    private String categoryName = "";
    private List<Problem> problems;

    private ObjectToJson objectToJson = new ObjectToJson();

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
        if (problem.getTitle().isEmpty() || problem.getQuestion().isEmpty()) {
            throw new ProblemSubmitException("Brakuje tytułu lub pytania. Spróbuj jeszcze raz.");
        }
        problems.add(problem);
        objectToJson.convertObjectToJson(problems);
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
