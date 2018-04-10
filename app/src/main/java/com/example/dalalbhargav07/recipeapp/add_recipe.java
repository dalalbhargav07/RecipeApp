package com.example.dalalbhargav07.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dalalbhargav07.recipeapp.model.createRecipe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by dalalbhargav07 on 09-04-2018.
 */

public class add_recipe extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText ingredients;
    EditText instructions;
    EditText url;

    EditText rating;

    //RatingBar ratebar;
    Button save;

    DatabaseReference recipedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);

        recipedb = FirebaseDatabase.getInstance().getReference("recipe");

        name = findViewById(R.id.et_name);
        description = findViewById(R.id.et_desc);
        ingredients = findViewById(R.id.et_ingredients);
        instructions = findViewById(R.id.et_instruction);
        save = findViewById(R.id.savebtn);

        rating = findViewById(R.id.et_rating);
        //ratebar = findViewById(R.id.ratingBar);
        url = findViewById(R.id.et_url);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecipe();
            }
        });
    } // end of oncreate

    private void addRecipe() {

        String name_tv = name.getText().toString();
        String desc = description.getText().toString();
        String ingr = ingredients.getText().toString();
        String instr = instructions.getText().toString();
        String rate = rating.getText().toString();
        //Float rate = ratebar.getRating();
        //String rte = String.valueOf(rate);
        String URL = url.getText().toString();

        Float rate_check  = Float.parseFloat(rate);

        if (rate_check >= 0 && rate_check <= 5) { //Validation for rate between 0 to 5
            if (!TextUtils.isEmpty(name_tv) || !TextUtils.isEmpty(desc) || !TextUtils.isEmpty(ingr) || !TextUtils.isEmpty(instr)) {
            //Validation for any field is empty or not.


                //getting a unique id using push().getKey() method
                //it will create a unique id and we will use it as the Primary Key for our Artist
                String id = recipedb.push().getKey();

                //creating an Artist Object
                createRecipe recipe = new createRecipe(id, name_tv, desc, ingr, instr, rate, URL);

                //Saving the Artist
                recipedb.child(id).setValue(recipe);

                //setting edittext to blank again
                name.setText("");

                //displaying a success toast
                Toast.makeText(this, "Recipe added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(add_recipe.this, showRecipe_recyclerview.class);
                startActivity(intent);
            } else {
                //if the value is not given displaying a toast
                Toast.makeText(this, "Please do not keep any field empty except URL", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Rating should be between 0 to 5", Toast.LENGTH_SHORT).show();
        }
    }

}
