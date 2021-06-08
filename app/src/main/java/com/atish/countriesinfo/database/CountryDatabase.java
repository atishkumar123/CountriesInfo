package com.atish.countriesinfo.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.atish.countriesinfo.Dao.CountryDao;
import com.atish.countriesinfo.model.Country;

@Database(entities = {Country.class},version=2)
public abstract class CountryDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="CountryDatabase";

    public abstract CountryDao countryDao();

    private static volatile CountryDatabase INSTANCE;
    public static CountryDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (CountryDatabase.class){
                if(INSTANCE==null) {

                    INSTANCE= Room.databaseBuilder(context,CountryDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }


            }
        }
        return INSTANCE;
    }
    static RoomDatabase.Callback callback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };


    static class PopulateAsynTask extends AsyncTask<Void,Void,Void> {
        private  CountryDao countryDao;
        PopulateAsynTask(CountryDatabase countryDatabase){
            countryDao=countryDatabase.countryDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }

}
