package zach.alarmtest;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AlarmRepository {
    private AlarmDao mAlarmDao;
    private LiveData<List<AlarmModelDb>> mAllAlarms;

    AlarmRepository(Application application){
        AlarmRoomDb db = AlarmRoomDb.getDatabase(application);
        mAlarmDao = db.alarmDao();
        mAllAlarms = mAlarmDao.getAllAlarms();
    }

    LiveData<List<AlarmModelDb>> getAllAlarms(){
        return mAllAlarms;
    }

    public void insert(AlarmModelDb alarm){
        new insertAsyncTask(mAlarmDao).execute(alarm);
    }

    private static class insertAsyncTask extends AsyncTask<AlarmModelDb, Void, Void> {
        private AlarmDao mAsyncTaskDao;

        insertAsyncTask(AlarmDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AlarmModelDb... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update(AlarmModelDb alarm) { new updateAsyncTask(mAlarmDao).execute(alarm); }

    private static class updateAsyncTask extends AsyncTask<AlarmModelDb, Void, Void> {
        private AlarmDao mAsyncTaskDao;

        updateAsyncTask(AlarmDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final AlarmModelDb... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void delete(AlarmModelDb alarm) {
        new deleteAsyncTask(mAlarmDao).execute(alarm);
    }

    public void deleteAll() {
        List<AlarmModelDb> allAlarms = mAllAlarms.getValue();
        for (AlarmModelDb alarm : allAlarms) {
            new deleteAsyncTask(mAlarmDao).execute(alarm);
        }
    }

    private static class deleteAsyncTask extends AsyncTask<AlarmModelDb, Void, Void> {
        private AlarmDao mAsyncTaskDao;

        deleteAsyncTask(AlarmDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AlarmModelDb... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
