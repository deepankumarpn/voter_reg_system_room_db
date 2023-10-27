package io.deepankumarpn.voteridregistration.ui.userregistration;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.AppUserRegEntity;

public class AppUserViewModel extends ViewModel {

    private AppUserRepository appUserRepository;

    public AppUserViewModel() {
    }

    public void setContext(Context context) {
        appUserRepository = new AppUserRepository(context);
    }

    public void registerUser(String firstName, String lastName, String email, String password, boolean userActiveStatus) {
        appUserRepository.insertUser(firstName, lastName, email, password, userActiveStatus);
    }

    public AppUserRegEntity loginUser(String email, String password, boolean userActiveStatus) {
        return appUserRepository.loginUser(email, password, userActiveStatus);
    }

    public boolean isEmailRegistered(String email) {
        return appUserRepository.isEmailRegistered(email);
    }
}