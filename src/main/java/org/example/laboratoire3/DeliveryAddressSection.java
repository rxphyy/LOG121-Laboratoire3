package org.example.laboratoire3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DeliveryAddressSection {
    @FXML
    private TitledPane DeliveryAddressSection;

    @FXML
    private TextField DeliveryAddressTextField;

    @FXML
    private RadioButton SameAdressesCheckbox;

    public RadioButton getSameAdressesCheckbox() {
        return SameAdressesCheckbox;
    }

    public TextField getDeliveryAddressField() {
        return DeliveryAddressTextField;
    }
}
