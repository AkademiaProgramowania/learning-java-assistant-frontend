package assistant.front.learningjavaassistantfrontend.repository;

import assistant.front.learningjavaassistantfrontend.model.Category;
import assistant.front.learningjavaassistantfrontend.model.User;

import java.util.List;

public interface Repository {
    String saveUsersToFile(List<User> users);

    List<User> loadUsersFromFile();

    String updateCategoriesToFile(List<Category> categories);

    List<Category> loadCategoriesFromFile();
}
