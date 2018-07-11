package zach.alarmtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.Date;
import java.util.List;

public class AlarmHomePage extends AppCompatActivity {
    private AlarmViewModel mAlarmViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.add_alarm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmHomePage.super.getBaseContext(), CreateEditAlarmPage.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AlarmAdapter adapter = new AlarmAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAlarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        mAlarmViewModel.getAllAlarms().observe(this, new Observer<List<AlarmModelDb>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmModelDb> alarmModelDbs) {
                adapter.setAlarms(alarmModelDbs);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] extraArray = data.getStringArrayExtra(CreateEditAlarmPage.EXTRA_REPLY);
            String alarmTime = extraArray[0];
            String alarmName = extraArray[1];
            String displayTime = extraArray[2];
            Date alarmDate = parseAlarmTime(alarmTime);
            long time = alarmDate.getTime();
            AlarmModelDb alarm = new AlarmModelDb(time, displayTime, alarmName, true);
//            List<AlarmModelDb> allAlarms = mAlarmViewModel.getAllAlarms().getValue();
            mAlarmViewModel.insert(alarm);
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.alarm_not_saved, Toast.LENGTH_LONG).show();
        }
    }

    public Date parseAlarmTime(String time){
        Integer hour, minute;
        hour = Integer.parseInt(time.substring(0,2));
        minute = Integer.parseInt(time.substring(2));

        Date d = new Date();
        d.setHours(hour);
        d.setMinutes(minute);
        d.setSeconds(0);

        return d;
    }
}



