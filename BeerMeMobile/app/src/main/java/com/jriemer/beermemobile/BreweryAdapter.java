package com.jriemer.beermemobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BreweryAdapter extends RecyclerView.Adapter<BreweryAdapter.ViewHolder> {

    private Context mContext;
    private List<Brewery> mBrewery;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mBreweryText;
        private ImageView mBreweryImage;
        private CardView mBreweryView;

        public ViewHolder(View itemView) {
            super(itemView);

            mBreweryText = itemView.findViewById(R.id.brewery_name);
            mBreweryImage = itemView.findViewById(R.id.brewery_image);
            mBreweryView = itemView.findViewById(R.id.breweryview);
        }
    }

    public BreweryAdapter(Context mContext, List<Brewery> mBrewery) {
        this.mContext = mContext;
        this.mBrewery = mBrewery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_brewery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mBreweryText.setText(mBrewery.get(position).getBrewery_name());
        Picasso.get().load(mBrewery.get(position).getBrewery_logo()).into(holder.mBreweryImage);

        holder.mBreweryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Brewery_Activity.class);
                intent.putExtra("BreweryName", mBrewery.get(position).getBrewery_name());
                intent.putExtra("City", mBrewery.get(position).getCity());
                intent.putExtra("State", mBrewery.get(position).getState());
                intent.putExtra("Address", mBrewery.get(position).getAddress());
                intent.putExtra("Phone", mBrewery.get(position).getPhone());
                intent.putExtra("Url", mBrewery.get(position).getUrl());
                intent.putExtra("Image", mBrewery.get(position).getBrewery_logo());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBrewery.size();
    }
}