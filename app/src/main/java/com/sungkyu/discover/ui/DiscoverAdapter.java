package com.sungkyu.discover.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private final String TAG = this.getClass().getSimpleName();
    private final LayoutInflater mInflater;
    private List<Restaurant> mRestaurant;
    private MainActivity mMainActivity;
    private ItemClickListener mItemClickListener;
    private int mCheckedPostion = -1;

    public DiscoverAdapter(MainActivity context) {
        mInflater = LayoutInflater.from(context);
        mRestaurant = new ArrayList<>();
        mMainActivity = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = mInflater.inflate(R.layout.adapter_item, parent,false);
        return new ViewHolder(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(mRestaurant.isEmpty()) return;
        try {
            Restaurant restaurant = mRestaurant.get(position);
            holder.reviewTextView.setText(restaurant.getDescription());
            holder.shopNameTextView.setText(restaurant.getName());
            holder.distanceTextView.setText(restaurant.getStatus());

            holder.checkBoxView.setChecked(restaurant.isFavorite());
            holder.checkBoxView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    restaurant.setFavorite(holder.checkBoxView.isChecked());
                    Log.d(TAG, "isChecked : " + " to position " + position + ", isChecked : " + holder.checkBoxView.isChecked());
                    mMainActivity.saveFavorite(restaurant);
                }
            });
            Glide.with(mMainActivity).load(restaurant.getImgUrl()).into(holder.imageView);
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
        public CheckBox checkBoxView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            shopNameTextView = itemView.findViewById(R.id.name);
            reviewTextView = itemView.findViewById(R.id.type);
            distanceTextView = itemView.findViewById(R.id.status);
            checkBoxView = itemView.findViewById(R.id.checker);
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
