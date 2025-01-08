package com.wbs.gui_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HelloController
{
    @FXML
    private TextField tb_eingabe;

    @FXML
    private Label lbl_label1;

    @FXML
    protected void onHelloButtonClick()
    {
        String formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        tb_eingabe.setText(formattedTime + ": Welcome to your first GUI project");
        lbl_label1.setText(formattedTime + ": Welcome to your first GUI project");
    }
}