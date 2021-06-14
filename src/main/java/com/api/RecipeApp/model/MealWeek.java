package com.api.RecipeApp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.*;

@DynamoDBTable(tableName = "MealWeeks")
public class MealWeek {

    private String id;
    private Map<String, List<String>> dayToRecipeIDs;
    private Date created;


    public MealWeek() {}

    public MealWeek(String id, Map<String, List<String>> dayToRecipeIDs) {
        this.id = id;
        this.dayToRecipeIDs = dayToRecipeIDs;
        this.created = new Date();
    }

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, List<String>> getDayToRecipeIDs() {
        return dayToRecipeIDs;
    }

    public void setDayToRecipeIDs(Map<String, List<String>> dayToRecipeIDs) {
        this.dayToRecipeIDs = dayToRecipeIDs;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}