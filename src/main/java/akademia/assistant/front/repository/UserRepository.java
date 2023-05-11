package akademia.assistant.front.repository;

import akademia.assistant.front.model.Category;
import akademia.assistant.front.model.User;

import java.util.List;

public class UserRepository implements Repository {
    private List<User> users;

    @Override
    public String saveUsers(List<User> users) {
        return null;
    }

    @Override
    public List<User> loadUsers() {
        return null;
    }

    @Override
    public String updateCategoriesToFile(List<Category> categories) {
        return null;
    }

    @Override
    public List<Category> loadCategoriesFromFile() {
        return null;
    }
}
