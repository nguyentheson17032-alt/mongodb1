package com.example.mongodb.dao;

import com.example.mongodb.config.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final MongoCollection<Document> collection;

    public OrderDAO(MongoDBConnection connection) {

        MongoDatabase database =
                connection.getDatabase();

        collection =
                database.getCollection("OrderCollection");
    }

    // Clear all orders
    public void clearCollection() {

        collection.deleteMany(new Document());
    }

    // Insert many orders
    public void insertMany(List<Document> orders) {

        collection.insertMany(orders);
    }

    // Update delivery address
    public boolean updateDeliveryAddress(
            int orderId,
            String newAddress
    ) {

        var result = collection.updateOne(
                Filters.eq("orderId", orderId),
                Updates.set(
                        "delivery_address",
                        newAddress
                )
        );

        return result.getModifiedCount() > 0;
    }

    // Delete order
    public boolean deleteOrder(int orderId) {

        var result = collection.deleteOne(
                Filters.eq("orderId", orderId)
        );

        return result.getDeletedCount() > 0;
    }

    // Get all orders
    public List<Document> findAll() {

        return collection
                .find()
                .into(new ArrayList<>());
    }

    // Calculate total amount of one order
    public double calculateTotalAmount(
            Document order
    ) {

        double totalAmount = 0;

        List<Document> products =
                order.getList(
                        "products",
                        Document.class
                );

        if (products == null) {
            return 0;
        }

        for (Document product : products) {

            double price =
                    product.getDouble("price");

            int quantity =
                    product.getInteger("quantity");

            totalAmount +=
                    price * quantity;
        }

        return totalAmount;
    }

    // Calculate total quantity of product_id = somi
    public int calculateTotalSomi() {

        int totalQuantity = 0;

        for (Document order : collection.find()) {

            List<Document> products =
                    order.getList(
                            "products",
                            Document.class
                    );

            if (products == null) {
                continue;
            }

            for (Document product : products) {

                String productId =
                        product.getString("product_id");

                if ("somi".equals(productId)) {

                    int quantity =
                            product.getInteger("quantity");

                    totalQuantity += quantity;
                }
            }
        }

        return totalQuantity;
    }
}