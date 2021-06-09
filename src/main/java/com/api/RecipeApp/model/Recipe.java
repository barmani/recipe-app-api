package com.api.RecipeApp.model;

import java.util.List;

public class Recipe {

    private String name;
    private List<Ingredient> ingredients;
    private String instructions;
    private int minutesToPrep;

    public Recipe(String name, List<Ingredient> ingredients, String instructions, int minutesToPrep) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.minutesToPrep = minutesToPrep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getMinutesToPrep() {
        return minutesToPrep;
    }

    public void setMinutesToPrep(int minutesToPrep) {
        this.minutesToPrep = minutesToPrep;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", minutesToPrep=" + minutesToPrep +
                '}';
    }
}
