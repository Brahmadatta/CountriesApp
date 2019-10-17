package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();

    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh(){

        fetchCountries();
    }

    private void fetchCountries(){

        CountryModel country1 = new CountryModel("India","New Delhi","");
        CountryModel country2 = new CountryModel("Finland","Helsinki","");
        CountryModel country3 = new CountryModel("Brazil","Brasilia","");

        List<CountryModel> countryModels = new ArrayList<>();
        countryModels.add(country1);
        countryModels.add(country2);
        countryModels.add(country3);
        countryModels.add(country1);
        countryModels.add(country2);
        countryModels.add(country3);
        countryModels.add(country1);
        countryModels.add(country2);
        countryModels.add(country3);
        countryModels.add(country1);
        countryModels.add(country2);
        countryModels.add(country3);


        countries.setValue(countryModels);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }
}
