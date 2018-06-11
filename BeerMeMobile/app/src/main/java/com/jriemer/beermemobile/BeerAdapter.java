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

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private Context mContext;
    private List<Beer> mBeer;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mBeerText;
        private ImageView mBeerImage;
        private CardView mBeerView;

        public ViewHolder(View itemView) {
            super(itemView);

            mBeerText = itemView.findViewById(R.id.beer_title);
            mBeerImage = itemView.findViewById(R.id.beer_image);
            mBeerView = itemView.findViewById(R.id.beerview);
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
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_beer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mBeerText.setText(mBeer.get(position).getBeer_name());
        Picasso.get().load(mBeer.get(position).getBeer_label()).into(holder.mBeerImage);

        holder.mBeerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,Beer_Activity.class);
                intent.putExtra("Image",mBeer.get(position).getBeer_label());
                intent.putExtra("Name",mBeer.get(position).getBeer_name());
                intent.putExtra("Style",mBeer.get(position).getStyle());
                intent.putExtra("Description",mBeer.get(position).getDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeer.size();
    }
}
