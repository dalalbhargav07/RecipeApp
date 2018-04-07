package com.example.dalalbhargav07.recipeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dalalbhargav07 on 07-04-2018.
 */

public class showRecipeDetail extends AppCompatActivity {

    private TextView name;
    private TextView desc;
    private TextView ingr;
    private TextView instr;
    private TextView rate;
    private TextView URL;

    private  String recipe_key = null;

    private DatabaseReference recipedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_recipe_page);

        recipe_key = getIntent().getExtras().getString("RecipeID");

        recipedb= FirebaseDatabase.getInstance().getReference().child("recipe");

        name= findViewById(R.id.tv_name);
        desc= findViewById(R.id.tv_desc);
        ingr = findViewById(R.id.tv_ingredients);
        instr = findViewById(R.id.tv_instruction);
        rate = findViewById(R.id.tv_rating);
        URL = findViewById(R.id.tv_url);

        recipedb.child(recipe_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String recipe_name = (String) dataSnapshot.child("recipe_name").getValue();
                String description = (String) dataSnapshot.child("description").getValue();
                String ingredients = (String) dataSnapshot.child("ingredients").getValue();
                String instructions = (String) dataSnapshot.child("instructions").getValue();
                String rte = (String) dataSnapshot.child("rate").getValue();
                String url = (String) dataSnapshot.child("URL").getValue();

                name.setText(recipe_name);
                desc.setText(description);
                ingr.setText(ingredients);
                instr.setText(instructions);
                rate.setText(rte);
                URL.setText(url);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
