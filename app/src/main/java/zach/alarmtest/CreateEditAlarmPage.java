package zach.alarmtest;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateEditAlarmPage extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private AlarmModelDb editAlarm;
    private String newAlarmTime;
//    private TextView newAlarmHour, newAlarmMinute, newAlarmAmPm;
    private EditText newAlarmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_alarm_page);
//        newAlarmHour = findViewById(R.id.alarm_hour);
//        newAlarmMinute = findViewById(R.id.alarm_minute);
//        newAlarmAmPm = findViewById(R.id.alarm_am_pm);
        newAlarmName = findViewById(R.id.new_alarm_name);

        final TextView alarm_hour = findViewById(R.id.alarm_hour);
        final TextView alarm_minute = findViewById(R.id.alarm_minute);
        final TextView alarm_am_pm = findViewById(R.id.alarm_am_pm);
        alarm_hour.setOnClickListener(openPickerListener);
        alarm_minute.setOnClickListener(openPickerListener);
        alarm_am_pm.setOnClickListener(openPickerListener);

        final Button saveButton = findViewById(R.id.save_id_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAlarmTime = alarm_hour.getText().toString() + ":" + alarm_minute.getText().toString() + alarm_am_pm.getText().toString();
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(newAlarmTime)) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else { ;
                    String name = newAlarmName.getText().toString();
                    String[] extras = new String[]{newAlarmTime, name};
                    replyIntent.putExtra(EXTRA_REPLY, extras);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new AlarmPickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    View.OnClickListener openPickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showTimePickerDialog(v);
        }
    };
}
