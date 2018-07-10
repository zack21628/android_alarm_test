package zach.alarmtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Locale;

public class AlarmPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, 12, 0,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String m = String.format(Locale.getDefault(), "%02d", minute);
        String am = "AM";
        int hour_formatted;
        if (hourOfDay > 11) {
            am = "PM";
            hour_formatted = hourOfDay - 12;
        }
        else {
            hour_formatted = hourOfDay;
        }
        String h = String.format(Locale.getDefault(), "%02d", hour_formatted);
        TextView alarmMinute = getActivity().findViewById(R.id.alarm_minute);
        TextView alarmHour = getActivity().findViewById(R.id.alarm_hour);
        TextView alarmAmPm = getActivity().findViewById(R.id.alarm_am_pm);
        alarmMinute.setText(m);
        alarmHour.setText(h);
        alarmAmPm.setText(am);
    }
}
