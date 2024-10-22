package org.example.laboratoire3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GiftCardSection {
    @FXML
    private TitledPane GiftCardSection;

    @FXML
    private TextField GiftCardNumberField;

    public void showSection() {
        GiftCardSection.setVisible(true);
        GiftCardSection.setManaged(true);
    }

    public void hideSection() {
        GiftCardSection.setVisible(false);
        GiftCardSection.setManaged(false);
    }

    public TextField getGiftCardNumberField() {
        return GiftCardNumberField;
    }
}
