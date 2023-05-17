package akademia.assistant.front.service;

import akademia.assistant.front.factory.ProblemsFactory;
import akademia.assistant.front.model.User;
import akademia.assistant.front.exception.AuthenticationException;
import akademia.assistant.front.model.Category;
import akademia.assistant.front.repository.Repository;
import akademia.assistant.front.repository.RepositoryImpl;

import java.util.List;
//todo refactor to two main services: for Authentication and for basic operations on Problem
public class Service {
    private final Repository<User> usersRepository = new RepositoryImpl<>();
    private final Repository<Category> categoryRepository = new RepositoryImpl<>();
    private User currentUser;

    public Service() {
        usersRepository.addElement(new User("qwe", "qwe"));//added to inspect login possibility
        ProblemsFactory problemFactory = new ProblemsFactory();
        categoryRepository.addElement(new Category("Basic", problemFactory.basicProblems()));
        categoryRepository.addElement(new Category("OOP", problemFactory.OOPProblems()));
        categoryRepository.addElement(new Category("Advance", problemFactory.advanceProblems()));
    }

    public void login(String username, String password) {
        if (!doesUserExist(username, password)) {
            throw new AuthenticationException("Użytkownik nie istnieje. Spróbuj jeszcze raz.");
        }
        currentUser = new User(username, password);
    }

    private boolean doesUserExist(String username, String password) {
        for (User user : usersRepository.getElements()) {
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
        for (User user : usersRepository.getElements()) {
            if (user.getUsername().equals(newUsername)) {
                return true;
            }
        }
        return false;
    }

    private void addUserToUserList(User newUser) {
        usersRepository.addElement(newUser);
    }

    private void clearCurrentUser() {
        currentUser = null;
    }

    private User getCurrentUser() {
        return currentUser;
    }

    /*private void updateCategories() {
        repository.updateCategoriesToFile(categoryList);
    }*/

    public List<Category> getCategories() {
        return categoryRepository.getElements();
    }

    public int getAmountCategories() {
        return categoryRepository.getElements().size();
    }

    public Category getCategoryByIndex(int index) {
        return categoryRepository.getElements().get(index);
    }
}
