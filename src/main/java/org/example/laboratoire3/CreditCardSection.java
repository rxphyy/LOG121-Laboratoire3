package org.example.laboratoire3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreditCardSection {
    @FXML
    private TitledPane CreditCardSection;

    @FXML
    private TextField CreditCardNumberField;

    @FXML
    private TextField CreditCardExpirationField;

    @FXML
    private TextField CreditCardCVCField;

    public void showSection() {

        CreditCardSection.setVisible(true);
        CreditCardSection.setManaged(true);
    }

    public void hideSection() {
        CreditCardSection.setVisible(false);
        CreditCardSection.setManaged(false);
    }

    public TextField getCreditCardNumberField() {
        return CreditCardNumberField;
    }

    public TextField getCreditCardExpirationField() {
        return CreditCardExpirationField;
    }

    public TextField getCreditCardCVCField() {
        return CreditCardCVCField;
    }
}
