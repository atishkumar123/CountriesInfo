package com.atish.countriesinfo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "CountryInfo")
public class Country {
    @PrimaryKey(autoGenerate = true)
    private int countryId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "capital")
    private String capital;
    @ColumnInfo(name = "flag")
    private String flag;
    @ColumnInfo(name = "region")
    private String region;
    @ColumnInfo(name = "subRegion")
    private String subRegion;
    @ColumnInfo(name = "population")
    private String population;
    @ColumnInfo(name = "borders")
    private  String borders;
    @ColumnInfo(name = "languages")
    private String languages;

    public Country(String name, String capital, String flag, String region, String subRegion, String population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subregion) {
        this.subRegion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
