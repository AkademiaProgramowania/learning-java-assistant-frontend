package akademia.assistant.front.repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl<T> implements Repository<T> {
    private final List<T> elements = new ArrayList<>();

    @Override
    public void addElement(T element) {
        elements.add(element);
    }

    @Override
    public List<T> getElements() {
        return elements;
    }
}
