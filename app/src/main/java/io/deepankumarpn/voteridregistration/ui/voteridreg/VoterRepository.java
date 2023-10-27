package io.deepankumarpn.voteridregistration.ui.voteridreg;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.deepankumarpn.voteridregistration.data.local.roomdb.VoterDatabase;
import io.deepankumarpn.voteridregistration.data.local.roomdb.dao.VoterDao;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.VoterEntity;

public class VoterRepository {
    private VoterDao voterDao;
    private VoterDatabase voterDatabase;

    private LiveData<List<VoterEntity>> allVoters;

    public VoterRepository(Context context) {
        voterDatabase = VoterDatabase.getInstance(context);
        voterDao = voterDatabase.voterDao();
    }

    public void insertVoter(VoterEntity voterEntity) {
        new Thread(() -> {
            voterDao.insertVoter(voterEntity);
        }).start();
    }

    public int getVotersCount() {
        final int[] count = new int[1];
        Thread thread = new Thread(() -> {
            count[0] = voterDao.getVotersCount();
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count[0];
    }

    public int getVotersCountByCreatedAt(String createdAt) {
        final int[] count = new int[1];
        Thread thread = new Thread(() -> {
            count[0] = voterDao.getVotersCountByCreatedAt(createdAt);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count[0];
    }

    public LiveData<List<VoterEntity>> getAllVoters() {
        MutableLiveData<List<VoterEntity>> liveData = new MutableLiveData<>();
        new Thread(() -> {
            List<VoterEntity> voters = voterDao.getAllVoters();
            liveData.postValue(voters);
        }).start();
        return liveData;
    }
}
