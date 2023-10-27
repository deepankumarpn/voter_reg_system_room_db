package io.deepankumarpn.voteridregistration.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.deepankumarpn.voteridregistration.R;
import io.deepankumarpn.voteridregistration.data.local.SharedPreferencesManager;
import io.deepankumarpn.voteridregistration.databinding.ActivityDashBoardBinding;
import io.deepankumarpn.voteridregistration.ui.voteridreg.VoterIDRegActivity;
import io.deepankumarpn.voteridregistration.ui.voteridreg.VoterViewModel;
import io.deepankumarpn.voteridregistration.utils.Utils;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityDashBoardBinding binding;
    private VoterViewModel voterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setOnclickListener();
        voterViewModel = new ViewModelProvider(this).get(VoterViewModel.class);
        voterViewModel.setContext(this);

        binding.voterIDCount.setText(String.valueOf(voterViewModel.getVotersCount()));
        binding.todayRegisterCount.setText(String.valueOf(voterViewModel.getVotersCountByCreatedAt(Utils.getTodayDate())));
    }

    private void setOnclickListener() {

        binding.addVoterFab.setOnClickListener(this);
        binding.logoutTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.addVoterFab) {
            Intent intent = new Intent(DashBoardActivity.this, VoterIDRegActivity.class);
            startActivity(intent);
        }
        if (id == R.id.logoutTxt) {
            Utils.loginAgain(this);
        }
    }
}