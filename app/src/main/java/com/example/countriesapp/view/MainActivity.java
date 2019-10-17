package com.example.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countriesList)
    RecyclerView countriesList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private ListViewModel mListViewModel;
    private CountryListAdapter mAdapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mListViewModel.refresh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(mAdapter);

        observeViewModel();

    }

    private void observeViewModel() {
        mListViewModel.countries.observe(this, countryModels -> {
            if (countryModels != null){
                countriesList.setVisibility(View.VISIBLE);
                mAdapter.updateCountries(countryModels);
            }
        });

        mListViewModel.countryLoadError.observe(this, isError -> {
            if (isError != null){
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        mListViewModel.loading.observe(this, isLoading -> {

            if (isLoading != null){
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading){
                    listError.setVisibility(View.GONE);
                    countriesList.setVisibility(View.GONE);
                }
            }
        });
    }
}
