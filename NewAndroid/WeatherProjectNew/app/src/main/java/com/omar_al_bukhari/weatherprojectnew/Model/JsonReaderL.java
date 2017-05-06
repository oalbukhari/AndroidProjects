package com.omar_al_bukhari.weatherprojectnew.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 04/05/2017
 * Any copy of this code is forbidden.
 */

public interface JsonReaderL {
    @GET("conditions/q/IT/{city}.json")
    public Call<Example> getnameCity(@Path("city")String city);

}