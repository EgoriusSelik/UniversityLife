package com.example.universitylife.news_screens;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universitylife.model.NewsEntity;
import com.example.universitylife.model.NewsListRepository;

import java.util.List;

public class NewsMainViewModel extends ViewModel implements NewsListRepository.onFirestoreTaskComplete {
    private MutableLiveData<List<NewsEntity>> newsListLiveData = new MutableLiveData<>();

    public MutableLiveData<List<NewsEntity>> getNewsListLiveData() {
        return newsListLiveData;
    }

    private NewsListRepository repository = new NewsListRepository(this);

    public NewsMainViewModel(){
        repository.getNewsData();
    }

    @Override
    public void newsDataLoaded(List<NewsEntity> newsListFirebaseModels) {
        newsListLiveData.setValue(newsListFirebaseModels);
    }

    @Override
    public void onError(Exception e) {
        System.out.println("Error load news" + e.getMessage());
    }
}