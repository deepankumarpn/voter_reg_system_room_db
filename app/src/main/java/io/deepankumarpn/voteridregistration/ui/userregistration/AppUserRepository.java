package io.deepankumarpn.voteridregistration.ui.userregistration;

import android.content.Context;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import io.deepankumarpn.voteridregistration.data.local.roomdb.VoterDatabase;
import io.deepankumarpn.voteridregistration.data.local.roomdb.dao.AppUserRegDao;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.AppUserRegEntity;

public class AppUserRepository {

    private AppUserRegDao appUserRegDao;
    private VoterDatabase voterDatabase;

    public AppUserRepository(Context context) {
        voterDatabase = VoterDatabase.getInstance(context);
        appUserRegDao = voterDatabase.appUserRegDao();
    }

    public void insertUser(String firstName, String lastName, String email, String password, boolean userActiveStatus) {
        AppUserRegEntity newAppUser = new AppUserRegEntity();
        newAppUser.firstName = firstName;
        newAppUser.lastName = lastName;
        newAppUser.email = email;
        newAppUser.password = password;
        newAppUser.userActiveStatus = userActiveStatus;

        new Thread(() -> {
            voterDatabase.runInTransaction(() -> {
                appUserRegDao.insert(newAppUser);
            });
        }).start();
    }

    public AppUserRegEntity loginUser(String email, String password, boolean userActiveStatus) {
        AtomicReference<AppUserRegEntity> user = new AtomicReference<>(null);
        Thread thread = new Thread(() -> {
            user.set(appUserRegDao.login(email, password, userActiveStatus));
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user.get();
    }

    public boolean isEmailRegistered(String email) {
        AtomicBoolean isRegistered = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            isRegistered.set(appUserRegDao.isEmailRegistered(email) > 0);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isRegistered.get();
    }
}
