package com.example.countriesapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.countriesapp.model.CountriesService;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.viewmodel.ListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {


    @Rule
    public InstantTaskExecutorRule mRule = new InstantTaskExecutorRule();

    @Mock
    CountriesService mCountriesService;

    @InjectMocks
    ListViewModel mListViewModel = new ListViewModel();

    private Single<List<CountryModel>> testSingle;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountriesSuccess(){
        CountryModel countryModel = new CountryModel("India","New Delhi","flag");
        ArrayList<CountryModel> countriesList = new ArrayList<>();
        countriesList.add(countryModel);

        testSingle = Single.just(countriesList);

        Mockito.when(mCountriesService.getCountries()).thenReturn(testSingle);

        mListViewModel.refresh();

        Assert.assertEquals(1,mListViewModel.countries.getValue().size());
        Assert.assertEquals(false,mListViewModel.countryLoadError.getValue());
        Assert.assertEquals(false,mListViewModel.loading.getValue());

    }

    @Before
    public void setupRxSchedulers(){

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run();},true);
            }
        };

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
    }
}
