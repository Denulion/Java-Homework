public class Room {
    private String code;
    private int seats;

    public Room(String code, int seats){
        this.code = code;
        this.seats = seats;
    }
    public void setCode(String code){
        this.code = code;
    }
    public void setSeats(int seats){
        this.seats = seats;
    }
    public String getCode(){
        return code;
    }
    public int getSeats(){
        return seats;
    }
    public void setRoom(String code, int seats){
        setCode(code);
        setSeats(seats);
    }
    public String toString(){
        return "Room[code= " + code + " seats= " + seats + "]";
    }
}
