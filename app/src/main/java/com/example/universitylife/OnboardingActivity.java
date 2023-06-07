package com.example.universitylife;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import com.example.universitylife.databinding.ActivityMainBinding;
import com.example.universitylife.onboarding.MyPagerAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.universitylife.databinding.ActivityOnboardingBinding;
import com.google.android.material.tabs.TabLayout;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager2 vpOnboarding;
    ActivityOnboardingBinding binding;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Портретная ориентация
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vpOnboarding = findViewById(R.id.pager_android);
        FragmentStateAdapter pageAdapter = new MyPagerAdapter(this);
        binding.pagerAndroid.setAdapter(pageAdapter);

        binding.indicator.setSliderColor(Color.parseColor("#E4E8EB"), Color.parseColor("#017CFE"))
                .setSliderWidth(getResources().getDimension(R.dimen.slider1))
                .setSliderHeight(getResources().getDimension(R.dimen.slider2))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setupWithViewPager(binding.pagerAndroid);

    }
}