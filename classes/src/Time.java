public class Time {
    private int second;
    private int minute;
    private int hour;

    public Time(int second, int minute, int hour) {
        setSecond(second);
        setMinute(minute);
        setHour(hour);
    }

    public void setSecond(int second) {
        if (second >= 0 && second <= 59) {
            this.second = second;
        } else {
            System.out.println("Error");
        }
    }

    public void setMinute(int minute) {
        if (minute >= 0 && minute <= 59) {
            this.minute = minute;
        } else {
            System.out.println("Error");
        }
    }

    public void setHour(int hour) {
        if (hour >= 0 && hour <= 23) {
            this.hour = hour;
        } else {
            System.out.println("Error");
        }
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public void setTime(int second, int minute, int hour) {
        setSecond(second);
        setMinute(minute);
        setHour(hour);
    }

    public Time nextSecond() {
        setSecond(second + 1);
        if (second == 60) {
            setSecond(0);
            setMinute(minute + 1);
        }
        if (minute == 60) {
            setMinute(0);
            setHour(hour + 1);
        }
        if (hour == 24) {
            setHour(0);
        }
        return this;
    }
}
