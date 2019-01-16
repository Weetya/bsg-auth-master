package com.bsgauth.repositories;

import com.bsgauth.model.Account;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AccountsRepository {
    public void delete (Account account) {

    }

    public void add (Account account) {

    }

    public Call<Map<String, List<Account>>> getAll () throws IOException {
        return MockRepositoryFabric.getInstance().getMockData();
    }
}