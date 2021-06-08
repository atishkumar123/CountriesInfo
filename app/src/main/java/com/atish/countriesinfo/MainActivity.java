package com.atish.countriesinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atish.countriesinfo.Adapter.CountryAdapter;
import com.atish.countriesinfo.model.Country;
import com.atish.countriesinfo.repository.CountryRepository;
import com.atish.countriesinfo.viewmodel.CountryViewModel;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CountryViewModel countryViewModel;
    private CountryRepository countryRepository;
    private RecyclerView recyclerView;
    public static List<Country> countryList=new ArrayList<>();
    private Country country;
    private CountryAdapter countryAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("All Country");
        recyclerView=findViewById(R.id.recyclerView);

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryAdapter=new CountryAdapter(MainActivity.this);
        recyclerView.setAdapter(countryAdapter);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager1);

//fetch data from given Api....

        fetchData2();


//set the to Adapter
        countryViewModel.getAllCountry().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countryList) {
                countryAdapter.setCountryList(countryList);


            }
        });



    }



    private void fetchData2() {
        String url ="https://restcountries.eu/rest/v2/region/Asia";

        StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray=new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String countryName = jsonObject.getString("name");
                                String capital = jsonObject.getString("capital");
                                String region = jsonObject.getString("region");
                                String subRegion = jsonObject.getString("subregion");
                                String population = jsonObject.getString("population");
                                JSONArray borders = jsonObject.getJSONArray("borders");
                                String border ="";
                                int len=borders.length();


                                for (int j = 0; j <( len <4?len:4); j++) {
                                    border += " ";
                                    border += borders.getString(j);

                                }
                                JSONArray languages = jsonObject.getJSONArray("languages");
                                String language = "";
                                for (int k = 0; k < languages.length(); k++) {
                                    language += languages.getJSONObject(k).getString("name");
                                    language += " ";
                                }
                                String flag = jsonObject.getString("flag");
                                Log.i("data2",flag);

                                country = new Country(countryName, capital, flag,region, subRegion, population, border, language);
                                countryList.add(country);
                            }
                            countryViewModel.insert(countryList);



                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Delete All data;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.clear_all){
            countryViewModel.deleteAll();
        }
        return super.onOptionsItemSelected(item);
    }
}