package com.api.RecipeApp.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.api.RecipeApp.model.MealWeek;
import com.api.RecipeApp.model.Recipe;
import com.api.RecipeApp.utils.DynamoDBClient;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MealWeekService {

    public MealWeek createMealWeek(MealWeek meals) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        meals.setCreated(new Date());
        mapper.save(meals);
        return meals;
    }

    public List<MealWeek> getAllMealWeeks() {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.scan(MealWeek.class, new DynamoDBScanExpression().withConsistentRead(false));
    }

    public MealWeek getMealWeekById(String id) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.load(MealWeek.class, id);
    }
}
