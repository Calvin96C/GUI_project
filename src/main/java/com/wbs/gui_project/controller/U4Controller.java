package com.wbs.gui_project.controller;

import com.wbs.gui_project.model.u4.Produkt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class U4Controller
{
    @FXML
    private TextField searchField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

    @FXML
    private TableView<Produkt> productTable;
    @FXML
    private TableColumn<Produkt, String> nameColumn;
    @FXML
    private TableColumn<Produkt, String> descriptionColumn;
    @FXML
    private TableColumn<Produkt, Integer> quantityColumn;
    @FXML
    private TableColumn<Produkt, Double> priceColumn;
    private final ObservableList<Produkt> products = FXCollections.observableArrayList();
    private FilteredList<Produkt> filteredProducts;


    @FXML
    public void initialize() {
        // Bind TableColumns to Product properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("beschreibung"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("menge"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("preis"));

        // Wrap the products list with a FilteredList
        filteredProducts = new FilteredList<>(products, p -> true);

        // Wrap the FilteredList in a SortedList
        SortedList<Produkt> sortedProducts = new SortedList<>(filteredProducts);

        // Bind the comparator of the SortedList to the TableView
        sortedProducts.comparatorProperty().bind(productTable.comparatorProperty());

        // Set the SortedList to the TableView
        productTable.setItems(sortedProducts);

        // Add a listener to the search field for filtering
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProducts.setPredicate(createFilter(newValue));
        });
    }

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

            // Add product to the ObservableList
            Produkt product = new Produkt(name, description, quantity, price);
            products.add(product);

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

    private Predicate<Produkt> createFilter(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            return product -> true; // Show all products
        }

        String lowerCaseFilter = searchText.toLowerCase();

        return product -> {
            // Filter by name, description, or other fields
            return product.getName().toLowerCase().contains(lowerCaseFilter)
                    || product.getBeschreibung().toLowerCase().contains(lowerCaseFilter)
                    || String.valueOf(product.getMenge()).contains(lowerCaseFilter)
                    || String.format("%.2f", product.getPreis()).contains(lowerCaseFilter);
        };
    }
}