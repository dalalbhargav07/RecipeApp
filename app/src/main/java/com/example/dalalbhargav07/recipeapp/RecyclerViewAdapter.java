package com.example.dalalbhargav07.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dalalbhargav07.recipeapp.model.createRecipe;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dalalbhargav07 on 07-04-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<createRecipe> infolist;

    public RecyclerViewAdapter(Context context, List<createRecipe> TempList) {

        this.infolist = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final createRecipe createRecipes = infolist.get(position);

        holder.recipeName.setText(createRecipes.getRecipe_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Context context = v.getContext();

                Intent intent = new Intent(context, showRecipeDetail.class);
                intent.putExtra("RecipeID",infolist.get(position).getRecipeID());
                                //And so on for the rest of the data that you want to pass to
                //the second activity

                context.startActivity(intent);
            }

        });

    }

    /*
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int
            position) {

        createRecipe createRecipes = infolist.get(position);

        holder.createRecipes.setText(businessDetailModel.businessName);
        holder.businessCity.setText(businessDetailModel.city);
        holder.businessRating.setText(businessDetailModel.ratingValue + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Context context = v.getContext();

                intent = new Intent(context, BusinessDetailActivity.class);
                intent.putExtra("Name",businessDetailModel.businessName);
                intent.putExtra("City",businessDetailModel.city);
                intent.putExtra("Rating",businessDetailModel.ratingValue);
                //And so on for the rest of the data that you want to pass to
                //the second activity

                context.startActivity(intent);
            }

        });*/


    @Override
    public int getItemCount() {

        return infolist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView recipeName;

        public ViewHolder(View itemView) {

            super(itemView);

            recipeName = (TextView) itemView.findViewById(R.id.tv_recipeName);

        }

    }
}