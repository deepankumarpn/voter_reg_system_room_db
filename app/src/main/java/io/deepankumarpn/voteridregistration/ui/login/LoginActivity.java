package io.deepankumarpn.voteridregistration.ui.login;

import static io.deepankumarpn.voteridregistration.utils.Utils.isPasswordValid;
import static io.deepankumarpn.voteridregistration.utils.Utils.isValidEmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.deepankumarpn.voteridregistration.R;
import io.deepankumarpn.voteridregistration.data.local.SharedPreferencesManager;
import io.deepankumarpn.voteridregistration.data.local.roomdb.VoterDatabase;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.AppUserRegEntity;
import io.deepankumarpn.voteridregistration.databinding.ActivityLoginBinding;
import io.deepankumarpn.voteridregistration.ui.dashboard.DashBoardActivity;
import io.deepankumarpn.voteridregistration.ui.userregistration.AppUserViewModel;
import io.deepankumarpn.voteridregistration.ui.userregistration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private AppUserViewModel appUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appUserViewModel = new ViewModelProvider(this).get(AppUserViewModel.class);
        appUserViewModel.setContext(this);
        setOnclickListener();
    }

    private void setOnclickListener() {
        binding.loginBtn.setOnClickListener(this);
        binding.newUserNavTxt.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.loginBtn) {
            String email = binding.emailEtx.getText().toString();
            String password = binding.pwdEtx.getText().toString();

            if (inputValidated(email, password)) {
                boolean isEmailRegistered = appUserViewModel.isEmailRegistered(email);

                if (!isEmailRegistered) {
                    binding.emailEtx.setError(getString(R.string.email_not_registered));
                } else {
                    AppUserRegEntity user = appUserViewModel.loginUser(email, password, true);
                    if (user != null) {
                        new SharedPreferencesManager(this).setEmail(email);
                        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, this.getString(R.string.email_id_is_not_activated), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (id == R.id.newUserNavTxt) {
            Intent regIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(regIntent);
        }
    }

    private boolean inputValidated(String email, String pwd) {
        if (!isValidEmail(email)) {
            binding.emailEtx.setError(getString(R.string.invalid_email_format));
            return false;
        }

        if (!isPasswordValid(pwd)) {
            binding.pwdEtx.setError(getString(R.string.invalid_password_format));
            return false;
        }
        binding.emailEtx.setError(null);
        binding.pwdEtx.setError(null);
        return true;
    }


}