package com.example.universitylife.news_screens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.universitylife.R;
import com.example.universitylife.model.NewsEntity;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(NewsEntity state, int position);
    }

    private final LayoutInflater inflater;

    private List<NewsEntity> states;
    public void setStates(List<NewsEntity> states) {
        this.states = states;
    }



    private final OnStateClickListener onClickListener;
    NewsListAdapter(Context context, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.news_item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, int position) {
        NewsEntity model = states.get(position);
        holder.name_of_mero.setText(model.getName_of_mero());
        holder.description.setText(model.getShort_description_of_mero());
        holder.date_and_place.setText(model.getDate_place_of_mero());
        Glide.with(holder.itemView).load(model.getImage_of_mero()).into(holder.imageMeroView);

        // Обработка нажатия
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(model, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (states == null){
            return 0;
        }
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageMeroView;
        final TextView name_of_mero, description, date_and_place, long_description;
        ViewHolder(View view){
            super(view);
            imageMeroView = view.findViewById(R.id.image_of_mero);
            name_of_mero = view.findViewById(R.id.mero_name);
            description = view.findViewById(R.id.description);
            date_and_place = view.findViewById(R.id.date_place);
            long_description = view.findViewById(R.id.long_description_sheet);
        }
    }
}
