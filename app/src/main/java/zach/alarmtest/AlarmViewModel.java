package zach.alarmtest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AlarmViewModel extends AndroidViewModel {
    private AlarmRepository mRepo;
    private LiveData<List<AlarmModelDb>> mAllAlarms;

    public AlarmViewModel(Application application){
        super(application);
        mRepo = new AlarmRepository(application);
        mAllAlarms = mRepo.getAllAlarms();
    }

    LiveData<List<AlarmModelDb>> getAllAlarms() {
        return mAllAlarms;
    }

    public void insert(AlarmModelDb alarm){
        mRepo.insert(alarm);
    }

    public void delete(AlarmModelDb alarm) { mRepo.delete(alarm); }

    public void deleteAll() { mRepo.deleteAll(); }

}
