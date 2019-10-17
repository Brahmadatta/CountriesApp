package com.example.countriesapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>{

    List<CountryModel> mCountryModelList;

    public CountryListAdapter(List<CountryModel> countryModelList) {
        mCountryModelList = countryModelList;
    }

    public void updateCountries(List<CountryModel> newCountries){
        mCountryModelList.clear();
        mCountryModelList.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {

        holder.bind(mCountryModelList.get(position));


    }

    @Override
    public int getItemCount() {
        return mCountryModelList.size();
    }

    public class CountryListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        ImageView countryImage;

        @BindView(R.id.name)
        TextView countryName;

        @BindView(R.id.capital)
        TextView countryCapital;


        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(CountryModel country){

            countryName.setText(country.getCountryName());
            countryCapital.setText(country.getCapital());
            Util.loadImage(countryImage,country.getFlag(),Util.getDrawable(countryImage.getContext()));
        }
    }
}
