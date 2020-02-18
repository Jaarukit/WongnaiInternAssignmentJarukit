package com.example.wongnaiinternassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wongnaiinternassignment.Models.Coin;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Coin> mCoin;
    private Context mContext;

    public Adapter(Context context, List<Coin> coin) {
        mContext = context;
        this.mCoin = coin;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName, mTextViewDescription;
        public ImageView mImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.coinName);
            mTextViewDescription = itemView.findViewById(R.id.coinDescription);
            mImageView = itemView.findViewById(R.id.coinImage);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mTextViewName.setText(mCoin.get(position).getmName());
        holder.mTextViewDescription.setText(mCoin.get(position).getmDescription());
        GlideApp.with(mContext)
                .load(mCoin.get(position).getmImageURL())
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mCoin.size();
    }

}
