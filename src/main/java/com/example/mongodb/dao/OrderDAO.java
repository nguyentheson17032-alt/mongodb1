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


    public OrderDAO(
            MongoDBConnection connection
    ) {

        MongoDatabase database =
                connection.getDatabase();

        collection =
                database.getCollection(
                        "OrderCollection"
                );
    }


    // =========================================================
    // CHECK ORDER EXISTS
    // =========================================================

    public boolean orderExists(
            int orderId
    ) {

        return collection.countDocuments(
                Filters.eq(
                        "orderId",
                        orderId
                )
        ) > 0;
    }


    // =========================================================
    // INSERT MANY DOCUMENTS
    // =========================================================

    public void insertMany(
            List<Document> orders
    ) {

        collection.insertMany(
                orders
        );
    }


    // =========================================================
    // READ ALL ORDERS
    // =========================================================

    public List<Document> findAll() {

        return collection
                .find()
                .into(
                        new ArrayList<>()
                );
    }


    // =========================================================
    // UPDATE DELIVERY ADDRESS
    // =========================================================

    public boolean updateDeliveryAddress(
            int orderId,
            String newAddress
    ) {

        var result =
                collection.updateOne(
                        Filters.eq(
                                "orderId",
                                orderId
                        ),
                        Updates.set(
                                "delivery_address",
                                newAddress
                        )
                );


        System.out.println(
                "Update orderId = "
                        + orderId
        );

        System.out.println(
                "Matched: "
                        + result.getMatchedCount()
        );

        System.out.println(
                "Modified: "
                        + result.getModifiedCount()
        );


        return result.getMatchedCount() > 0;
    }


    // =========================================================
    // DELETE ORDER
    // =========================================================

    public boolean deleteOrder(
            int orderId
    ) {

        var result =
                collection.deleteOne(
                        Filters.eq(
                                "orderId",
                                orderId
                        )
                );


        System.out.println(
                "Delete orderId = "
                        + orderId
        );

        System.out.println(
                "Deleted: "
                        + result.getDeletedCount()
        );


        return result.getDeletedCount() > 0;
    }


    // =========================================================
    // CALCULATE TOTAL AMOUNT
    // =========================================================

    public double calculateTotalAmount(
            Document order
    ) {

        double total = 0;


        List<Document> products =
                order.getList(
                        "products",
                        Document.class
                );


        if (products == null) {

            return 0;
        }


        for (
                Document product :
                products
        ) {

            Number priceNumber =
                    product.get(
                            "price",
                            Number.class
                    );


            Number quantityNumber =
                    product.get(
                            "quantity",
                            Number.class
                    );


            if (
                    priceNumber == null
                            ||
                            quantityNumber == null
            ) {

                continue;
            }


            double price =
                    priceNumber.doubleValue();


            int quantity =
                    quantityNumber.intValue();


            total +=
                    price * quantity;
        }


        return total;
    }


    // =========================================================
    // COUNT PRODUCT ID = SOMI
    // =========================================================

    public int countSomi() {

        int totalQuantity = 0;


        List<Document> orders =
                findAll();


        for (
                Document order :
                orders
        ) {

            List<Document> products =
                    order.getList(
                            "products",
                            Document.class
                    );


            if (products == null) {

                continue;
            }


            for (
                    Document product :
                    products
            ) {

                String productId =
                        product.getString(
                                "product_id"
                        );


                if (
                        "somi".equals(
                                productId
                        )
                ) {

                    Number quantityNumber =
                            product.get(
                                    "quantity",
                                    Number.class
                            );


                    if (
                            quantityNumber != null
                    ) {

                        totalQuantity +=
                                quantityNumber.intValue();
                    }
                }
            }
        }


        return totalQuantity;
    }
}