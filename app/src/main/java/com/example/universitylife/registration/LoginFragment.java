package com.example.universitylife.registration;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universitylife.OnboardingActivity;
import com.example.universitylife.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;

    private EditText emailEdit, passEdit;
    private Button loginButton;

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    System.out.println("yeeeees");
                    BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.setSelectedItemId(R.id.news);
                    goToApp();
                }
            }
        });
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView linkreg = view.findViewById(R.id.link_to_reg);
        String text = "<font color=#000000>Еще нет аккаунта?</font> <font color=#017CFE>Зарегистрироваться</font>";
        linkreg.setText(Html.fromHtml(text));
        TextView txt_to_registration = view.findViewById(R.id.link_to_reg);
        txt_to_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", "lol");
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment, bundle);
            }
        });

        ImageView to_onboard = view.findViewById(R.id.imageView2);
        to_onboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), OnboardingActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEdit = view.findViewById(R.id.email_login);
        passEdit = view.findViewById(R.id.pass_login);
        loginButton = view.findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEdit.getText().toString();
                String password = passEdit.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    viewModel.signIn(email, password);
                }
                else {
                    Toast.makeText(getActivity(), "Заполните все поля",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToApp(){
        Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment).navigate(R.id.action_loginFragment_to_newsMainFragment);
    }

}