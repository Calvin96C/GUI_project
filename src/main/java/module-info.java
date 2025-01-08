module com.wbs.gui_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wbs.gui_project to javafx.fxml;
    exports com.wbs.gui_project;
    exports com.wbs.gui_project.controller;
    opens com.wbs.gui_project.controller to javafx.fxml;
}