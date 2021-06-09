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

		String tableName = "Recipes";

		try {
			System.out.println("Attempting to create table");
			Table table = dynamoDB.createTable(tableName,
					Arrays.asList(new KeySchemaElement("name", KeyType.HASH)), // Partition
					Arrays.asList(new AttributeDefinition("name", ScalarAttributeType.S)),
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
