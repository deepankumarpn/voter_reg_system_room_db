package io.deepankumarpn.voteridregistration;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.deepankumarpn.voteridregistration.network.APIManager;
import io.deepankumarpn.voteridregistration.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VoterIDRegistrationApp extends Application {

    private Gson gson;
    private Retrofit retrofit;
    private HttpLoggingInterceptor logging;

    @Override
    public void onCreate() {
        super.onCreate();
        logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
//        if (BuildConfig.DEBUG) {
//            logging.level(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            logging.level(HttpLoggingInterceptor.Level.BASIC);
//        }
    }

    public Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
        return retrofit;
    }

    public APIManager getAPIManager() {
        if (retrofit == null) {
            retrofit = getRetrofit();
        }
        return retrofit.create(APIManager.class);
    }

    public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gson;
    }
}
