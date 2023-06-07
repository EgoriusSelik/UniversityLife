package com.example.universitylife.ui.profile_screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universitylife.R;
import com.example.universitylife.news_screens.NewsItem;

import org.w3c.dom.Text;

import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder> {

    interface OnStateClickListener{
        void onStateClick(ProfileListItem state, int position);
    }

    private final LayoutInflater inflater;
    private final List<ProfileListItem> states_of_profile_items;

    private final OnStateClickListener onClickListener;

    ProfileListAdapter(Context context, List<ProfileListItem> states, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.states_of_profile_items = states;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProfileListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.profile_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProfileListItem item = states_of_profile_items.get(position);
        holder.image_of_act.setImageResource(item.getImage_of_action());
        holder.name.setText(item.getName());

        // Обработка нажатия
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(item, position);
                if (position == 0){

                    final float scale = holder.item_cont.getResources().getDisplayMetrics().density;
                    final float scale2 = holder.item_cont2.getResources().getDisplayMetrics().density;
                    if (holder.add_panel.getVisibility() == View.GONE){
                        System.out.println("test");
                        holder.item_cont.getLayoutParams().height = (int)(186 * scale);
                        holder.item_cont2.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        holder.add_panel.setVisibility(View.VISIBLE);
                    }
                    else{
                        holder.item_cont.getLayoutParams().height = (int)(70 * scale);
                        holder.item_cont2.getLayoutParams().height = (int)(60 * scale2);
                        holder.add_panel.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView image_of_act;
        final TextView name;

        final ConstraintLayout add_panel;

        final ConstraintLayout item_cont;
        final ConstraintLayout item_cont2;

        final TextView toach;

        ViewHolder(View view){
            super(view);
            image_of_act = view.findViewById(R.id.imageachivment);
            name = view.findViewById(R.id.achivment);
            add_panel = view.findViewById(R.id.more_ach);
            item_cont = view.findViewById(R.id.item_container);
            item_cont2 = view.findViewById(R.id.constraintLayout2);
            toach = view.findViewById(R.id.go_to_ach_screen);
        }
    }
}
