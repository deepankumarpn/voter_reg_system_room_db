package io.deepankumarpn.voteridregistration.data.local.roomdb.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.AppUserRegEntity;
@Dao
public interface  AppUserRegDao {
    @Insert
    void insert(AppUserRegEntity appUserRegEntity);

    @Query("SELECT * FROM app_users_table WHERE email = :email AND password = :password AND userActiveStatus = :userActiveStatus")
    AppUserRegEntity login(String email, String password, boolean userActiveStatus);

    @Query("SELECT COUNT(*) FROM app_users_table WHERE email = :email")
    int isEmailRegistered(String email);
}
