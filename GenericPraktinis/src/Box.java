public class Box<T extends Comparable<T>> {
    private T item1;
    private T item2;
    private boolean locked;

    public Box(T item1, T item2) {
        if (item1.compareTo(item2) > 0){
            this.item1 = item2;
            this.item2 = item1;
        }else{
            this.item1 = item1;
            this.item2 = item2;
        }
    }

    public T getItem1() {
        if(this.locked){
            throw new IllegalStateException("Box is locked!");
        }
        return this.item1;
    }

    public T getItem2() {
        if(this.locked){
            throw new IllegalStateException("Box is locked!");
        }
        return this.item2;
    }
    public void lock(){
        this.locked=true;
    }
    public void unlock(){
        this.locked=false;
    }
    public boolean isLocked(){
        return this.locked;
    }
    @Override
    public String toString(){
        return "Box{" + "item1=" + this.item1 + ", item2=" + this.item2 + ", locked=" + this.locked + "}";
    }
}
