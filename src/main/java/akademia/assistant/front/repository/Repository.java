package akademia.assistant.front.repository;

import java.util.List;

public interface Repository<T> {

    void addElement(T element);

    List<T> getElements();
}
