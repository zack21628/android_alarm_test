package zach.alarmtest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "alarm_table")
public class AlarmModelDb {
    @PrimaryKey (autoGenerate = true)
    private int id ;

    @ColumnInfo (name = "time")
    private long time;

    @ColumnInfo (name= "display_time")
    private String displayTime;

    @ColumnInfo (name = "tf_hour")
    private String tfHour;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "active")
    private Boolean active;

    public AlarmModelDb(long time, String displayTime, String tfHour, String name, Boolean active) {
        this.id = 0;
        this.time = time;
        this.displayTime = displayTime;
        this.tfHour = tfHour;
        this. name = name;
        this.active = active;
    }

    public void setId(int id) { this.id = id; }

    public void setTime(long time) { this.time = time; }

    public void setDisplayTime(String displayTime) { this.displayTime = displayTime; }

    public void setName(String name) { this.name = name; }

    public void setActive(Boolean active) { this.active = active; }

    public int getId() { return this.id; }

    public long getTime() {
        return this.time;
    }

    public String getDisplayTime() { return this.displayTime; }

    public String getTfHour() { return this.tfHour; }

    public String getName() {
        return this.name;
    }

    public Boolean getActive() {
        return this.active;
    }

}
