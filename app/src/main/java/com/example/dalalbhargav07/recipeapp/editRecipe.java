package com.example.dalalbhargav07.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by dalalbhargav07 on 07-04-2018.
 */

public class editRecipe extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText ingredients;
    EditText instructions;
    EditText url;
    private String recipe_key = null;

    EditText rating;

    //RatingBar ratebar;
    Button update;
    Button delete;

    DatabaseReference recipedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        recipe_key = getIntent().getExtras().getString("RecipeID");

        recipedb = FirebaseDatabase.getInstance().getReference().child("recipe");

        name = findViewById(R.id.et_name);
        description = findViewById(R.id.et_desc);
        ingredients = findViewById(R.id.et_ingredients);
        instructions = findViewById(R.id.et_instruction);
        update = findViewById(R.id.savebtn);
        delete = findViewById(R.id.del);

        rating = findViewById(R.id.et_rating);
        url = findViewById(R.id.et_url);

        recipedb.child(recipe_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String recipe_name = (String) dataSnapshot.child("recipe_name").getValue();
                String desc = (String) dataSnapshot.child("description").getValue();
                String ingr = (String) dataSnapshot.child("ingredients").getValue();
                String instr = (String) dataSnapshot.child("instructions").getValue();
                String rte = (String) dataSnapshot.child("rate").getValue();
                String link = (String) dataSnapshot.child("url").getValue();

                try {
                    URL myURL = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                name.setText(recipe_name);
                description.setText(desc);
                ingredients.setText(ingr);
                instructions.setText(instr);
                rating.setText(rte);
                url.setText(link);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_tv = name.getText().toString();
                String desc_tv = description.getText().toString();
                String ingr_tv = ingredients.getText().toString();
                String instr_tv = instructions.getText().toString();
                String rate_tv = rating.getText().toString();
                String URL_tv = url.getText().toString();

                Float rate_check  = Float.parseFloat(rate_tv);
                //Toast.makeText(this, rate_check.toString(), Toast.LENGTH_SHORT).show();
                if (rate_check >= 0 && rate_check <= 5) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("recipe_name", name_tv);
                    result.put("description", desc_tv);
                    result.put("ingredients", ingr_tv);
                    result.put("instructions", instr_tv);
                    result.put("rate", rate_tv);
                    result.put("url", URL_tv);
                    FirebaseDatabase.getInstance().getReference().child("recipe").child(recipe_key).updateChildren(result);
                    Toast.makeText(editRecipe.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(editRecipe.this,showRecipe_recyclerview.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(editRecipe.this, "Rating should be between 0 to 5", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipedb.child(recipe_key).removeValue();
                Intent intent = new Intent(editRecipe.this,showRecipe_recyclerview.class);
                startActivity(intent);
            }
        });

    }
}
