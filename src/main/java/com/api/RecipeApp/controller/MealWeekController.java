package com.api.RecipeApp.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.api.RecipeApp.model.MealWeek;
import com.api.RecipeApp.model.Recipe;
import com.api.RecipeApp.service.MealWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/mealweek")
public class MealWeekController {

    private MealWeekService mealWeekService;

    @Autowired
    public MealWeekController(MealWeekService mealWeekService) {
        this.mealWeekService = mealWeekService;
    }

    @PostMapping
    public ResponseEntity createMealWeek(@RequestBody MealWeek meals) {
        try {
            MealWeek createdMealWeek = mealWeekService.createMealWeek(meals);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMealWeek);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity getAllMealWeeks() {
        try {
            List<MealWeek> mealWeeks = mealWeekService.getAllMealWeeks();
            return ResponseEntity.status(HttpStatus.OK).body(mealWeeks);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getMealWeekById(@PathVariable String id) {
        try {
            MealWeek mealWeek = mealWeekService.getMealWeekById(id);
            return ResponseEntity.status(HttpStatus.OK).body(mealWeek);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateMealWeek(@PathVariable String id, @RequestBody MealWeek mealWeek) {
        try {
            MealWeek updatedMealWeek = mealWeekService.updateMealWeek(mealWeek);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMealWeek);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMealWeek(@PathVariable String id) {
        try {
            MealWeek deletedMealWeek = mealWeekService.deleteMealWeek(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedMealWeek);
        } catch(AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

}
