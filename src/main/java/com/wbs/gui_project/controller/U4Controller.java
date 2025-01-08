package com.wbs.gui_project.controller;

import com.wbs.gui_project.model.u4.Produkt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class U4Controller
{
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private ListView<String> inventoryList;

    private final List<Produkt> products = new ArrayList<>();

    @FXML
    protected void produktHinzufuegen() {
        try {
            String name = nameField.getText();
            String description = descriptionField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            // Validate inputs
            if (name.isEmpty() || description.isEmpty() || quantity < 0 || price < 0) {
                throw new IllegalArgumentException("Invalid input values.");
            }

            // Add product to the list
            Produkt product = new Produkt(name, description, quantity, price);
            products.add(product);

            // Update inventory list view
            inventoryList.getItems().clear();
            for (Produkt p : products) {
                inventoryList.getItems().add(p.toString());
            }

            // Clear input fields
            nameField.clear();
            descriptionField.clear();
            quantityField.clear();
            priceField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numeric values for Quantity and Price.");
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Input", e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}