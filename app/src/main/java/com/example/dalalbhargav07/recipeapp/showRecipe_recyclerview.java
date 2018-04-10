package com.example.dalalbhargav07.recipeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dalalbhargav07.recipeapp.model.createRecipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dalalbhargav07 on 07-04-2018.
 */

//https://www.android-examples.com/show-firebase-database-data-into-recyclerview/

public class showRecipe_recyclerview extends AppCompatActivity {

    DatabaseReference recipedb;

    ProgressDialog progressDialog;


    List<createRecipe> list = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(showRecipe_recyclerview.this));

        progressDialog = new ProgressDialog(showRecipe_recyclerview.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();


        recipedb = FirebaseDatabase.getInstance().getReference("recipe");

        recipedb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    createRecipe createrecipes = dataSnapshot.getValue(createRecipe.class);

                    list.add(createrecipes);
                }

                adapter = new RecyclerViewAdapter(showRecipe_recyclerview.this, list);

                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

    }
}
