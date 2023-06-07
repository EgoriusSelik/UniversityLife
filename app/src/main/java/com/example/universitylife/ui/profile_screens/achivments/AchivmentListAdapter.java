package com.example.universitylife.ui.profile_screens.achivments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universitylife.R;

import java.util.List;

public class AchivmentListAdapter extends RecyclerView.Adapter<AchivmentListAdapter.ViewHolder> {

    private final LayoutInflater inflater;

    private final List<AchivmentItem> states;

    public AchivmentListAdapter(Context context, List<AchivmentItem> states) {
        this.inflater = LayoutInflater.from(context);
        this.states = states;
    }

    @NonNull
    @Override
    public AchivmentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.achivment_list_item, parent, false);
        return new AchivmentListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AchivmentListAdapter.ViewHolder holder, int position) {
        AchivmentItem state = states.get(position);
        holder.imageAch.setImageResource(state.getImage_of_ach());
        holder.name_of_ach.setText(state.getName_of_ach());
        holder.description_of_ach.setText(state.getDescription_of_ach());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageAch;
        final TextView name_of_ach, description_of_ach;
        ViewHolder(View view){
            super(view);
            imageAch = view.findViewById(R.id.ach_image_l);
            name_of_ach = view.findViewById(R.id.ach_name);
            description_of_ach = view.findViewById(R.id.ach_description);
        }
    }
}
