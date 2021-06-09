package com.api.RecipeApp.controller;

import com.api.RecipeApp.model.Recipe;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/recipe")
public class RecipeController {

    public RecipeController() {}

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {

        System.out.println(recipe);
        return null;
    }

    @GetMapping
    public Recipe getRecipes() {
        System.out.println("GET");
        return null;
    }

    @GetMapping(value = "/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        System.out.println(id);
        return null;
    }


}
