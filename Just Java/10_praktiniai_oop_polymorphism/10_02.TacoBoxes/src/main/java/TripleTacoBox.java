public class TripleTacoBox implements TacoBox{
    private int tacos;

    public TripleTacoBox(){
        this.tacos = 3;
    }

    @Override
    public int tacosRemaining() {
        return this.tacos;
    }

    @Override
    public void eat() {
        if(tacos > 0) {
            tacos -= 1;
        }else {
            System.out.println("No tacos");
        }
    }
}
