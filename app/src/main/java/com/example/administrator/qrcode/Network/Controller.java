package com.example.administrator.qrcode.Network;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seoil on 2016-12-07.
 */
public class Controller extends Application {
    private static Controller instance;
    public static Controller getInstance(){return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        Controller.instance = this;
    }

    private ServerInterface api;
    public ServerInterface getNetworkService() {
        return api;
    }

    private String baseUrl;

    public void buildNetworkService() {
        synchronized (Controller.class) {
            if (api == null) {
                baseUrl = String.format("http://119.207.180.205:8080/qrlocker/");

                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(factory)
                        .build();

                api = retrofit.create(ServerInterface.class);
            }
        }
    }

}