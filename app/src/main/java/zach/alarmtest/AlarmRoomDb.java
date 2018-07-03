package zach.alarmtest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {AlarmModelDb.class }, version = 1)
public abstract class AlarmRoomDb extends RoomDatabase {
    public abstract AlarmDao alarmDao();

    private static AlarmRoomDb INSTANCE;

    static AlarmRoomDb getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (AlarmRoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlarmRoomDb.class, "alarm_databse")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}