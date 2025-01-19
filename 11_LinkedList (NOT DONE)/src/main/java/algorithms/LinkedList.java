package algorithms;

public class LinkedList<E> implements List<E> {

    private int size;

    private final Element<E> headAndTail;

    public LinkedList() {
        size = 0;
        headAndTail = new Element<>(null);
        clear();
    }

    @Override
    public void insert(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Element<E> element = new Element<>(value);
        element.attachBefore(getElement(index));
        ++size;
    }

    private Element<E> getElement(int index) {
        Element<E> element = headAndTail.getNext();

        for (int i = 0; i < index; ++i) {
            element = element.getNext();
        }

        return element;
    }

    @Override
    public void add(E value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public E delete(int index) {
        checkOutBounds(index);

        Element<E> element = getElement(index);
        element.detach();

        --size;

        return element.getValue();
    }

    @Override
    public boolean delete(E value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public void clear() {
        headAndTail.setNext(headAndTail);
        headAndTail.setPrev(headAndTail);
        size = 0;
    }

    @Override
    public E set(int index, E value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int indexOf(E value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public boolean contains(E value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Implement me");
    }

    private void checkOutBounds(int index) {
        if (isOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    private static final class Element<E> {
        private E value;

        private Element<E> next;

        private Element<E> prev;

        public Element(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element<E> getNext() {
            return next;
        }

        public void setNext(Element<E> next) {
            this.next = next;
        }

        public Element<E> getPrev() {
            return prev;
        }

        public void setPrev(Element<E> prev) {
            this.prev = prev;
        }

        public void attachBefore(Element<E> next) {
            setNext(next);
            setPrev(next.getPrev());

            next.setPrev(this);
            getPrev().setNext(this);
        }

        public void detach() {
            getPrev().setNext(getNext());
            getNext().setPrev(getPrev());
        }
    }

}
