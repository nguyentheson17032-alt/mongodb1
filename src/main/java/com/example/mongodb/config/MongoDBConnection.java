package com.example.mongodb.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String URI =
            "mongodb://localhost:27017";

    private static final String DATABASE_NAME =
            "eShop";

    private final MongoClient mongoClient;

    public MongoDBConnection() {

        mongoClient = MongoClients.create(URI);

        System.out.println(
                "MongoDB connection created."
        );
    }

    public MongoDatabase getDatabase() {

        return mongoClient.getDatabase(
                DATABASE_NAME
        );
    }

    public void close() {

        if (mongoClient != null) {

            mongoClient.close();

            System.out.println(
                    "MongoDB connection closed."
            );
        }
    }
}