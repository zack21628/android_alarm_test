package zach.alarmtest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    private List<AlarmModelDb> mAlarms;
    public Resources res;
    AlarmModel tempValues = null;
    int i = 0;

    public AlarmAdapter(Activity a, ArrayList d, Resources resLocal){
        this.activity = a;
        this.data = d;
        this.res = resLocal;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView alarm;
        public TextView alarm_name;
        public Switch active;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        View vi = convertView;

        if (convertView == null){
            vi = inflater.inflate(R.layout.alarm_custom_view, null);

            holder = new ViewHolder();
            holder.alarm = (TextView) vi.findViewById(R.id.alarm_text);
            holder.alarm_name = (TextView) vi.findViewById(R.id.alarm_name);
            holder.active = (Switch) vi.findViewById(R.id.active);

            vi.setTag(holder);
        }
        else {
            holder = (ViewHolder) vi.getTag();
        }

        if (data.size() <= 0){
            holder.alarm.setText("No Alarms Exist");
        }
        else {
            tempValues = null;
            tempValues = (AlarmModel) data.get(position);

            holder.alarm.setText( tempValues.getTime() );
            holder.alarm_name.setText( tempValues.getName() );
            holder.active.setChecked( tempValues.getActive() );

            vi.setOnClickListener( new OnItemClickListener(position) );
        }
        return vi;
    }

    @Override
    public void onClick(View v){
        Log.v("AlarmAdapter", "=====Row button clicked=====");
    }
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0){
            AlarmHomePage ah = (AlarmHomePage) activity;
            ah.onItemClick(mPosition);

        }
    }

    void setAlarms(List<AlarmModelDb> alarms) {
        mAlarms = alarms;
        notifyDataSetChanged();
    }
}
