package com.sungkyu.discover.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungkyu.discover.R;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder>{

    private final LayoutInflater mInflater;

    public DiscoverAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = mInflater.inflate(R.layout.adapter_item, parent,false);
        return new ViewHolder(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView shopNameTextView;
        public TextView reviewTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            shopNameTextView = itemView.findViewById(R.id.name);
            reviewTextView = itemView.findViewById(R.id.type);
            // Added onClick event listener into each Review
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
