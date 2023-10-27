package io.deepankumarpn.voteridregistration.ui.voteridreg;

import static io.deepankumarpn.voteridregistration.utils.Utils.is18AndAbove;
import static io.deepankumarpn.voteridregistration.utils.Utils.isNameValid;
import static io.deepankumarpn.voteridregistration.utils.Utils.isValidEmail;
import static io.deepankumarpn.voteridregistration.utils.Utils.isValidPhoneNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import io.deepankumarpn.voteridregistration.R;
import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.VoterEntity;
import io.deepankumarpn.voteridregistration.databinding.ActivityVoterIdregBinding;
import io.deepankumarpn.voteridregistration.ui.dashboard.DashBoardActivity;
import io.deepankumarpn.voteridregistration.utils.Utils;

public class VoterIDRegActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityVoterIdregBinding binding;
    private Calendar myCalendar;
    private VoterViewModel voterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoterIdregBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setOnclickListener();
        voterViewModel = new ViewModelProvider(this).get(VoterViewModel.class);
        voterViewModel.setContext(this);
    }

    private void setOnclickListener() {
        binding.regVoterBtn.setOnClickListener(this);
        binding.dobEtx.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.regVoterBtn) {
            String firstName = binding.firstNameEtx.getText().toString();
            String lastName = binding.lastNameEtx.getText().toString();
            String dob = binding.dobEtx.getText().toString();
            String mobileNumber = "+91" + binding.mobileNumberEtx.getText().toString();
            String email = binding.emailEtx.getText().toString();
            String address = binding.addressEtx.getText().toString();
            String taluk = binding.talukEtx.getText().toString();
            String district = binding.districtEtx.getText().toString();
            String state = binding.stateEtx.getText().toString();

            if (inputValidated(firstName, lastName, Utils.convertStringToDate(dob), mobileNumber, email, address, taluk, district, state)) {
                VoterEntity voterEntity = new VoterEntity();
                voterEntity.createdAt = Utils.getTodayDate();
                voterEntity.firstName = firstName;
                voterEntity.lastName = lastName;
                voterEntity.dob = dob;
                voterEntity.mobilenumber = mobileNumber;
                voterEntity.email = email;
                voterEntity.address = address;
                voterEntity.taluk = taluk;
                voterEntity.district = district;
                voterEntity.state = state;
                voterViewModel.insertVoter(voterEntity);
                Intent intent = new Intent(VoterIDRegActivity.this, DashBoardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        }

        if (id == R.id.dobEtx) {
            myCalendar = Calendar.getInstance();
            myCalendar.add(Calendar.DATE, -1);
            DatePickerDialog datePickerDialog = new DatePickerDialog(VoterIDRegActivity.this, dateSetListener,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
            datePickerDialog.show();
        }
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            binding.dobEtx.setText(Utils.beautifyDate(myCalendar.getTime()));
        }
    };

    private boolean inputValidated(String name, String lastName, Date dob,
                                   String mobileNumber, String email, String address,
                                   String taluk, String district, String state) {
        if (!isNameValid(name)) {
            binding.firstNameEtx.setError(getString(R.string.invalid_name));
            return false;
        }

        if (!isNameValid(lastName)) {
            binding.lastNameEtx.setError(getString(R.string.invalid_name));
            return false;
        }

        if (!is18AndAbove(dob)) {
            binding.dobEtx.setError(getString(R.string.user_is_under_18));
            Toast.makeText(this, "" + getString(R.string.user_is_under_18), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidPhoneNumber(mobileNumber)) {
            binding.mobileNumberEtx.setError(getString(R.string.invalid_mobile_number));
            return false;
        }

        if (!isValidEmail(email)) {
            binding.emailEtx.setError(getString(R.string.invalid_email_format));
            return false;
        }

        if (!isNameValid(address)) {
            binding.addressEtx.setError(getString(R.string.address_required));
            return false;
        }

        if (!isNameValid(taluk)) {
            binding.talukEtx.setError(getString(R.string.taluk_required));
            return false;
        }

        if (!isNameValid(district)) {
            binding.districtEtx.setError(getString(R.string.district_required));
            return false;
        }
        if (!isNameValid(state)) {
            binding.stateEtx.setError(getString(R.string.state_required));
            return false;
        }


        binding.firstNameEtx.setError(null);
        binding.lastNameEtx.setError(null);
        binding.dobEtx.setError(null);
        binding.mobileNumberEtx.setError(null);
        binding.emailEtx.setError(null);
        binding.addressEtx.setError(null);
        binding.talukEtx.setError(null);
        binding.districtEtx.setError(null);
        binding.stateEtx.setError(null);
        return true;
    }
}