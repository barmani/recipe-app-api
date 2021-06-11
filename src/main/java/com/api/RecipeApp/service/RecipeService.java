package com.api.RecipeApp.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.api.RecipeApp.model.Recipe;
import com.api.RecipeApp.utils.DynamoDBClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    public Recipe createRecipe(Recipe recipe) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.save(recipe);
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.scan(Recipe.class, new DynamoDBScanExpression().withConsistentRead(false));
    }

    public Recipe getRecipe(String id) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.load(Recipe.class, id);
    }
}
