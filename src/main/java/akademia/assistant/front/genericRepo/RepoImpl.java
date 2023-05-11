package akademia.assistant.front.genericRepo;

import java.util.ArrayList;
import java.util.List;

public class RepoImpl<T> implements Repo<T>{

    private List<T> elements = new ArrayList<>();

    @Override
    public void add(T element) {
        elements.add(element);
    }

    @Override
    public List<T> getElements() {
        return elements;
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
