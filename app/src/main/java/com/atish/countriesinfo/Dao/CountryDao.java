package com.atish.countriesinfo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.atish.countriesinfo.model.Country;

import java.util.List;
@Dao
public interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Country> actorList);
    @Query("SELECT * FROM countryinfo")
    LiveData<List<Country>> getAllActors();
    @Query("DELETE FROM CountryInfo")
    void deleteAll();
}
