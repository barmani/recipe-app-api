package com.api.RecipeApp.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.api.RecipeApp.model.Recipe;
import com.api.RecipeApp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Create a new recipe.
     * @param recipe
     * @return the created recipe
     */
    @PostMapping
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        try {
            Recipe createdRecipe = recipeService.createRecipe(recipe);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Scan the table to get all recipes.
     * @return list of recipes
     */
    @GetMapping
    public ResponseEntity getRecipes() {
        System.out.println("GET all");
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            return ResponseEntity.status(HttpStatus.OK).body(recipes);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Retrieve a recipe by id.
     * @param id
     * @return the intended recipe
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity getRecipe(@PathVariable String id) {
        try {
            System.out.println(id);
            Recipe recipe = recipeService.getRecipe(id);
            return ResponseEntity.status(HttpStatus.OK).body(recipe);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Send whole recipe to update.
     *
     * @param id
     * @param recipe
     * @return updated recipe
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity updateRecipe(@PathVariable String id, @RequestBody Recipe recipe) {
        System.out.println("PUT");
        try {
            System.out.println(id);
            Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
            return ResponseEntity.status(HttpStatus.OK).body(updatedRecipe);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteRecipe(@PathVariable String id) {
        System.out.println("DELETE");
        try {
            System.out.println(id);
            Recipe updatedRecipe = recipeService.deleteRecipe(id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedRecipe);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }


}
