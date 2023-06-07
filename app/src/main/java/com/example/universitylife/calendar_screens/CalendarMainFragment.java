package com.example.universitylife.calendar_screens;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.universitylife.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class CalendarMainFragment extends Fragment {

    private List<CalendarListItem> states = new ArrayList<CalendarListItem>();

    Map<String, Object> user_events;
    private String dateFormatted;

    private CalendarMainViewModel calendarMainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendarMainViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(CalendarMainViewModel.class);

    }

    public static CalendarMainFragment newInstance() {
        return new CalendarMainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_calendar_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        dateFormatted = fmt.format(calendar.getTime());
        System.out.println(dateFormatted);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewcalendar);
        CalendarListAdapter calendarListAdapter = new CalendarListAdapter(getContext());
        recyclerView.setAdapter(calendarListAdapter);

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                GregorianCalendar calendar = new GregorianCalendar( i, i1, i2 );
                SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
                view.findViewById(R.id.textView3).setVisibility(View.VISIBLE);
                dateFormatted = fmt.format(calendar.getTime());
                if (user_events != null){
                    states.clear();
                    for (String value : user_events.keySet()) {
                        if(value.split("/")[1].startsWith(dateFormatted)){
                            view.findViewById(R.id.textView3).setVisibility(View.GONE);
                            String[] buff = value.split("/");
                            states.add(new CalendarListItem(buff[0], buff[1]));
                        }
                    }
                    calendarListAdapter.setStates(states);
                    calendarListAdapter.notifyDataSetChanged();
                }
            }
        });

        calendarMainViewModel.getEventsListLiveData().observe(getViewLifecycleOwner(), new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> stringObjectMap) {
                if (stringObjectMap != null){
                    user_events = stringObjectMap;
                }
            }
        });

        if (user_events != null){
            for (String value : user_events.keySet()) {
                if(value.split("/")[1].startsWith(dateFormatted)){
                    view.findViewById(R.id.textView3).setVisibility(View.GONE);
                    String[] buff = value.split("/");
                    states.add(new CalendarListItem(buff[0], buff[1]));
                }
            }

            calendarListAdapter.setStates(states);
            calendarListAdapter.notifyDataSetChanged();
        }

    }
}