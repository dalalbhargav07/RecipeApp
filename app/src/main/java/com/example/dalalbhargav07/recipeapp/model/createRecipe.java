package com.example.dalalbhargav07.recipeapp.model;

import java.io.Serializable;

/**
 * Created by dalalbhargav07 on 05-04-2018.
 */

public class createRecipe {
    private String recipeID;

    private String recipe_name;

    private String description;

    private String ingredients;

    private String instructions;

    private String rate;


    public createRecipe() {}

    public createRecipe(String recipeID, String recipe_name, String description, String ingredients, String instructions, String rate) {

        this.recipeID = recipeID;
        this.recipe_name = recipe_name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.rate = rate;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }



    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }
}
