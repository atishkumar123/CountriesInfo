package com.atish.countriesinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atish.countriesinfo.model.Country;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import static com.atish.countriesinfo.MainActivity.countryList;
public class Country_detailsActivity extends AppCompatActivity {
int countryPosition;
ImageView imageView;
TextView countryname,capital,region,subregion,population,borders,languages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        Intent intent=getIntent();
        countryPosition=intent.getIntExtra("position",0);
        Country country=countryList.get(countryPosition);

        getSupportActionBar().setTitle(country.getName()+" Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //find all view by their id

        imageView=findViewById(R.id.countryImage);
        countryname=findViewById(R.id.countryName);
        capital=findViewById(R.id.capital);
        region=findViewById(R.id.region);
        subregion=findViewById(R.id.subregion);
        population=findViewById(R.id.population);
        borders=findViewById(R.id.border);
        languages=findViewById(R.id.languages);
        //set text
        countryname.setText(country.getName());
        capital.setText(country.getCapital());
        region.setText(country.getRegion());
        subregion.setText(country.getSubRegion());
        population.setText(country.getPopulation());
        borders.setText(country.getBorders());
        languages.setText(country.getLanguages());




        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(this)
                .getRequestBuilder();
        requestBuilder
                .load(country.getFlag())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(imageView);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}