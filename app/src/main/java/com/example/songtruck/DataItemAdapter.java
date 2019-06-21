package com.example.songtruck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.DataViewHolder> {

    private onItemClickListener mListener;
    private ArrayList<ItemHolder> mItemList;

    public DataItemAdapter(ArrayList<ItemHolder> mItemList) {
        this.mItemList = mItemList;
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new DataViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        ItemHolder currentItem = mItemList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageProfile());
        holder.mTextViewName.setText(currentItem.getTextName());
        holder.mTextViewPrice.setText("Price:"+currentItem.getTextPrice());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {


        CircleImageView mImageView;
        TextView mTextViewName;
        TextView mTextViewPrice;

        DataViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.profile_image);
            mTextViewName = itemView.findViewById(R.id.textview_name);
            mTextViewPrice = itemView.findViewById(R.id.textview_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                            listener.onItemClick(pos);
                    }
                }
            });
        }
    }

}
