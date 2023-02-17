package assistant.front.learningjavaassistantfrontend;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private Repository repository = new BinFileRepository();
    private List<Category> categoryList;
    private List<User> users;

    private User currentUser;

    Service() {
        users = repository.loadUsersFromFile();
        categoryList = repository.loadCategoriesFromFile();
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void clearCurrentUser() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
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

    public boolean doesNicknameExist(String newUsername) {
        for (User user:users) {
            if (user.getUsername().equals(newUsername)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesUserExist(String username, String password) {
        for (User user:getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void updateCategories() {
        repository.updateCategoriesToFile(categoryList);
    }

    public void addUserToUserList(User newUser) {
        users.add(newUser);
        repository.saveUsersToFile(users);
    }

    public List<User> getUsers() {
        return users;
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
}
