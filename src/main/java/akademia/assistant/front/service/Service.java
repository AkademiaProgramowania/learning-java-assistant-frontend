package akademia.assistant.front.service;

import akademia.assistant.front.model.User;
import akademia.assistant.front.exception.AuthenticationException;
import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.Problem;
import akademia.assistant.front.repository.BinFileRepository;
import akademia.assistant.front.repository.Repository;

import java.util.ArrayList;
import java.util.List;
//todo refactor to two main services: for Authentication and for basic operations on Problem
public class Service {
    private final Repository repository = new BinFileRepository();
    private final List<Category> categoryList;
    private final List<User> users;
    private User currentUser;

    public Service() {
        users = repository.loadUsers();
//        categoryList = repository.loadCategoriesFromFile();
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Basic", basicProblemFactory()));
        categoryList.add(new Category("OOP", OOPProblemFactory()));
        categoryList.add(new Category("Advance", advanceProblemFactory()));
    }

    public void login(String username, String password) {
        if (!doesUserExist(username, password)) {
            throw new AuthenticationException("Użytkownik nie istnieje. Spróbuj jeszcze raz.");
        }
        currentUser = new User(username, password);
    }

    private boolean doesUserExist(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new AuthenticationException("Niepoprawna nazwa użytkownika lub hasło.");
        } else if (doesNicknameExist(username)) {
            throw new AuthenticationException("Użytkownik o tym nicku już istnieje.");
        }
        User newUser = new User(username, password);
        addUserToUserList(newUser);
        currentUser = newUser;
    }


    private boolean doesNicknameExist(String newUsername) {
        for (User user : users) {
            if (user.getUsername().equals(newUsername)) {
                return true;
            }
        }
        return false;
    }

    private void addUserToUserList(User newUser) {
        users.add(newUser);
        repository.saveUsers(users);
    }

    private void clearCurrentUser() {
        currentUser = null;
    }

    private User getCurrentUser() {
        return currentUser;
    }

    private void updateCategories() {
        repository.updateCategoriesToFile(categoryList);
    }

    public List<Category> getCategories() {
        return categoryList;
    }

    public int getAmountCategories() {
        return categoryList.size();
    }

    public Category getCategoryByIndex(int index) {
        return categoryList.get(index);
    }

    public List<Problem> basicProblemFactory() {
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

    public List<Problem> OOPProblemFactory() {
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

    public List<Problem> advanceProblemFactory() {
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
