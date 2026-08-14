package com.example.mongodb.ui;

import com.example.mongodb.dao.OrderDAO;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class OrderManagementUI extends JFrame {

    private final OrderDAO orderDAO;

    private JTextField txtOrderId;
    private JTextField txtAddress;

    private JTable table;
    private DefaultTableModel tableModel;

    private JLabel lblStatus;

    public OrderManagementUI(OrderDAO orderDAO) {

        this.orderDAO = orderDAO;

        initializeUI();
    }

    private void initializeUI() {

        setTitle("E-Shop - Order Management");

        setSize(1000, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(new BorderLayout(10, 10));

        // =====================================================
        // TOP PANEL
        // =====================================================

        JPanel inputPanel =
                new JPanel(
                        new GridBagLayout()
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Order Information"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 5, 5, 5);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // Order ID

        gbc.gridx = 0;
        gbc.gridy = 0;

        inputPanel.add(
                new JLabel("Order ID:"),
                gbc
        );


        txtOrderId =
                new JTextField(10);

        gbc.gridx = 1;

        inputPanel.add(
                txtOrderId,
                gbc
        );


        // Address

        gbc.gridx = 2;

        inputPanel.add(
                new JLabel("Delivery Address:"),
                gbc
        );


        txtAddress =
                new JTextField(20);

        gbc.gridx = 3;

        inputPanel.add(
                txtAddress,
                gbc
        );


        add(
                inputPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(2, 4, 10, 10)
                );

        buttonPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Operations"
                )
        );


        JButton btnInsert =
                new JButton(
                        "Insert Orders"
                );

        JButton btnUpdate =
                new JButton(
                        "Update Address"
                );

        JButton btnDelete =
                new JButton(
                        "Delete Order"
                );

        JButton btnRead =
                new JButton(
                        "Read All Orders"
                );

        JButton btnCalculate =
                new JButton(
                        "Calculate Total"
                );

        JButton btnSomi =
                new JButton(
                        "Find SOMI"
                );

        JButton btnClear =
                new JButton(
                        "Clear Collection"
                );

        JButton btnExit =
                new JButton(
                        "Exit"
                );


        buttonPanel.add(btnInsert);

        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnRead);

        buttonPanel.add(btnCalculate);

        buttonPanel.add(btnSomi);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnExit);


        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.add(
                buttonPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "No",
                "Order ID",
                "Product ID",
                "Product Name",
                "Size",
                "Price",
                "Quantity",
                "Total"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };


        table =
                new JTable(tableModel);

        table.setRowHeight(25);

        table.getTableHeader()
                .setReorderingAllowed(false);


        JScrollPane scrollPane =
                new JScrollPane(table);


        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // STATUS
        // =====================================================

        lblStatus =
                new JLabel(
                        "Status: Ready"
                );

        lblStatus.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10
                )
        );


        add(
                lblStatus,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON EVENTS
        // =====================================================

        btnInsert.addActionListener(
                e -> insertOrders()
        );

        btnUpdate.addActionListener(
                e -> updateAddress()
        );

        btnDelete.addActionListener(
                e -> deleteOrder()
        );

        btnRead.addActionListener(
                e -> readAllOrders()
        );

        btnCalculate.addActionListener(
                e -> calculateTotal()
        );

        btnSomi.addActionListener(
                e -> calculateSomi()
        );

        btnClear.addActionListener(
                e -> clearCollection()
        );

        btnExit.addActionListener(
                e -> exitApplication()
        );
    }


    // =========================================================
    // INSERT
    // =========================================================

    private void insertOrders() {

        try {

            List<Document> orders =
                    createSampleOrders();

            orderDAO.clearCollection();

            orderDAO.insertMany(orders);

            lblStatus.setText(
                    "Status: Insert orders successfully!"
            );

            readAllOrders();

        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // CREATE SAMPLE DATA
    // =========================================================

    private List<Document> createSampleOrders() {

        Document product1 =
                new Document()
                        .append(
                                "product_id",
                                "quanau"
                        )
                        .append(
                                "product_name",
                                "quan au"
                        )
                        .append(
                                "size",
                                "XL"
                        )
                        .append(
                                "price",
                                10.0
                        )
                        .append(
                                "quantity",
                                1
                        );


        Document product2 =
                new Document()
                        .append(
                                "product_id",
                                "somi"
                        )
                        .append(
                                "product_name",
                                "ao so mi"
                        )
                        .append(
                                "size",
                                "XL"
                        )
                        .append(
                                "price",
                                10.5
                        )
                        .append(
                                "quantity",
                                2
                        );


        Document product3 =
                new Document()
                        .append(
                                "product_id",
                                "jean"
                        )
                        .append(
                                "product_name",
                                "quan jean"
                        )
                        .append(
                                "size",
                                "L"
                        )
                        .append(
                                "price",
                                20.0
                        )
                        .append(
                                "quantity",
                                1
                        );


        Document product4 =
                new Document()
                        .append(
                                "product_id",
                                "somi"
                        )
                        .append(
                                "product_name",
                                "ao so mi"
                        )
                        .append(
                                "size",
                                "M"
                        )
                        .append(
                                "price",
                                10.5
                        )
                        .append(
                                "quantity",
                                3
                        );


        Document order1 =
                new Document()
                        .append(
                                "orderId",
                                1
                        )
                        .append(
                                "products",
                                Arrays.asList(
                                        product1,
                                        product2
                                )
                        )
                        .append(
                                "total_amount",
                                31.0
                        )
                        .append(
                                "delivery_address",
                                "Hanoi"
                        );


        Document order2 =
                new Document()
                        .append(
                                "orderId",
                                2
                        )
                        .append(
                                "products",
                                Arrays.asList(
                                        product3
                                )
                        )
                        .append(
                                "total_amount",
                                20.0
                        )
                        .append(
                                "delivery_address",
                                "Haiphong"
                        );


        Document order3 =
                new Document()
                        .append(
                                "orderId",
                                3
                        )
                        .append(
                                "products",
                                Arrays.asList(
                                        product4
                                )
                        )
                        .append(
                                "total_amount",
                                31.5
                        )
                        .append(
                                "delivery_address",
                                "Danang"
                        );


        return Arrays.asList(
                order1,
                order2,
                order3
        );
    }


    // =========================================================
    // UPDATE ADDRESS
    // =========================================================

    private void updateAddress() {

        try {

            int orderId =
                    Integer.parseInt(
                            txtOrderId.getText()
                    );

            String address =
                    txtAddress.getText().trim();


            if (address.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter delivery address.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            boolean success =
                    orderDAO.updateDeliveryAddress(
                            orderId,
                            address
                    );


            if (success) {

                lblStatus.setText(
                        "Status: Update address successfully!"
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Delivery address updated.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Order ID not found.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }


            readAllOrders();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Order ID must be a number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // DELETE
    // =========================================================

    private void deleteOrder() {

        try {

            int orderId =
                    Integer.parseInt(
                            txtOrderId.getText()
                    );


            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete Order ID = "
                                    + orderId
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );


            if (choice != JOptionPane.YES_OPTION) {

                return;
            }


            boolean success =
                    orderDAO.deleteOrder(
                            orderId
                    );


            if (success) {

                lblStatus.setText(
                        "Status: Delete order successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Order ID not found.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }


            readAllOrders();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Order ID must be a number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // READ
    // =========================================================

    private void readAllOrders() {

        try {

            tableModel.setRowCount(0);

            List<Document> orders =
                    orderDAO.findAll();

            int no = 1;


            for (Document order : orders) {

                int orderId =
                        order.getInteger(
                                "orderId"
                        );


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
                            product.getString(
                                    "product_id"
                            );

                    String productName =
                            product.getString(
                                    "product_name"
                            );

                    String size =
                            product.getString(
                                    "size"
                            );

                    double price =
                            product.getDouble(
                                    "price"
                            );

                    int quantity =
                            product.getInteger(
                                    "quantity"
                            );

                    double total =
                            price * quantity;


                    tableModel.addRow(
                            new Object[]{
                                    no++,
                                    orderId,
                                    productId,
                                    productName,
                                    size,
                                    price,
                                    quantity,
                                    total
                            }
                    );
                }
            }


            lblStatus.setText(
                    "Status: Read all orders successfully!"
            );

        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // CALCULATE TOTAL
    // =========================================================

    private void calculateTotal() {

        try {

            List<Document> orders =
                    orderDAO.findAll();


            StringBuilder result =
                    new StringBuilder();


            double grandTotal = 0;


            for (Document order : orders) {

                int orderId =
                        order.getInteger(
                                "orderId"
                        );


                double total =
                        orderDAO.calculateTotalAmount(
                                order
                        );


                grandTotal += total;


                result.append(
                                "Order ID: "
                        )
                        .append(orderId)
                        .append(
                                " | Total: "
                        )
                        .append(
                                String.format(
                                        "%.2f",
                                        total
                                )
                        )
                        .append("\n");
            }


            result.append(
                            "\nGrand Total: "
                    )
                    .append(
                            String.format(
                                    "%.2f",
                                    grandTotal
                            )
                    );


            JOptionPane.showMessageDialog(
                    this,
                    result.toString(),
                    "Calculate Total",
                    JOptionPane.INFORMATION_MESSAGE
            );


            lblStatus.setText(
                    "Status: Calculate total successfully!"
            );

        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // FIND SOMI
    // =========================================================

    private void calculateSomi() {

        try {

            int total =
                    orderDAO.calculateTotalSomi();


            JOptionPane.showMessageDialog(
                    this,
                    "Total quantity of product_id = somi: "
                            + total,
                    "Find SOMI",
                    JOptionPane.INFORMATION_MESSAGE
            );


            lblStatus.setText(
                    "Status: Find SOMI successfully!"
            );

        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // CLEAR COLLECTION
    // =========================================================

    private void clearCollection() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete all orders?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );


        if (choice != JOptionPane.YES_OPTION) {

            return;
        }


        orderDAO.clearCollection();

        tableModel.setRowCount(0);

        lblStatus.setText(
                "Status: Collection cleared!"
        );
    }


    // =========================================================
    // EXIT
    // =========================================================

    private void exitApplication() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to exit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION
                );


        if (choice == JOptionPane.YES_OPTION) {

            dispose();

            System.exit(0);
        }
    }


    // =========================================================
    // ERROR
    // =========================================================

    private void showError(Exception ex) {

        ex.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}