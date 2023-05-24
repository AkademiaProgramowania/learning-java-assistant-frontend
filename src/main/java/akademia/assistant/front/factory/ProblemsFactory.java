package akademia.assistant.front.factory;

import akademia.assistant.front.model.Comment;
import akademia.assistant.front.model.Problem;

import java.util.ArrayList;
import java.util.List;

public class ProblemsFactory {
    private final CommentsFactory commentsFactory = new CommentsFactory();

    public List<Problem> basicProblems() {
        Problem basics1 = new Problem("Wyświetlanie w konsoli",
                "Jak wygląda instrukcja, która drukuje w języku Java?");
        Problem basics2 = new Problem("Tytuł problemu 2", "Pytanie problemu 2");
        Problem basics3 = new Problem("Tytuł problemu 3", "Pytanie problemu 3");
        List<Problem> basicProblems = new ArrayList<>();
        basicProblems.add(basics1);
        basicProblems.add(basics2);
        basicProblems.add(basics3);

        for (int i = 0; i < commentsFactory.basic1ProblemsComments().size(); i++) {
            basics1.addComment(commentsFactory.basic1ProblemsComments().get(i));
        }

        for (int i = 0; i < commentsFactory.basic2ProblemsComments().size(); i++) {
            basics2.addComment(commentsFactory.basic2ProblemsComments().get(i));
        }

        for (int i = 0; i < commentsFactory.basic3ProblemsComments().size(); i++) {
            basics3.addComment(commentsFactory.basic3ProblemsComments().get(i));
        }



        return basicProblems;
    }

    public List<Problem> OOPProblems() {
        Problem OOP1 = new Problem("Tworzenie obiektu",
                "Jaką instrukcją należy stworzyć obiekt przykładowej klasy Controller?");
        Problem OOP5 = new Problem("Tytuł problemu 5", "Pytanie problemu 5");
        Problem OOP6 = new Problem("Tytuł problemu 6", "Pytanie problemu 6");
        List<Problem> OOPProblems = new ArrayList<>();
        OOPProblems.add(OOP1);
        OOPProblems.add(OOP5);
        OOPProblems.add(OOP6);

        for (int i = 0; i < commentsFactory.OOP1ProblemsComments().size(); i++) {
            OOP1.addComment(commentsFactory.OOP1ProblemsComments().get(i));
        }

        for (int i = 0; i < commentsFactory.OOP5ProblemsComments().size(); i++) {
            OOP5.addComment(commentsFactory.OOP5ProblemsComments().get(i));
        }

        for (int i = 0; i < commentsFactory.OOP6ProblemsComments().size(); i++) {
            OOP6.addComment(commentsFactory.OOP6ProblemsComments().get(i));
        }

        return OOPProblems;
    }

    public List<Problem> advanceProblems() {
        Problem advance1 = new Problem("Tytuł problemu 10",
                "Pytanie problemu 10?");
        Problem advance2 = new Problem("Tytuł problemu 11",
                "Pytanie problemu 11?");
        Problem advance3 = new Problem("Tytuł problemu 12", "Pytanie problemu 12?");
        List<Problem> advanceProblems = new ArrayList<>();
        advanceProblems.add(advance1);
        advanceProblems.add(advance2);
        advanceProblems.add(advance3);


        for (int i = 0; i < commentsFactory.advance10ProblemsComments().size(); i++) {
            advance1.addComment(commentsFactory.advance10ProblemsComments().get(i));
        }

        for (int i = 0; i < commentsFactory.advance11ProblemsComments().size(); i++) {
            advance2.addComment(commentsFactory.advance11ProblemsComments().get(i));
        }

        for (int i = 0; i < commentsFactory.advance12ProblemsComments().size(); i++) {
            advance3.addComment(commentsFactory.advance12ProblemsComments().get(i));
        }

        return advanceProblems;
    }

    private void addCommentsToProblem(Problem problem, List<Comment> commentsToProblem) {
        for (Comment comment : commentsToProblem) {
            problem.addComment(comment);
        }
    }
}
