package akademia.assistant.front.genericRepo;

public class Main {
    public static void main(String[] args) {
        Repo<Product1> repo1 = new RepoImpl<>();
        repo1.add(new Product1());


        Repo<Product2> repo2 = new RepoImpl<>();
        repo2.add(new Product2());

        repo1.test();
        repo2.test();
    }
}
