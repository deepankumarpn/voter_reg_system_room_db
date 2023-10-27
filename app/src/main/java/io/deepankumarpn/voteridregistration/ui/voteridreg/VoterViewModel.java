package io.deepankumarpn.voteridregistration.ui.voteridreg;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.VoterEntity;

public class VoterViewModel extends ViewModel {
    private VoterRepository voterRepository;

    private LiveData<List<VoterEntity>> allVoters;

    public VoterViewModel() {
    }

    public void setContext(Context context) {
        voterRepository = new VoterRepository(context);
    }

    public void insertVoter(VoterEntity voterEntity) {
        voterRepository.insertVoter(voterEntity);
    }

    public int getVotersCount() {
        return voterRepository.getVotersCount();
    }

    public int getVotersCountByCreatedAt(String createdAt) {
        return voterRepository.getVotersCountByCreatedAt(createdAt);
    }

    public LiveData<List<VoterEntity>> getAllVoters() {
        return allVoters;
    }
}
