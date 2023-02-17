package assistant.front.learningjavaassistantfrontend;

import java.util.List;

public interface Repository {
    String saveUsersToFile(List<User> users);

    List<User> loadUsersFromFile();

    String updateCategoriesToFile(List<Category> categories);

    List<Category> loadCategoriesFromFile();
}
