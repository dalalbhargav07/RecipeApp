package com.example.dalalbhargav07.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dalalbhargav07 on 07-04-2018.
 */

public class showRecipeDetail extends AppCompatActivity {

    private TextView name;
    private TextView desc;
    private TextView ingr;
    private TextView instr;
    private TextView rate;
    private TextView urL;
    private Button editbtn;

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
        urL = findViewById(R.id.tv_url);
        editbtn = findViewById(R.id.editbtn);

        recipedb.child(recipe_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String recipe_name = (String) dataSnapshot.child("recipe_name").getValue();
                String description = (String) dataSnapshot.child("description").getValue();
                String ingredients = (String) dataSnapshot.child("ingredients").getValue();
                String instructions = (String) dataSnapshot.child("instructions").getValue();
                String rte = (String) dataSnapshot.child("rate").getValue();
                String url = (String) dataSnapshot.child("url").getValue();


                name.setText(recipe_name);
                desc.setText(description);
                ingr.setText(ingredients);
                instr.setText(instructions);
                rate.setText(rte);
                urL.setText(url);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(showRecipeDetail.this,editRecipe.class);
                intent.putExtra("RecipeID",recipe_key);
                startActivity(intent);
            }
        });


    }
}
