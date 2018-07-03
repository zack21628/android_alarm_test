package zach.alarmtest;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface AlarmDao {
    @Insert
    void insert(AlarmModelDb alarm);

    @Delete
    void delete(AlarmModelDb alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Query("SELECT * FROM alarm_table ORDER BY time ASC")
    LiveData<List<AlarmModelDb>> getAllAlarms();

}
