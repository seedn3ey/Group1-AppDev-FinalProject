module com.gabriel.twoforms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens com.gabriel.twoforms to javafx.fxml;
    exports com.gabriel.twoforms;
}