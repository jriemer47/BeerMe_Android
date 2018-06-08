package com.jriemer.beermemobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private Context mContext;
    private List<Beer> mBeer;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mBeerText;
        private ImageView mBeerImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mBeerText = itemView.findViewById(R.id.beer_title);
            mBeerImage = itemView.findViewById(R.id.beer_image);
        }
    }

    public BeerAdapter(Context mContext, List<Beer> mBeer) {
        this.mContext = mContext;
        this.mBeer = mBeer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        view = mInflator.inflate(R.layout.cardview_item_beer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mBeerText.setText(mBeer.get(position).getBeer_name());
        Picasso.get().load(mBeer.get(position).getBeer_label()).into(holder.mBeerImage);
    }

    @Override
    public int getItemCount() {
        return mBeer.size();
    }
}
