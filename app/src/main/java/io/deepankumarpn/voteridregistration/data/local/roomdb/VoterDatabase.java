package io.deepankumarpn.voteridregistration.data.local.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.deepankumarpn.voteridregistration.data.local.roomdb.dao.AppUserRegDao;
import io.deepankumarpn.voteridregistration.data.local.roomdb.dao.VoterDao;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.AppUserRegEntity;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.VoterEntity;

@Database(entities = {VoterEntity.class, AppUserRegEntity.class}, version = 1, exportSchema = false)
public abstract class VoterDatabase extends RoomDatabase {

    public abstract VoterDao voterDao();

    public abstract AppUserRegDao appUserRegDao();

    private static VoterDatabase instance;

    public static synchronized VoterDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            VoterDatabase.class, "voter_registration_system_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
