module com.mycompany.conservatoire {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.conservatoire to javafx.fxml;
    exports com.mycompany.conservatoire;
    exports modele;
}
