package zach.alarmtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        private final TextView time, name;
        private final Switch active;

        private AlarmViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.alarm_text);
            name = itemView.findViewById(R.id.alarm_name);
            active = itemView.findViewById(R.id.active);
        }
    }

    private final LayoutInflater mInflater;
    private List<AlarmModelDb> mAlarms;

    AlarmAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.alarm_custom_view, parent, false);
        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        if (mAlarms != null) {
            AlarmModelDb current = mAlarms.get(position);
            holder.name.setText(current.getName());
            holder.time.setText(current.getDisplayTime());
            holder.active.setChecked(current.getActive());
        }
        else {
            holder.name.setText("No Alarms");
        }
    }

    @Override
    public int getItemCount(){
        if (mAlarms != null) {
            return mAlarms.size();
        }
        else return 0;
    }

    void setAlarms(List<AlarmModelDb> alarms) {
        mAlarms = alarms;
        notifyDataSetChanged();
    }
}
