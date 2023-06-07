package com.example.universitylife.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universitylife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDataFragment extends Fragment {

    private DatabaseReference mDatabase;
    private String USER_MAIN_DATA = "User";

    private EditText name_and_sername;
    private EditText instityt;
    private EditText group;
    private EditText description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data, container, false);
        name_and_sername = view.findViewById(R.id.user_name);
        instityt = view.findViewById(R.id.email_login);
        group = view.findViewById(R.id.email_login_2);
        description = view.findViewById(R.id.email_login_1);

        mDatabase = FirebaseDatabase
                .getInstance("https://login-register-universitylife-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference(USER_MAIN_DATA);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button go_to_main_app = view.findViewById(R.id.button_user_to_app);
        go_to_main_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();

                String id = auth.getUid();
                String name_bd = name_and_sername.getText().toString();
                String instityt_bd = instityt.getText().toString();
                String group_bd = group.getText().toString();
                String description_bd = description.getText().toString();
                HashMap<String, String> mero_in_calendar = new HashMap<String, String>();

                if (!TextUtils.isEmpty(name_bd) && !TextUtils.isEmpty(instityt_bd)
                        && !TextUtils.isEmpty(group_bd) && !TextUtils.isEmpty(description_bd)){
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    UserEntity userEntity = new
                            UserEntity(id, name_bd, instityt_bd, group_bd, description_bd);
                    mDatabase.push().setValue(userEntity).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                System.out.println("Nice");
                                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
                                bottomNavigationView.setVisibility(View.VISIBLE);
                                bottomNavigationView.setSelectedItemId(R.id.news);
                                navController.navigate(R.id.action_userDataFragment2_to_newsMainFragment);
                            }
                            else {
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}