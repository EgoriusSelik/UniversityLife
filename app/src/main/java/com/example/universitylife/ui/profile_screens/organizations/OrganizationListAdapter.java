package com.example.universitylife.ui.profile_screens.organizations;

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

public class OrganizationListAdapter extends RecyclerView.Adapter<OrganizationListAdapter.ViewHolder>{
    private final LayoutInflater inflater;

    private final List<OrganizationItem> states;

    public OrganizationListAdapter(Context context, List<OrganizationItem> states) {
        this.inflater = LayoutInflater.from(context);
        this.states = states;
    }

    @NonNull
    @Override
    public OrganizationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.org_list_item, parent, false);
        return new OrganizationListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrganizationListAdapter.ViewHolder holder, int position) {
        OrganizationItem state = states.get(position);
        holder.imageOrg.setImageResource(state.getImage_of_org());
        holder.name_of_org.setText(state.getName_of_org());
        holder.description_of_org.setText(state.getDescription_of_org());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageOrg;
        final TextView name_of_org, description_of_org;
        ViewHolder(View view){
            super(view);
            imageOrg = view.findViewById(R.id.org_image);
            name_of_org = view.findViewById(R.id.org_name);
            description_of_org = view.findViewById(R.id.org_description);
        }
    }
}
