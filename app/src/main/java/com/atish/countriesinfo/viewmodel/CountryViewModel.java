package com.atish.countriesinfo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.atish.countriesinfo.model.Country;
import com.atish.countriesinfo.repository.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepository;
    private LiveData<List<Country>> getAllCountry;
    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository=new CountryRepository(application);
        getAllCountry=countryRepository.getAllActors();
    }
    public void insert (List<Country> list)
    {
        countryRepository.insert(list);
    }

    public LiveData<List<Country>>getAllCountry(){
        return getAllCountry;
    }

    public void deleteAll(){
        countryRepository.deleteAll();
    }
}
