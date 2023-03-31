package assistant.front.learningjavaassistantfrontend.repository;

import assistant.front.learningjavaassistantfrontend.model.Category;
import assistant.front.learningjavaassistantfrontend.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinFileRepository implements Repository {
    @Override
    public String saveUsersToFile(List<User> users) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"));
            oos.writeObject(users);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Users have been saved.";
    }

    @Override
    public List<User> loadUsersFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"));
            List<User> users = (List<User>) ois.readObject();
            ois.close();
            return users;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String updateCategoriesToFile(List<Category> categories) {
        try {
            new File("categories.bin").delete();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("categories.bin"));
            oos.writeObject(categories);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Users have been saved.";
    }

    @Override
    public List<Category> loadCategoriesFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("categories.bin"));
            List<Category> categories = (List<Category>) ois.readObject();
            ois.close();
            return categories;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
