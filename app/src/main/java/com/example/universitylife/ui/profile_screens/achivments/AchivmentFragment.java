package com.example.universitylife.ui.profile_screens.achivments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.universitylife.R;
import com.example.universitylife.news_screens.NewsItem;
import com.example.universitylife.news_screens.NewsListAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class AchivmentFragment extends Fragment {

    ArrayList<AchivmentItem> states = new ArrayList<AchivmentItem>();

    public AchivmentFragment() {
        // Required empty public constructor
    }
    public static AchivmentFragment newInstance(String param1, String param2) {
        AchivmentFragment fragment = new AchivmentFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achimvent_list, container, false);

        setInitialData();
        RecyclerView recyclerView = view.findViewById(R.id.achivment_list);
        AchivmentListAdapter achivmentListAdapter = new AchivmentListAdapter(getContext(), states);
        recyclerView.setAdapter(achivmentListAdapter);

        ImageView go_back = view.findViewById(R.id.go_back_to_profile);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_achivmentFragment_to_profileMainFragment);
            }
        });



        return view;
    }

    private void setInitialData(){
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
        states.add(new AchivmentItem("Инженер-наставник", "Поучаствуй в проведении инженерных эстафет", R.drawable.four));
    }
}