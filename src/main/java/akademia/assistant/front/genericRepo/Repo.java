package akademia.assistant.front.genericRepo;

import java.util.List;

public interface Repo<T> {

    void add(T element);
    List<T> getElements();

    void test();
}
