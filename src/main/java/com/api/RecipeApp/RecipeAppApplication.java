package com.api.RecipeApp;

import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

@SpringBootApplication
public class RecipeAppApplication {

	public static void main(String[] args) {
		System.out.println("Revving up!");

		createDynamodbTables();
		SpringApplication.run(RecipeAppApplication.class, args);
	}

	public static void createDynamodbTables() {

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-1"))
				.build();

		DynamoDB dynamoDB = new DynamoDB(client);

		String recipeTable = "Recipes";
		String mealWeeksTable = "MealWeeks";

		// Create Recipes Table
		try {
			System.out.println("Attempting to create recipes table");
			Table table = dynamoDB.createTable(recipeTable,
					Arrays.asList(new KeySchemaElement("id", KeyType.HASH)), // Partition
					Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
					new ProvisionedThroughput(10L, 10L));
			table.waitForActive();
			System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
		}
		catch (Exception e) {
			if (e instanceof ResourceInUseException) {
				System.err.println("Table already created");
			} else {
				System.err.println("Unable to create table: ");
				System.err.println(e.getMessage());
			}
		}

		// Create MealWeeks Table
		try {
			System.out.println("Attempting to create mealweeks table");
			Table table = dynamoDB.createTable(mealWeeksTable,
					Arrays.asList(new KeySchemaElement("id", KeyType.HASH)), // Partition
					Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
					new ProvisionedThroughput(10L, 10L));
			table.waitForActive();
			System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
		}
		catch (Exception e) {
			if (e instanceof ResourceInUseException) {
				System.err.println("Table already created");
			} else {
				System.err.println("Unable to create table: ");
				System.err.println(e.getMessage());
			}
		}

	}

}
