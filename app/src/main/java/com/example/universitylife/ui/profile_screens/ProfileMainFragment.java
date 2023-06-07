package com.example.universitylife.ui.profile_screens;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.universitylife.R;
import com.example.universitylife.registration.LoginViewModel;
import com.example.universitylife.registration.UserEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfileMainFragment extends Fragment {

    private LoginViewModel viewModel;

    private ProfileMainViewModel profileMainViewModel;

    private UserEntity userEntityInFragment;

    private NavController navController;
    ArrayList<ProfileListItem> states = new ArrayList<ProfileListItem>();

    public static ProfileMainFragment newInstance() {
        return new ProfileMainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        viewModel.getLoggedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getActivity().findViewById(R.id.bottom_nav).setVisibility(View.GONE);
                goToLogin();
            }
        });
        profileMainViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(ProfileMainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_main, container, false);

        TextView name_and_sername = view.findViewById(R.id.textView15);
        TextView user_instityt = view.findViewById(R.id.user_instityt);
        TextView user_group = view.findViewById(R.id.user_group);
        TextView user_status = view.findViewById(R.id.user_status);

        profileMainViewModel.getUserEntityMutableLiveData().observe(getViewLifecycleOwner(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {

                Spanned text = Html.fromHtml("<b>Институт</b>: "+ userEntity.getInstityt());
                Spanned text1 = Html.fromHtml("<b>Группа</b>: "+ userEntity.getGroup());

                name_and_sername.setText(userEntity.getName_and_sername());
                user_instityt.setText(text);
                user_group.setText(text1);
                user_status.setText(userEntity.getDescription());
            }
        });

        setInitialData();

        RecyclerView recyclerView = view.findViewById(R.id.profile_list);
        // определяем слушателя нажатия элемента в списке
        ProfileListAdapter.OnStateClickListener stateClickListener
                = new ProfileListAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ProfileListItem state, int position) {
                if (position == 0){
                    TextView go_to_ach = view.findViewById(R.id.profile_list).findViewById(R.id.go_to_ach_screen);
                    go_to_ach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                                    .navigate(R.id.action_profileMainFragment_to_achivmentFragment);
                        }
                    });
                }
                else if (position == 1){
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                            .navigate(R.id.action_profileMainFragment_to_historyFragment);
                }
                else if (position == 2){
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                            .navigate(R.id.action_profileMainFragment_to_organizationScreenFragment);
                }
                else if (position == 3){
                    viewModel.signOut();
                }
            }
        };

        ProfileListAdapter profileListAdapter = new ProfileListAdapter(getContext(), states, stateClickListener);
        recyclerView.setAdapter(profileListAdapter);

        ImageView edit_user_data = view.findViewById(R.id.edit_user_data);
        edit_user_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
                bottomNavigationView.setVisibility(View.GONE);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_profileMainFragment_to_userDataFragment2);
            }
        });

        return view;
    }

    private void setInitialData(){
        states.add(new ProfileListItem("Достижения", R.drawable.progressstar, R.drawable.indicator));
        states.add(new ProfileListItem("История", R.drawable.history, R.drawable.indicator));
        states.add(new ProfileListItem("Организации", R.drawable.accountbox, R.drawable.indicator));
        states.add(new ProfileListItem("Выход", R.drawable.logout, R.drawable.indicator));
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void goToLogin(){
        Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment).navigate(R.id.action_profileMainFragment_to_loginFragment);
    }
}