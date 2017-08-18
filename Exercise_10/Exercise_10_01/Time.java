public class Time {
    int hour, minute, second;

    public Time() {
        this(System.currentTimeMillis());
    }

    public Time(long elapsedTime) {
        setTime(elapsedTime);
    }

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void setTime(long elapsedTime) {
        long secs = elapsedTime / 1000;
        second = (int) (secs % 60);
        minute = (int) ((secs / 60) % 60);
        hour = (int) (secs / 3600) % 24;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("%2d:%2d:%2d", hour, minute, second);
    }
}
