package com.example.universitylife.onboarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPagerAdapter extends FragmentStateAdapter {

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return Onboarding1Fragment.newInstance();
        }
        if (position == 1){
            return Onboarding2Fragment.newInstance();
        }
        if (position == 2){
            return Onboarding3Fragment.newInstance();
        }
        if (position == 3){
            return Onboarding4Fragment.newInstance();
        }
        return Onboarding1Fragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
