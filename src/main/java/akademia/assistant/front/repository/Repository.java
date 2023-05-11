package akademia.assistant.front.repository;

import akademia.assistant.front.model.User;
import akademia.assistant.front.model.Category;

import java.util.List;

public interface Repository {
    String saveUsers(List<User> users);

    List<User> loadUsers();

    String updateCategoriesToFile(List<Category> categories);

    List<Category> loadCategoriesFromFile();
}
