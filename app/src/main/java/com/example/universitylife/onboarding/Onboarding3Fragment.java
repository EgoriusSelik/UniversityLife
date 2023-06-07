package com.example.universitylife.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.universitylife.R;

public class Onboarding3Fragment extends Fragment {


    public Onboarding3Fragment() {
        // Required empty public constructor
    }

    public static Onboarding3Fragment newInstance() {
        Onboarding3Fragment fragment = new Onboarding3Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_onboarding3, container, false);
    }
}