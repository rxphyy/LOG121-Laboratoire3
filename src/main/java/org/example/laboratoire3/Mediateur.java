package org.example.laboratoire3;

import javafx.scene.control.TextField;

public interface Mediateur {
    void initialize();
    void handlePaymentModeChange(int index);
    void handleDeliveryModeChange(int index);
    void handleSameAdressesChange();
    void displayErrorMessage(String field);
    void hideErrorMessage();
    void validateField(TextField textField, String regex, String champ);
}
