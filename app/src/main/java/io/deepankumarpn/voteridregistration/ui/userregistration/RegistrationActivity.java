package io.deepankumarpn.voteridregistration.ui.userregistration;

import static io.deepankumarpn.voteridregistration.utils.Utils.isNameValid;
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
import io.deepankumarpn.voteridregistration.data.local.roomdb.dao.AppUserRegDao;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.AppUserRegEntity;
import io.deepankumarpn.voteridregistration.databinding.ActivityRegistrationBinding;
import io.deepankumarpn.voteridregistration.ui.dashboard.DashBoardActivity;
import io.deepankumarpn.voteridregistration.ui.login.LoginActivity;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegistrationBinding binding;
    private AppUserViewModel appUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setOnclickListener();
        appUserViewModel = new ViewModelProvider(this).get(AppUserViewModel.class);
        appUserViewModel.setContext(this);
    }

    private void setOnclickListener() {
        binding.submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.submitBtn) {
            String firstName = binding.firstNameEtx.getText().toString();
            String lastName = binding.lastNameEtx.getText().toString();
            String email = binding.emailEtx.getText().toString();
            String password = binding.pwdEtx.getText().toString();
            String conPassword = binding.confirmPwdEtx.getText().toString();
            boolean userActiveStatus = true;

            if (inputValidated(firstName, lastName, email, password, conPassword)) {

                boolean isEmailRegistered = appUserViewModel.isEmailRegistered(email);

                if (isEmailRegistered) {
                    binding.emailEtx.setError(getString(R.string.email_already_exit));
                } else {
                    appUserViewModel.registerUser(firstName, lastName, email, password, userActiveStatus);
                    new SharedPreferencesManager(this).setEmail(email);

                    Intent intent = new Intent(RegistrationActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }
    }

    private boolean inputValidated(String name, String lastName, String email, String password, String confirmPassword) {
        if (!isNameValid(name)) {
            binding.firstNameEtx.setError(getString(R.string.invalid_name));
            return false;
        }

        if (!isNameValid(lastName)) {
            binding.lastNameEtx.setError(getString(R.string.invalid_name));
            return false;
        }

        if (!isValidEmail(email)) {
            binding.emailEtx.setError(getString(R.string.invalid_email_format));
            return false;
        }

        if (!isPasswordValid(password)) {
            binding.pwdEtx.setError(getString(R.string.invalid_password_format));
            return false;
        }

        if (!password.equals(confirmPassword)) {
            binding.confirmPwdEtx.setError(getString(R.string.password_mismatch));
            return false;
        }
        binding.firstNameEtx.setError(null);
        binding.lastNameEtx.setError(null);
        binding.emailEtx.setError(null);
        binding.pwdEtx.setError(null);
        binding.confirmPwdEtx.setError(null);
        return true;
    }
}