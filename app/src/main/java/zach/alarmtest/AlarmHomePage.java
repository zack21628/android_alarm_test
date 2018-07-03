package zach.alarmtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AlarmHomePage extends AppCompatActivity {
    ListView list;
    AlarmAdapter adapter;
    public AlarmHomePage CustomListView = null;
    public ArrayList<AlarmModel> CustomListViewValues = new ArrayList<AlarmModel>();

    private AlarmViewModel alarmViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_alarm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmHomePage.super.getBaseContext(), CreateEditAlarmPage.class);
                //intent.putExtra()
                //startActivity(intent);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        list = (ListView) findViewById(R.id.list);
        CustomListView = this;
        // setListData();
        Resources res = getResources();

        adapter = new AlarmAdapter(CustomListView, CustomListViewValues, res);

        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        alarmViewModel.getAllAlarms().observe(this, new Observer<List<AlarmModelDb>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmModelDb> alarms) {
                adapter.setAlarms(alarms);
            }
        });
        list.setAdapter(adapter);
    }
    public void setListData() {
        Boolean a = true;
        for (int i = 0; i < 11; i++){
            final AlarmModel alarm_item = new AlarmModel();
            alarm_item.setTime("10:0" + i);
            alarm_item.setName("Alarm "+ i);
            alarm_item.setActive(a);
            a = !a;
            CustomListViewValues.add(alarm_item);
        }
    }

    public void onItemClick(int mPosition) {
        AlarmModel tempValues = (AlarmModel) CustomListViewValues.get(mPosition);
        Toast.makeText(CustomListView, "" + tempValues.getTime() + "Name:" + tempValues.getName()
                        + "Url:" + tempValues.getActive(), Toast.LENGTH_LONG).show();
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
            AlarmModelDb alarm = new AlarmModelDb(extraArray[0], extraArray[1], true);
            alarmViewModel.insert(alarm);
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.alarm_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}



