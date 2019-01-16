package com.bsgauth.repositories;

import com.bsgauth.model.Account;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

public interface MockRepository {
    @GET("6b9842b6-14fe-11e9-8960-a93e970e1051")
    public Call<Map<String, List<Account>>> getMockData ();
}
