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
    // private String time;
    private long time;

    @ColumnInfo (name= "display_time")
    private String displayTime;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "active")
    private Boolean active;

    public AlarmModelDb(long time, String displayTime, String name, Boolean active) {
        this.id = 0;
        this.time = time;
        this.displayTime = displayTime;
        this. name = name;
        this.active = active;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return this.id; }

    public long getTime() {
        return this.time;
    }

    public String getDisplayTime() {return this.displayTime; }

    public String getName() {
        return this.name;
    }

    public Boolean getActive() {
        return this.active;
    }

}
