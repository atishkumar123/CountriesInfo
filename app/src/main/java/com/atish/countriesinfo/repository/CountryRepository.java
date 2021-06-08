package com.atish.countriesinfo.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.atish.countriesinfo.Dao.CountryDao;
import com.atish.countriesinfo.database.CountryDatabase;
import com.atish.countriesinfo.model.Country;

import java.util.List;

public class CountryRepository {
    private CountryDatabase database;

    private LiveData<List<Country>> getAllActors;

    public CountryRepository(Application application){
        database=CountryDatabase.getInstance(application);
        getAllActors=database.countryDao().getAllActors();
    }
    public void insert(List<Country> countryList){
        new InsertAsynTask(database).execute(countryList);

    }
    public void deleteAll(){
        new deleteAllAsynTask(database).execute();
    }
    public LiveData<List<Country>> getAllActors(){
        return getAllActors;
    }


    static class InsertAsynTask extends AsyncTask<List<Country>,Void,Void> {
        private CountryDao countryDao;
        InsertAsynTask(CountryDatabase countryDatabase)
        {
            countryDao=countryDatabase.countryDao();
        }

        @Override
        protected Void doInBackground(List<Country>... lists) {
            countryDao.insert(lists[0]);
            return null;
        }
    }
    private static class deleteAllAsynTask extends AsyncTask<Void,Void,Void>{
        private CountryDao countryDao;

        public deleteAllAsynTask(CountryDatabase countryDatabase) {
            this.countryDao =countryDatabase.countryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }

}

