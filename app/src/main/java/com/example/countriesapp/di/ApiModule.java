package com.example.countriesapp.di;

import com.example.countriesapp.model.CountriesApi;
import com.example.countriesapp.model.CountriesService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "https://raw.githubusercontent.com";


    @Provides
    public CountriesApi provideCountriesAPi(){

        return new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build()
                                .create(CountriesApi.class);

    }

    @Provides
    public CountriesService providesCountriesService(){
        return CountriesService.getInstance();
    }

}
