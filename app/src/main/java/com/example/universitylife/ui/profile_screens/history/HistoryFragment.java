package com.example.universitylife.ui.profile_screens.history;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.universitylife.R;
import com.example.universitylife.ui.profile_screens.achivments.AchivmentItem;
import com.example.universitylife.ui.profile_screens.achivments.AchivmentListAdapter;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    ArrayList<HistoryItem> states = new ArrayList<HistoryItem>();
    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);

        setInitialData();
        RecyclerView recyclerView = view.findViewById(R.id.history_list);
        HistoryListAdapter historyListAdapter = new HistoryListAdapter(getContext(), states);
        recyclerView.setAdapter(historyListAdapter);

        ImageView go_back = view.findViewById(R.id.go_back_to_profile2);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_historyFragment_to_profileMainFragment);
            }
        });

        return view;
    }

    private void setInitialData(){
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
        states.add(new HistoryItem("День студента", "27.03.2023", R.drawable.accountbox));
    }
}