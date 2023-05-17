package akademia.assistant.front.factory;

import akademia.assistant.front.model.Problem;

import java.util.ArrayList;
import java.util.List;

public class ProblemsFactory {
    public List<Problem> basicProblems() {
        Problem basics1 = new Problem("Wyświetlanie w konsoli",
                "Jak wygląda instrukcja, która drukuje w języku Java?");
        Problem basics2 = new Problem("Tytuł problemu 2", "Pytanie problemu 2");
        Problem basics3 = new Problem("Tytuł problemu 3", "Pytanie problemu 3");
        List<Problem> basicProblems = new ArrayList<>();
        basicProblems.add(basics1);
        basicProblems.add(basics2);
        basicProblems.add(basics3);
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
        return OOPProblems;
    }

    public List<Problem> advanceProblems() {
        Problem advance1 = new Problem("Tytuł problemu 10",
                "Pytanie problemu 10?");
        Problem advance2 = new Problem("Tytuł problemu 11",
                "Pytanie problemu 11?");
        List<Problem> OOPProblems = new ArrayList<>();
        OOPProblems.add(advance1);
        OOPProblems.add(advance2);
        return OOPProblems;
    }
}
