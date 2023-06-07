package com.example.universitylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.universitylife.calendar_screens.CalendarMainFragment;
import com.example.universitylife.news_screens.NewsMainFragment;
import com.example.universitylife.news_screens.NewsMainViewModel;
import com.example.universitylife.registration.LoginFragment;
import com.example.universitylife.ui.profile_screens.ProfileMainFragment;
import com.example.universitylife.ui.profile_screens.ProfileMainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ProfileMainViewModel profileMainViewModel;
    private NewsMainViewModel newsMainViewModel;

    @SuppressLint({"NonConstantResourceId", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Портретная ориентация
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        newsMainViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(NewsMainViewModel.class);

        // Добавляем меню навигации
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setVisibility(View.GONE);
        // Устанавливаем переходы

        nav.setOnItemSelectedListener(item -> {
            if (Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination()
            == Navigation.findNavController(this, R.id.nav_host_fragment).findDestination(R.id.newsMainFragment)){
                switch (item.getItemId()){
                    case R.id.news:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_newsMainFragment_self);
                        break;
                    case R.id.calendar:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_newsMainFragment_to_calendarMainFragment);
                        break;
                    case R.id.profile:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_newsMainFragment_to_profileMainFragment);
                        break;
                }
            }

            if (Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination()
                    == Navigation.findNavController(this, R.id.nav_host_fragment).findDestination(R.id.calendarMainFragment)){
                switch (item.getItemId()){
                    case R.id.news:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_calendarMainFragment_to_newsMainFragment);
                        break;
                    case R.id.calendar:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_calendarMainFragment_self);
                        break;
                    case R.id.profile:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_calendarMainFragment_to_profileMainFragment);
                        break;
                }
            }

            if (Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination()
                    == Navigation.findNavController(this, R.id.nav_host_fragment).findDestination(R.id.profileMainFragment)){
                switch (item.getItemId()){
                    case R.id.news:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_profileMainFragment_to_newsMainFragment);
                        break;
                    case R.id.calendar:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_profileMainFragment_to_calendarMainFragment);
                        break;
                    case R.id.profile:
                        Navigation.findNavController(this, R.id.nav_host_fragment).
                                navigate(R.id.action_profileMainFragment_self);
                        break;
                }
            }
            return true;
        });


        // Получение доступа к уведомлениям
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
        // Получение доступа к записи в хранилище
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        // Получение доступа к чтению хранилища
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
        }
        // Получение доступа к интернету
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.INTERNET}, 4);
        }
    }
}
