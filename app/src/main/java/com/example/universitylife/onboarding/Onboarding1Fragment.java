package com.example.universitylife.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.universitylife.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Onboarding1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Onboarding1Fragment extends Fragment {


    public Onboarding1Fragment() {
        // Required empty public constructor
    }

    public static Onboarding1Fragment newInstance() {
        Onboarding1Fragment fragment = new Onboarding1Fragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding1, container, false);
    }
}