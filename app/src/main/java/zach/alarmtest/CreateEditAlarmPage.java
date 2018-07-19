package zach.alarmtest;

import android.content.res.Resources;
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
    public static final String EXTRA_REPLY = "new_alarm";

    private String new_alarm_time, alarm_hour_tf_time;
    String displayTime, displayName, displayHour, displayMinute, displayAmPm, displayTf;
    private Boolean existing_alarm = false;
    private Boolean timepicker_has_been_opened = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_alarm_page);
        Intent intent = getIntent();
        final int alarmId = intent.getIntExtra("id", -1);
        displayTime = displayName = displayHour = displayMinute = displayAmPm = displayTf = "";
        if (alarmId > -1) {
            existing_alarm = true;
            displayName = intent.getStringExtra("alarm_name");
            displayTime = intent.getStringExtra("display");
            displayTf = intent.getStringExtra("tf_hour");
            displayHour = displayTime.substring(0, 2);
            displayMinute = displayTime.substring(3, 5);
            displayAmPm = displayTime.substring(5);
        }

        final TextView alarm_hour = findViewById(R.id.alarm_hour);
        final TextView alarm_minute = findViewById(R.id.alarm_minute);
        final TextView alarm_am_pm = findViewById(R.id.alarm_am_pm);
        final TextView alarm_hour_tf = findViewById(R.id.alarm_hour_tf);
        final TextView alarm_name = findViewById(R.id.new_alarm_name);
        alarm_hour.setOnClickListener(openPickerListener);
        alarm_minute.setOnClickListener(openPickerListener);
        alarm_am_pm.setOnClickListener(openPickerListener);

        if (existing_alarm) {
            alarm_hour.setText(displayHour);
            alarm_minute.setText(displayMinute);
            alarm_am_pm.setText(displayAmPm);
            alarm_hour_tf.setText(displayTime);
            alarm_name.setText(displayName);
        }

        final Button saveButton = findViewById(R.id.save_id_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_alarm_time = alarm_hour_tf.getText().toString() +
                        alarm_minute.getText().toString();
                alarm_hour_tf_time = alarm_hour.getText().toString() + ":" +
                        alarm_minute.getText().toString() + " " + alarm_am_pm.getText().toString();
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(new_alarm_time)) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String name = alarm_name.getText().toString();
                    replyIntent.putExtra("alarm_time", new_alarm_time);
                    replyIntent.putExtra("alarm_name", name);
                    replyIntent.putExtra("display_time", alarm_hour_tf_time);
                    replyIntent.putExtra("existing_alarm", existing_alarm);
                    if (existing_alarm) {
                        replyIntent.putExtra("alarm_id", alarmId);
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                timepicker_has_been_opened = false;
                finish();
            }
        });
    }
    public void showTimePickerDialog(View v) {
        AlarmPickerFragment newFragment = new AlarmPickerFragment();
        Bundle editBundle = new Bundle();
        editBundle.putString("alarm_hour", displayHour);
        editBundle.putString("alarm_minute", displayMinute);
        editBundle.putString("alarm_tf", displayTf);
        editBundle.putBoolean("already_opened", timepicker_has_been_opened);
        newFragment.setArguments(editBundle);
        if (this.existing_alarm)
            newFragment.show(getSupportFragmentManager(), getResources().getString(R.string.edit_timepicker));
        else
            newFragment.show(getSupportFragmentManager(), getResources().getString(R.string.new_timepicker));
        timepicker_has_been_opened = true;
    }
    View.OnClickListener openPickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showTimePickerDialog(v);
        }
    };
}
