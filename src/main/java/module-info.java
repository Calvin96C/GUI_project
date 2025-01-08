module com.wbs.gui_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wbs.gui_project to javafx.fxml;
    exports com.wbs.gui_project;
}