package com.example.universitylife.ui.profile_screens.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universitylife.R;
import com.example.universitylife.ui.profile_screens.achivments.AchivmentItem;
import com.example.universitylife.ui.profile_screens.achivments.AchivmentListAdapter;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder>{
    private final LayoutInflater inflater;

    private final List<HistoryItem> states;

    public HistoryListAdapter(Context context, List<HistoryItem> states) {
        this.inflater = LayoutInflater.from(context);
        this.states = states;
    }

    @NonNull
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.history_list_item, parent, false);
        return new HistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryListAdapter.ViewHolder holder, int position) {
        HistoryItem state = states.get(position);
        holder.image_of_mero.setImageResource(state.getImage_of_mero());
        holder.name_of_mero.setText(state.getName_of_mero());
        holder.date_of_mero.setText(state.getDate_of_mero());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView image_of_mero;
        final TextView name_of_mero, date_of_mero;
        ViewHolder(View view){
            super(view);
            image_of_mero = view.findViewById(R.id.image_of_merohist);
            name_of_mero = view.findViewById(R.id.name_of_merohist);
            date_of_mero = view.findViewById(R.id.date_of_merohist);
        }
    }
}

