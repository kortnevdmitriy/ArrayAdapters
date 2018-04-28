package com.valentishealth.app.presshub.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.valentishealth.app.presshub.R;
import com.valentishealth.app.presshub.model.Modelist;

import java.util.List;

/**
 * Created by Hp on 4/13/2018.
 */

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {
    private List<Modelist> mDataList;
    private Context context;


    public ModelAdapter(Context context, List<Modelist> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mDataList.get(position).getName());
        holder.tv_rating.setText(mDataList.get(position).getRating());
        holder.tv_studio.setText(mDataList.get(position).getStudio());
        holder.tv_category.setText(mDataList.get(position).getCategories());
        holder.tv_name.setText(mDataList.get(position).getName());
        holder.tv_desc.setText(mDataList.get(position).getDescription());
        //load image from the iternet and set it into imageview using glide

        Glide.with(context).load(mDataList.get(position).getImg()).apply(RequestOptions.centerCropTransform()).into(holder.img_thumbanil);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_desc;
        TextView tv_rating;
        TextView tv_studio;
        TextView tv_category;
        ImageView img_thumbanil;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.txtAnimeTitle);
            tv_category = itemView.findViewById(R.id.txtCategories);
            tv_desc = itemView.findViewById(R.id.txtDescription);
            tv_rating = itemView.findViewById(R.id.txtRating);
            tv_studio = itemView.findViewById(R.id.txtStudio);
            img_thumbanil = itemView.findViewById(R.id.thumbnail);
        }

    }

}
