package com.example.dalalbhargav07.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dalalbhargav07.recipeapp.model.createRecipe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText ingredients;
    EditText instructions;
    EditText url;

    EditText rating;

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
        String URL = url.getText().toString();

        if (!TextUtils.isEmpty(name_tv)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = recipedb.push().getKey();

            //creating an Artist Object
            createRecipe recipe = new createRecipe(id, name_tv,desc, ingr, instr,rate);

            //Saving the Artist
            recipedb.child(id).setValue(recipe);

            //setting edittext to blank again
            name.setText("");

            //displaying a success toast
            Toast.makeText(this, "Recipe added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, showRecipe_recyclerview.class);
            startActivity(intent);
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }

    }


}
