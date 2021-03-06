package com.api.RecipeApp.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
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

    public MealWeek updateMealWeek(MealWeek mealWeek) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        // current values
        MealWeek currentMealWeek = mapper.load(MealWeek.class, mealWeek.getId());
        MealWeek updatedMealWeek = new MealWeek();
        updatedMealWeek.setId(mealWeek.getId());
        updatedMealWeek.setDayToRecipeIDs(mealWeek.getDayToRecipeIDs());
        updatedMealWeek.setCreated(currentMealWeek.getCreated());
        mapper.save(updatedMealWeek, new DynamoDBMapperConfig.Builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE).build());
        return updatedMealWeek;
    }

    public MealWeek deleteMealWeek(String id) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        MealWeek toDelete = new MealWeek();
        toDelete.setId(id);
        mapper.delete(toDelete);
        return toDelete;
    }
}
