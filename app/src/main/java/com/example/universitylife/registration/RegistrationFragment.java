package com.example.universitylife.registration;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
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
import com.google.firebase.auth.FirebaseUser;

public class RegistrationFragment extends Fragment {

    private LoginViewModel viewModel;

    private EditText emailEdit, passEdit, passConf;
    private Button registerButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    //getActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
                    goToApp();
                }
            }
        });
    }

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        TextView linkreg = view.findViewById(R.id.link_to_enter);
        String text = "<font color=#000000>Уже есть аккаунт?</font> <font color=#017CFE>Войти</font>";
        linkreg.setText(Html.fromHtml(text));

        TextView txt_to_enter = view.findViewById(R.id.link_to_enter);
        txt_to_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", "lol_enter");
                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment, bundle);
            }
        });

        ImageView to_onboard_reg = view.findViewById(R.id.imageView2reg);
        to_onboard_reg.setOnClickListener(new View.OnClickListener() {
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

        emailEdit = view.findViewById(R.id.email_reg);
        passEdit = view.findViewById(R.id.pass_reg);
        passConf = view.findViewById(R.id.pass_reg_confirm);
        registerButton = view.findViewById(R.id.button_reg);

        registerButton = view.findViewById(R.id.button_reg);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEdit.getText().toString();
                String password = passEdit.getText().toString();
                String password_confirm = passConf.getText().toString();
                System.out.println(password.equals(password_confirm));
                System.out.println("testyyyyyyy");
                if (!email.isEmpty() && !password.isEmpty() && (password.equals(password_confirm))){
                    viewModel.register(email, password);
                }
                else if (email.isEmpty()){
                    Toast.makeText(getContext(), "Введите email",Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(getContext(), "Введите пароль",Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(password_confirm)){
                    Toast.makeText(getContext(), "Пароль не совпадает",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToApp(){
        Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment).navigate(R.id.action_registrationFragment_to_userDataFragment2);
    }
}