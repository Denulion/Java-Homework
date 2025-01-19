package algorithms;

public interface List<E> extends Iterable<E> {

     void insert(int index, E value);

     void add(E value);

     E delete(int index);

     boolean delete(E value);

     void clear();

     E set(int index, E value);

     E get(int index);

     int indexOf(E value);

     boolean contains(E value);

     int size();

     boolean isEmpty();

}
