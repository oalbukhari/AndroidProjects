package com.omar_al_bukhari.weatherprojectnew.Model;

import android.app.DownloadManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class JsonReader{



    String cityName;

    Gson gson = new GsonBuilder()
            .create();
    String apiKey = "ecad2a61d663ed54";

    OkHttpClient.Builder httpclientBuilder = new OkHttpClient.Builder();
    public JsonReaderL jsonreaderApi= new Retrofit.Builder()
            .baseUrl("http://api.wunderground.com/api/"+apiKey+"/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callFactory(httpclientBuilder.build())
            .build().create(JsonReaderL.class);


    public JsonReader() {}


}