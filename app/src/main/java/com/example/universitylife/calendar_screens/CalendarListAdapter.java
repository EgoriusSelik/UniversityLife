package com.example.universitylife.calendar_screens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.universitylife.R;
import com.example.universitylife.model.NewsEntity;

import java.util.List;

public class CalendarListAdapter extends RecyclerView.Adapter<CalendarListAdapter.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(NewsEntity state, int position);
    }

    private final LayoutInflater inflater;

    private List<CalendarListItem> states;
    public void setStates(List<CalendarListItem> states) {
        this.states = states;
    }
    CalendarListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public CalendarListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.calendar_list_item, parent, false);
        return new CalendarListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarListAdapter.ViewHolder holder, int position) {
        CalendarListItem calendarListItem = states.get(position);
        holder.name_of_mero.setText(calendarListItem.getName());
        holder.date_and_place.setText(calendarListItem.getDate_and_place());
    }

    @Override
    public int getItemCount() {
        if (states == null){
            return 0;
        }
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name_of_mero, date_and_place;
        ViewHolder(View view){
            super(view);
            name_of_mero = view.findViewById(R.id.name_of_notify_mero);
            date_and_place = view.findViewById(R.id.date_and_place_of_mero);
        }
    }
}
