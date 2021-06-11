package com.api.RecipeApp.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.api.RecipeApp.model.Recipe;
import com.api.RecipeApp.utils.DynamoDBClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Recipe updateRecipe(String id, Recipe recipe) {
        AmazonDynamoDB dynamoDB = DynamoDBClient.getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

//        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
//        expectedAttributeValueMap.put("id", new ExpectedAttributeValue(new AttributeValue(id)));
//        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);

        mapper.save(recipe, new DynamoDBMapperConfig.Builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE).build());
        return recipe;
    }
}
