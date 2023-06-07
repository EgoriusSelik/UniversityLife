package com.example.universitylife.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.universitylife.MainActivity;
import com.example.universitylife.OnboardingActivity;
import com.example.universitylife.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Onboarding4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Onboarding4Fragment extends Fragment {

    public Onboarding4Fragment() {
        // Required empty public constructor
    }

    public static Onboarding4Fragment newInstance() {
        Onboarding4Fragment fragment = new Onboarding4Fragment();
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

        View view = inflater.inflate(R.layout.fragment_onboarding4, container, false);

        // Переход обратно к входу
        Button btn_to_login = view.findViewById(R.id.button_to_login_from_onb);
        btn_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        return view;
    }
}