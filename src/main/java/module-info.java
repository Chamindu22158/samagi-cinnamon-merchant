module ijse.edu.main.samagicinnamonmerchant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires static lombok;
    requires com.jfoenix;
    requires jasperreports;


    opens ijse.edu.main.samagicinnamonmerchant.controller to javafx.fxml;
    opens ijse.edu.main.samagicinnamonmerchant.dto.tm to javafx.base;
    exports ijse.edu.main.samagicinnamonmerchant;
}
