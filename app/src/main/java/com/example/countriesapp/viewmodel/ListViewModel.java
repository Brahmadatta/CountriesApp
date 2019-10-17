package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.CountriesService;
import com.example.countriesapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();

    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private CountriesService mCountriesService = CountriesService.getInstance();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    public void refresh(){

        fetchCountries();
    }

    private void fetchCountries(){


        loading.setValue(true);

        mCompositeDisposable.add(
                mCountriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(List<CountryModel> countryModels) {
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                        countries.setValue(countryModels);
                    }

                    @Override
                    public void onError(Throwable e) {

                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();

                    }
                })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
