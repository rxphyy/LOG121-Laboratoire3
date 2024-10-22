package org.example.laboratoire3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

import java.util.List;

public class MediateurFormulairePaiement implements Mediateur {
    private final String CARD_NUMBER_REGEX = "^[0-9]{16}$"; // 16 chiffres
    private final String EXP_DATE_REGEX = "^(0[1-9]|1[0-2])/([0-9]{2})$"; // MM/YY
    private final String CVC_REGEX = "^[0-9]{3}$"; // 3 chiffres
    private final String GIFT_CARD_REGEX = "^[0-9]{16}$"; // 16 chiffres

    private FormulairePaiement formulaire;
    private CreditCardSection creditCardSection;
    private GiftCardSection giftCardSection;
    private BillingAddressSection billingAddressSection;
    private DeliveryAddressSection deliveryAddressSection;
    private Label ErrorLabel;

    public MediateurFormulairePaiement(FormulairePaiement formulairePaiement, GiftCardSection giftCardSection, CreditCardSection creditCardSection,
                                       DeliveryAddressSection deliveryAddressSection, BillingAddressSection billingAddressSection, Label errorLabel) {
        this.formulaire = formulairePaiement;
        this.giftCardSection = giftCardSection;
        this.creditCardSection = creditCardSection;
        this.deliveryAddressSection = deliveryAddressSection;
        this.billingAddressSection = billingAddressSection;
        this.ErrorLabel = errorLabel;

        creditCardSection.hideSection();
        giftCardSection.showSection();

        initialize();
    }

    @FXML
    public void initialize() {
        creditCardSection.getCreditCardNumberField().textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(creditCardSection.getCreditCardNumberField(), CARD_NUMBER_REGEX, "Numéro de carte");
        });

        creditCardSection.getCreditCardExpirationField().textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(creditCardSection.getCreditCardExpirationField(), EXP_DATE_REGEX, "Date d'expiration");
        });

        creditCardSection.getCreditCardCVCField().textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(creditCardSection.getCreditCardCVCField(), CVC_REGEX, "Code de sécurité");
        });

        giftCardSection.getGiftCardNumberField().textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(giftCardSection.getGiftCardNumberField(), GIFT_CARD_REGEX, "Numéro de carte cadeau");
        });

        deliveryAddressSection.getSameAdressesCheckbox().selectedProperty().addListener((observable, oldValue, newValue) -> {
            handleSameAdressesChange(newValue);
        });

        deliveryAddressSection.getDeliveryAddressField().textProperty().addListener((ov, oldValue, newValue) -> {
            handleSameAdressesChange(deliveryAddressSection.getSameAdressesCheckbox().isSelected());
        });
    }

    @Override
    public void handlePaymentModeChange(int index) {
        switch (index) {
            case 0: // Carte cadeau
                creditCardSection.hideSection();
                giftCardSection.showSection();
                enableLivraisonPorte();
                break;
            case 1: // Carte de crédit
                creditCardSection.showSection();
                giftCardSection.hideSection();
                enableLivraisonPorte();
                break;
            case 2: // Paiement à la livraison
                creditCardSection.hideSection();
                giftCardSection.hideSection();
                disableLivraisonPorte();
                break;
            default:
                break;
        }
        hideErrorMessage();
    }

    private void disableLivraisonPorte() {
        if (this.formulaire.getDeliveryOptionChoiceBox().getItems().contains("Laisser à la porte"))
            this.formulaire.getDeliveryOptionChoiceBox().getItems().remove("Laisser à la porte");
    }

    private void enableLivraisonPorte() {
        if (!this.formulaire.getDeliveryOptionChoiceBox().getItems().contains("Laisser à la porte"))
            this.formulaire.getDeliveryOptionChoiceBox().getItems().add("Laisser à la porte");
    }

    @Override
    public void handleSameAdressesChange(boolean isSameAddress) {
        if (isSameAddress) {
            this.billingAddressSection.getBillingAddressField().setDisable(true);
            this.billingAddressSection.getBillingAddressField().setText(this.deliveryAddressSection.getDeliveryAddressField().getText());
        } else
            this.billingAddressSection.getBillingAddressField().setDisable(false);

    }

    @Override
    public void displayErrorMessage(String field) {
        ErrorLabel.setVisible(true);
        ErrorLabel.setManaged(true);
        ErrorLabel.setText("Le champ '" + field + "' est invalide. Veuillez réessayer.");
    }

    @Override
    public void hideErrorMessage() {
        ErrorLabel.setVisible(false);
        ErrorLabel.setManaged(false);
        ErrorLabel.setText("");
    }

    @Override
    public void validateField(TextField textField, String regex, String champ) {
        if (textField.getText().matches(regex) || textField.getText().isEmpty())
            hideErrorMessage();
        else
            displayErrorMessage(champ);
    }
}