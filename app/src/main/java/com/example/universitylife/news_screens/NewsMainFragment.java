package com.example.universitylife.news_screens;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.universitylife.R;
import com.example.universitylife.model.NewsEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsMainFragment extends Fragment {

    private NewsMainViewModel viewModel;

    private int visible_button_delete = 0;

    private MaterialTimePicker materialTimePicker;
    private Calendar calendar;

    private AlarmManager alarmManager;

    private PendingIntent pendingIntent;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    BottomSheetDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(NewsMainViewModel.class);
    }

    public static NewsMainFragment newInstance() {
        return new NewsMainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_main, container, false);
        return view;
    }
    private void createDialog(String name_to_set, String descr, String image){
        View view = getLayoutInflater().inflate(R.layout.fragment_news_more_info, null, false);
        TextView description = view.findViewById(R.id.long_description_sheet);
        TextView name = view.findViewById(R.id.name_mero_sheet);
        ImageView image_of_mero = view.findViewById(R.id.image_mero_sheet);
        description.setText(descr);
        name.setText(name_to_set);
        Glide.with(view).load(image).into(image_of_mero);
        dialog.setContentView(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_news);

        // определяем слушателя нажатия элемента в списке
        NewsListAdapter.OnStateClickListener stateClickListener
                = new NewsListAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(NewsEntity state, int position) {
                FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
                String id = auth.getUid();
                String add_mero = state.getName_of_mero() + "/" + state.getDate_place_of_mero();

                dialog = new BottomSheetDialog(getContext());
                createDialog(state.getName_of_mero(),
                        state.getLong_description_of_mero(), state.getImage_of_mero());
                dialog.show();

                //Уведомления
                Button button_to_notify = dialog.findViewById(R.id.set_reminder);
                Button button_to_delete_notify = dialog.findViewById(R.id.set_reminder3);


                if (visible_button_delete == 0){
                    button_to_notify.setVisibility(View.VISIBLE);
                    button_to_delete_notify.setVisibility(View.GONE);
                }
                else if (visible_button_delete == 1){
                    button_to_notify.setVisibility(View.GONE);
                    button_to_delete_notify.setVisibility(View.VISIBLE);
                }

                createNotificationChannel();
                button_to_notify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel("channel_id",
                                    "Канал напоминаний", NotificationManager.IMPORTANCE_DEFAULT);
                            channel.setDescription("Напоминания");
                            NotificationManager notificationManager =
                                    getActivity().getSystemService(NotificationManager.class);
                            notificationManager.createNotificationChannel(channel);
                        }
                        showTimePicker();
                        button_to_notify.setVisibility(View.GONE);
                        button_to_delete_notify.setVisibility(View.VISIBLE);
                        visible_button_delete = 1;
                    }
                });
                button_to_delete_notify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cancelAlarm();
                        button_to_notify.setVisibility(View.VISIBLE);
                        button_to_delete_notify.setVisibility(View.GONE);
                        visible_button_delete = 0;
                    }
                });


                Button set_mero = dialog.findViewById(R.id.set_mero);
                System.out.println(firebaseFirestore.collection("Dates").document(id).get());
                firebaseFirestore.collection("Dates").document(id).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                System.out.println("Yes&");
                                Map<String, Object> data = new HashMap<>();
                                data = task.getResult().getData();
                                if (data != null){
                                    for (String value : data.keySet()) {
                                        if (String.valueOf(value).equals(add_mero)){
                                            if (set_mero != null) {
                                                set_mero.setText("Уже добавлено");
                                                set_mero.setEnabled(false);
                                            }
                                            break;
                                        }
                                    }
                                }
                                set_mero.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Map<String, Object> meros = new HashMap<>();
                                        meros.put(add_mero, position);
                                        firebaseFirestore.collection("Dates").document(id).set(meros, SetOptions.merge())
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(getActivity(), "Мероприятие добавлено успешно",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("No fields");
                            }
                        });

            }
        };

        NewsListAdapter newsListAdapter = new NewsListAdapter(getContext(), stateClickListener);
        recyclerView.setAdapter(newsListAdapter);

        viewModel.getNewsListLiveData().observe(getViewLifecycleOwner(), new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(List<NewsEntity> newsListFirebaseModels) {
                newsListAdapter.setStates(newsListFirebaseModels);
                newsListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createNotificationChannel(){
        CharSequence name = "Канал напоминаний";
        String description = "Channel AlarmManager";
        int imroptance = NotificationManager.IMPORTANCE_DEFAULT;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel("notify", name, imroptance);
            NotificationManager notificationManager = (NotificationManager) getContext().
                    getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    private void showTimePicker(){
        materialTimePicker = new MaterialTimePicker
                .Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время напоминания")
                .build();
        materialTimePicker.show(getParentFragmentManager(), "Напоминания");

        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                setAlarm();
            }
        });
    }
    private void setAlarm(){
        alarmManager = (AlarmManager) getContext().
                getSystemService(Context.ALARM_SERVICE);
        System.out.println(calendar);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(getContext(), "Напоминание установлено", Toast.LENGTH_SHORT).show();

    }

    private void cancelAlarm(){
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent,0);
        if (alarmManager == null){
            alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getContext(), "Напоминание удалено", Toast.LENGTH_SHORT).show();

    }
}