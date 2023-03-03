package assistant.front.learningjavaassistantfrontend;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private final Repository repository = new BinFileRepository();
    private final List<Category> categoryList;
    private final List<User> users;
    private User currentUser;

    Service() {
        users = repository.loadUsersFromFile();
        categoryList = repository.loadCategoriesFromFile();
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
        repository.saveUsersToFile(users);
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
        List<Problem> basicProblems = new ArrayList<>();
        basicProblems.add(basics1);
        return basicProblems;
    }

    public List<Problem> OOPProblemFactory() {
        Problem OOP1 = new Problem("Tworzenie obiektu",
                "Jaką instrukcją należy stworzyć obiekt przykładowej klasy Controller?");
        List<Problem> OOPProblems = new ArrayList<>();
        OOPProblems.add(OOP1);
        return OOPProblems;
    }
}
