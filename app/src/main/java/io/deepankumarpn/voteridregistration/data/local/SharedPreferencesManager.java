package io.deepankumarpn.voteridregistration.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import io.deepankumarpn.voteridregistration.utils.Constants;

public class SharedPreferencesManager {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void setEmail(String email) {
        sharedPreferences.edit().putString(Constants.EMAIL, email).apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(Constants.EMAIL, "");
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
