package com.sungkyu.discover.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sungkyu.discover.R;
import com.sungkyu.discover.db.Entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder>{

    private final LayoutInflater mInflater;
    private List<Restaurant> mRestaurant;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public DiscoverAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mRestaurant = new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = mInflater.inflate(R.layout.adapter_item, parent,false);
        return new ViewHolder(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mRestaurant.isEmpty()) return;
        try {
            Restaurant restaurant = mRestaurant.get(position);
            holder.reviewTextView.setText(restaurant.getDescription());
            holder.shopNameTextView.setText(restaurant.getName());
            holder.distanceTextView.setText(restaurant.getStatus());
            Glide.with(mContext).load(restaurant.getImgUrl()).into(holder.imageView);
        } catch (Exception e) {
            // if Url is empty
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mRestaurant.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView shopNameTextView;
        public TextView reviewTextView;
        public TextView distanceTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            shopNameTextView = itemView.findViewById(R.id.name);
            reviewTextView = itemView.findViewById(R.id.type);
            distanceTextView = itemView.findViewById(R.id.status);
            // Added onClick event listener into each Review
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) mItemClickListener.onItemClick(mRestaurant.get(getAdapterPosition()));
        }
    }

    public void addRestaurant(List<Restaurant> restaurants) {
        mRestaurant.addAll(restaurants);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
    }

    /*
     * Interface to notify the clicked item.
     */
    public interface ItemClickListener {
        void onItemClick(Restaurant r);
    }
}
