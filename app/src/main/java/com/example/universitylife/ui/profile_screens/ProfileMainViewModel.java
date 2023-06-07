package com.example.universitylife.ui.profile_screens;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universitylife.registration.UserEntity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileMainViewModel extends ViewModel {

    private final String USER_MAIN_DATA = "User";
    private UserEntity userEntity;

    public ProfileMainViewModel() {
        readUsersData(new DataStatus() {
            @Override
            public void DataIsLoaded(UserEntity userEntity, List<String> keys) {
                userEntityMutableLiveData.setValue(userEntity);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    public interface DataStatus{
        void DataIsLoaded(UserEntity userEntity, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    MutableLiveData<UserEntity> userEntityMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<UserEntity> getUserEntityMutableLiveData() {
        return userEntityMutableLiveData;
    }

    private DatabaseReference mDatabase = FirebaseDatabase
            .getInstance("https://login-register-universitylife-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference(USER_MAIN_DATA);

    public void readUsersData(final DataStatus dataStatus){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
                String id = auth.getUid();

                userEntity = new UserEntity();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    if (keyNode.child("id").getValue().equals(id)){
                        userEntity = keyNode.getValue(UserEntity.class);
                    }
                }
                dataStatus.DataIsLoaded(userEntity, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}