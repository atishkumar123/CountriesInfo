package com.atish.countriesinfo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

;
import com.atish.countriesinfo.Country_detailsActivity;
import com.atish.countriesinfo.R;
import com.atish.countriesinfo.model.Country;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;


import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    List<Country> countryList;
    Context context;

    public CountryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.country_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Country country=countryList.get(position);
        Log.i("name4",country.getRegion());


        holder.countryName.setText(country.getName());
        holder.capital.setText(country.getCapital());
//        Glide.with(context)
//                .load(country.getFlag())
////                .listener(new RequestListener<Drawable>() {
////                    @Override
////                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
////                        //progressBar.setVisibility(View.GONE);
////                        Log.i("name","atish");
////                        return false;
////                    }
////
////                    @Override
////                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
////                        //progressBar.setVisibility(View.GONE);
////                        Log.i("name","nitish");
////                        return false;
////                    }
////                }).transition(DrawableTransitionOptions.withCrossFade())
//                .into(holder.imageView);
        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(context)
                .getRequestBuilder();
        requestBuilder
                .load(country.getFlag())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(holder.imageView);

         holder.country_layout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, Country_detailsActivity.class);
                 intent.putExtra("position", position);
                 context.startActivity(intent);

             }
         });

    }

    @Override
    public int getItemCount() {
        if(countryList!=null) {
            return countryList.size();
        }else{
            return 0;
        }
    }
    public void setCountryList(List<Country>countryList){
        this.countryList=countryList;
        notifyDataSetChanged();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  countryName,capital;
       LinearLayout country_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageFlag);
            countryName=itemView.findViewById(R.id.countryName);
            country_layout=itemView.findViewById(R.id.country_layout);
            capital=itemView.findViewById(R.id.capital);
        }
    }
}
