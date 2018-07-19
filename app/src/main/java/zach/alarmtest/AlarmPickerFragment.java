package zach.alarmtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Locale;

public class AlarmPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Boolean already_opened = getArguments().getBoolean("already_opened", false);
        int defHour = 12;
        int defMinute = 0;
        String tag = this.getTag();
        if (tag.equals(getResources().getString(R.string.edit_timepicker)) && !already_opened) {
            String h = getArguments().getString("alarm_tf");
            String m = getArguments().getString("alarm_minute");
            int hour = Integer.parseInt(h);
            int minute = Integer.parseInt(m);
            defHour = hour;
            defMinute = minute;
        }
        return new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, defHour,  defMinute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String m = String.format(Locale.getDefault(), "%02d", minute);
        String tf = String.format(Locale.getDefault(), "%02d", hourOfDay);
        String am = "AM";
        int hour_formatted;
        if (hourOfDay > 12) {
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
        TextView alarmHourOfDay = getActivity().findViewById(R.id.alarm_hour_tf);
        alarmMinute.setText(m);
        alarmHour.setText(h);
        alarmAmPm.setText(am);
        alarmHourOfDay.setText(tf);
    }
}
