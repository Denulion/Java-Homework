package algorithms;

public interface Iterator<E> {

    void first();

    void last();

    boolean isDone();

    void next();

    void previous();

    E current();

}
