package zach.alarmtest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "alarm_table")
public class AlarmModelDb {
    @PrimaryKey (autoGenerate = true)
    private int id ;

    @NonNull
    @ColumnInfo (name = "time")
    private String time;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "active")
    private Boolean active;

    public AlarmModelDb(String time, String name, Boolean active) {
        this.time = time;
        this. name = name;
        this.active = active;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return this.id; }

    public String getTime() {
        return this.time;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getActive() {
        return this.active;
    }

}
