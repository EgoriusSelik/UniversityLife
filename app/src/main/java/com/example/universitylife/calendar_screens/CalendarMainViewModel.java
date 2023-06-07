package com.example.universitylife.calendar_screens;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universitylife.model.EventsListRepository;

import java.util.Map;

public class CalendarMainViewModel extends ViewModel implements EventsListRepository.onFirestoreTaskComplete {

    private MutableLiveData<Map<String, Object>> eventsListLiveData = new MutableLiveData<>();

    public MutableLiveData<Map<String, Object>> getEventsListLiveData() {
        return eventsListLiveData;
    }

    private EventsListRepository repository = new EventsListRepository(this);

    public CalendarMainViewModel(){
        repository.getNewsData();
    }

    @Override
    public void EventsDataLoaded(Map<String, Object> events_of_user) {
        eventsListLiveData.setValue(events_of_user);
    }

    @Override
    public void onError(Exception e) {
        System.out.println("Error load dates" + e.getMessage());
    }
}