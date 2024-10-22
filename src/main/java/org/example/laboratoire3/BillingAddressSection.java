package org.example.laboratoire3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BillingAddressSection {
    @FXML
    private TitledPane BillingAddressSection;

    @FXML
    private TextField BillingAddressTextField;

    public TextField getBillingAddressField() {
        return BillingAddressTextField;
    }
}
