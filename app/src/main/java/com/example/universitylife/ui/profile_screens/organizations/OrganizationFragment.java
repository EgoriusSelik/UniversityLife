package com.example.universitylife.ui.profile_screens.organizations;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.universitylife.R;
import com.example.universitylife.ui.profile_screens.achivments.AchivmentItem;
import com.example.universitylife.ui.profile_screens.achivments.AchivmentListAdapter;

import java.util.ArrayList;

public class OrganizationFragment extends Fragment {

    ArrayList<OrganizationItem> states = new ArrayList<OrganizationItem>();
    private OrganizationViewModel mViewModel;

    public static OrganizationFragment newInstance() {
        return new OrganizationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization_list, container, false);

        setInitialData();
        RecyclerView recyclerView = view.findViewById(R.id.org_list);
        OrganizationListAdapter organizationListAdapter = new OrganizationListAdapter(getContext(), states);
        recyclerView.setAdapter(organizationListAdapter);

        ImageView go_back = view.findViewById(R.id.go_back_to_profile3);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_organizationScreenFragment_to_profileMainFragment);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrganizationViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setInitialData(){
        states.add(new OrganizationItem("ПФО \"Вектор\"", "«Вектор» — направление успеха! Отряд организовывает и проводит все мероприятия для абитуриентов. Для участников отряда проводятся уникальные тренинги, семинары и мастер-классы.",R.drawable.icon_of_vactor));
        states.add(new OrganizationItem("ПФО \"Вектор\"", "«Вектор» — направление успеха! Отряд организовывает и проводит все мероприятия для абитуриентов. Для участников отряда проводятся уникальные тренинги, семинары и мастер-классы.",R.drawable.icon_of_vactor));
        states.add(new OrganizationItem("ПФО \"Вектор\"", "«Вектор» — направление успеха! Отряд организовывает и проводит все мероприятия для абитуриентов. Для участников отряда проводятся уникальные тренинги, семинары и мастер-классы.",R.drawable.icon_of_vactor));
        states.add(new OrganizationItem("ПФО \"Вектор\"", "«Вектор» — направление успеха! Отряд организовывает и проводит все мероприятия для абитуриентов. Для участников отряда проводятся уникальные тренинги, семинары и мастер-классы.",R.drawable.icon_of_vactor));
        states.add(new OrganizationItem("ПФО \"Вектор\"", "«Вектор» — направление успеха! Отряд организовывает и проводит все мероприятия для абитуриентов. Для участников отряда проводятся уникальные тренинги, семинары и мастер-классы.",R.drawable.icon_of_vactor));
    }
}