package com.bsgauth.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MockRepositoryFabric {
    private static final MockRepository ourInstance = new Retrofit.Builder()
                .baseUrl("https://jsonblob.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(MockRepository.class);

    public static MockRepository getInstance() {
        return ourInstance;
    }

    private MockRepositoryFabric() {
    }
}
