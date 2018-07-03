package zach.alarmtest;


public class AlarmModel {
    String time;
    String name;
    Boolean active;


    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }
    public void setTime(String t) {
        this.time = t;
    }
    public void setName(String n) {
        this.name = n;
    }
    public void setActive(Boolean a) {
        this.active = a;
    }
}
