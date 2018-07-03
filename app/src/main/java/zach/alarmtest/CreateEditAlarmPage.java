package zach.alarmtest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEditAlarmPage extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private AlarmModelDb editAlarm;

    private EditText newAlarmTime;
    private EditText newAlarmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_alarm_page);
        newAlarmTime = findViewById(R.id.new_alarm_time);
        newAlarmName = findViewById(R.id.new_alarm_name);

        final Button saveButton = findViewById(R.id.save_id_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(newAlarmTime.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String time = newAlarmTime.getText().toString();
                    String name = newAlarmName.getText().toString();
                    String[] extras = new String[]{time, name};
                    replyIntent.putExtra(EXTRA_REPLY, extras);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}
