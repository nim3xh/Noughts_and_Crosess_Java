package noughts_and_crosess;

public class Player<T> {
    private final T name;

    public Player(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }
}
