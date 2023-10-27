package io.deepankumarpn.voteridregistration.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import io.deepankumarpn.voteridregistration.data.local.SharedPreferencesManager;
import io.deepankumarpn.voteridregistration.databinding.ActivitySplashBinding;
import io.deepankumarpn.voteridregistration.ui.dashboard.DashBoardActivity;
import io.deepankumarpn.voteridregistration.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this);
        if (!sharedPreferencesManager.getEmail().isEmpty()) {
            startActivity(new Intent(this, DashBoardActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

}