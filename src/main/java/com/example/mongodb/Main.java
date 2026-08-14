package com.example.mongodb;

import com.example.mongodb.config.MongoDBConnection;
import com.example.mongodb.dao.OrderDAO;
import com.example.mongodb.ui.OrderManagementUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        MongoDBConnection connection =
                new MongoDBConnection();

        OrderDAO orderDAO =
                new OrderDAO(connection);


        SwingUtilities.invokeLater(() -> {

            OrderManagementUI ui =
                    new OrderManagementUI(
                            orderDAO
                    );

            ui.setVisible(true);
        });
    }
}