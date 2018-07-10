package zach.alarmtest;

import android.text.InputFilter;
import android.text.Spanned;

public class TimeFilter implements InputFilter {
    private int mMin, mMax;

    public TimeFilter(int min, int max) {
        this.mMin= min;
        this.mMax = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
        String inputString = dest.toString() + source.toString();
        int size = inputString.length();
        int input = Integer.parseInt(inputString);
        if (isInRange(mMin, mMax, input)) {
            return null;
        }
        else {
            return "";
        }
    }

    private boolean isInRange(int min, int max, int input) {
        if (min > max) {
            return false;
        }
        else {
            return (input >= min && input <= max);
        }
    }
}
