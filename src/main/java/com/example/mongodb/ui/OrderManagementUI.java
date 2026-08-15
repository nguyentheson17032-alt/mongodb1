package com.example.mongodb.ui;

import com.example.mongodb.dao.OrderDAO;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class OrderManagementUI
        extends JFrame {


    private final OrderDAO orderDAO;


    private JTextField txtOrderId;

    private JTextField txtAddress;


    private JTable table;

    private DefaultTableModel tableModel;


    private JLabel lblStatus;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public OrderManagementUI(
            OrderDAO orderDAO
    ) {

        this.orderDAO = orderDAO;

        createGUI();
    }


    // =========================================================
    // CREATE GUI
    // =========================================================

    private void createGUI() {

        setTitle(
                "E-Shop Order Management"
        );


        setSize(
                950,
                650
        );


        setLocationRelativeTo(
                null
        );


        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );


        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );


        // =====================================================
        // TOP AREA
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );


        // =====================================================
        // INPUT PANEL
        // =====================================================

        JPanel inputPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );


        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Order Information"
                )
        );


        inputPanel.add(
                new JLabel(
                        "Order ID:"
                )
        );


        txtOrderId =
                new JTextField(
                        8
                );


        inputPanel.add(
                txtOrderId
        );


        inputPanel.add(
                new JLabel(
                        "New Delivery Address:"
                )
        );


        txtAddress =
                new JTextField(
                        20
                );


        inputPanel.add(
                txtAddress
        );


        topPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );


        buttonPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Operations"
                )
        );


        JButton btnInsert =
                new JButton(
                        "Insert Many"
                );


        JButton btnUpdate =
                new JButton(
                        "Edit Address"
                );


        JButton btnDelete =
                new JButton(
                        "Remove Order"
                );


        JButton btnRead =
                new JButton(
                        "Read Orders"
                );


        JButton btnCalculate =
                new JButton(
                        "Calculate Total"
                );


        JButton btnSomi =
                new JButton(
                        "Count SOMI"
                );


        Dimension buttonSize =
                new Dimension(
                        130,
                        40
                );


        btnInsert.setPreferredSize(
                buttonSize
        );


        btnUpdate.setPreferredSize(
                buttonSize
        );


        btnDelete.setPreferredSize(
                buttonSize
        );


        btnRead.setPreferredSize(
                buttonSize
        );


        btnCalculate.setPreferredSize(
                buttonSize
        );


        btnSomi.setPreferredSize(
                buttonSize
        );


        buttonPanel.add(
                btnInsert
        );


        buttonPanel.add(
                btnUpdate
        );


        buttonPanel.add(
                btnDelete
        );


        buttonPanel.add(
                btnRead
        );


        buttonPanel.add(
                btnCalculate
        );


        buttonPanel.add(
                btnSomi
        );


        topPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );


        add(
                topPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {

                "No",

                "Product Name",

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
                new JTable(
                        tableModel
                );


        table.setRowHeight(
                28
        );


        table.getTableHeader()
                .setReorderingAllowed(
                        false
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );


        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "OrderCollection"
                )
        );


        add(
                scrollPane,
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
                e -> insertMany()
        );


        btnUpdate.addActionListener(
                e -> updateAddress()
        );


        btnDelete.addActionListener(
                e -> removeOrder()
        );


        btnRead.addActionListener(
                e -> readOrders()
        );


        btnCalculate.addActionListener(
                e -> calculateTotal()
        );


        btnSomi.addActionListener(
                e -> countSomi()
        );
    }


    // =========================================================
    // CREATE SAMPLE DOCUMENT
    // =========================================================

    private Document createSampleOrder() {

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


        return new Document()
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
    }


    // =========================================================
    // INSERT MANY
    // =========================================================

    private void insertMany() {

        try {

            int orderId = 1;


            // Prevent duplicate order

            if (
                    orderDAO.orderExists(
                            orderId
                    )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Order ID 1 already exists!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );


                lblStatus.setText(
                        "Status: Order already exists!"
                );


                return;
            }


            Document order =
                    createSampleOrder();


            List<Document> orders =
                    List.of(
                            order
                    );


            orderDAO.insertMany(
                    orders
            );


            lblStatus.setText(
                    "Status: Insert successfully!"
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Insert order successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            readOrders();


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // UPDATE DELIVERY ADDRESS
    // =========================================================

    private void updateAddress() {

        try {

            int orderId =
                    getOrderId();


            String newAddress =
                    txtAddress
                            .getText()
                            .trim();


            if (
                    newAddress.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter new delivery address!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );


                return;
            }


            boolean success =
                    orderDAO
                            .updateDeliveryAddress(
                                    orderId,
                                    newAddress
                            );


            if (success) {

                lblStatus.setText(
                        "Status: Address updated!"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Delivery address updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                // Read again from MongoDB

                readOrders();


            } else {

                lblStatus.setText(
                        "Status: Order not found!"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Order ID "
                                + orderId
                                + " not found!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }


        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Order ID must be a number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // REMOVE ORDER
    // =========================================================

    private void removeOrder() {

        try {

            int orderId =
                    getOrderId();


            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Do you want to remove Order ID "
                                    + orderId
                                    + "?",
                            "Confirm Remove",
                            JOptionPane.YES_NO_OPTION
                    );


            if (
                    confirm
                            != JOptionPane.YES_OPTION
            ) {

                return;
            }


            boolean success =
                    orderDAO.deleteOrder(
                            orderId
                    );


            if (success) {

                lblStatus.setText(
                        "Status: Order removed!"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Order removed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                // Read again from MongoDB

                readOrders();


            } else {

                lblStatus.setText(
                        "Status: Order not found!"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Order ID "
                                + orderId
                                + " not found!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }


        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Order ID must be a number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // READ ALL ORDERS
    // =========================================================

    private void readOrders() {

        try {

            tableModel.setRowCount(
                    0
            );


            List<Document> orders =
                    orderDAO.findAll();


            int no = 1;


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

                    String productName =
                            product.getString(
                                    "product_name"
                            );


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


                    double price =
                            priceNumber == null
                                    ? 0
                                    : priceNumber.doubleValue();


                    int quantity =
                            quantityNumber == null
                                    ? 0
                                    : quantityNumber.intValue();


                    double total =
                            price * quantity;


                    tableModel.addRow(
                            new Object[]{

                                    no,

                                    productName,

                                    String.format(
                                            "%.2f",
                                            price
                                    ),

                                    quantity,

                                    String.format(
                                            "%.2f",
                                            total
                                    )
                            }
                    );


                    no++;
                }
            }


            lblStatus.setText(
                    "Status: Read orders successfully!"
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // CALCULATE TOTAL AMOUNT
    // =========================================================

    private void calculateTotal() {

        try {

            List<Document> orders =
                    orderDAO.findAll();


            if (
                    orders.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "No order found!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );


                return;
            }


            StringBuilder message =
                    new StringBuilder();


            double grandTotal = 0;


            for (
                    Document order :
                    orders
            ) {

                Number orderIdNumber =
                        order.get(
                                "orderId",
                                Number.class
                        );


                int orderId =
                        orderIdNumber == null
                                ? 0
                                : orderIdNumber.intValue();


                double total =
                        orderDAO
                                .calculateTotalAmount(
                                        order
                                );


                grandTotal += total;


                message
                        .append(
                                "Order ID: "
                        )
                        .append(
                                orderId
                        )
                        .append(
                                " | Total amount: "
                        )
                        .append(
                                String.format(
                                        "%.2f",
                                        total
                                )
                        )
                        .append(
                                "\n"
                        );
            }


            message
                    .append(
                            "\nTotal amount: "
                    )
                    .append(
                            String.format(
                                    "%.2f",
                                    grandTotal
                            )
                    );


            JOptionPane.showMessageDialog(
                    this,
                    message.toString(),
                    "Calculate Total Amount",
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
    // COUNT SOMI
    // =========================================================

    private void countSomi() {

        try {

            int total =
                    orderDAO.countSomi();


            JOptionPane.showMessageDialog(
                    this,
                    "Total quantity of product_id = somi: "
                            + total,
                    "Count SOMI",
                    JOptionPane.INFORMATION_MESSAGE
            );


            lblStatus.setText(
                    "Status: Count SOMI successfully!"
            );


        } catch (Exception ex) {

            showError(ex);
        }
    }


    // =========================================================
    // GET ORDER ID
    // =========================================================

    private int getOrderId()
            throws NumberFormatException {

        String text =
                txtOrderId
                        .getText()
                        .trim();


        if (
                text.isEmpty()
        ) {

            throw new NumberFormatException();
        }


        return Integer.parseInt(
                text
        );
    }


    // =========================================================
    // SHOW ERROR
    // =========================================================

    private void showError(
            Exception ex
    ) {

        ex.printStackTrace();


        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}