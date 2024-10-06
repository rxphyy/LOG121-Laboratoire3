package org.example.laboratoire3;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MediateurFormulairePaiement implements Mediateur {
    private final String[] DELIVERY_OPTIONS = {
        "Livraison en main propre",
        "Se retrouver à l’extérieur",
        "Laisser à la porte"
    };

    private final String CREDIT_CARD_NUM_REGEX = "^(\\d{16}|(\\d{4}-){3}\\d{4})$";
    private final String CREDIT_CARD_EXP_REGEX = "^([0-2]?[0-9]|3[01])[-/](0[1-9]|1[0-2]|[1-9])$|^(0[1-9]|[1][0-2]|[1-9])[-/](0[1-9]|[12][0-9]|3[01]|[1-9])$";
    private final String CREDIT_CARD_CVC_REGEX = "^(\\d{3})$";

    private ChoiceBox<String> PaymentModeChoiceBox;
    private ChoiceBox<String> DeliveryOptionChoiceBox;
    private TitledPane CreditCardSection;
    private TitledPane GiftCardSection;
    private TitledPane BillingAddressSection;
    private Label ErrorLabel;
    private TextField CreditCardNumberField;
    private TextField CreditCardExpirationField;
    private TextField CreditCardCVCField;
    private RadioButton SameAdressesCheckbox;
    private TextField BillingAddressTextField;
    private TextField DeliveryAddressTextField;

    public MediateurFormulairePaiement(ChoiceBox<String> PaymentModeChoiceBox, ChoiceBox<String> DeliveryOptionChoiceBox, TitledPane GiftCardSection, TitledPane CreditCardSection, TitledPane BillingAddressSection, TextField CreditCardNumberField, TextField CreditCardExpirationField, TextField CreditCardCVCField, Label ErrorLabel, RadioButton SameAddressCheckbox, TextField BillingAddressTextField, TextField DeliveryAddressTextField) {
        this.PaymentModeChoiceBox = PaymentModeChoiceBox;
        this.DeliveryOptionChoiceBox = DeliveryOptionChoiceBox;
        this.GiftCardSection = GiftCardSection;
        this.CreditCardSection = CreditCardSection;
        this.BillingAddressSection = BillingAddressSection;
        this.CreditCardNumberField = CreditCardNumberField;
        this.CreditCardExpirationField = CreditCardExpirationField;
        this.CreditCardCVCField = CreditCardCVCField;
        this.ErrorLabel = ErrorLabel;
        this.SameAdressesCheckbox = SameAddressCheckbox;
        this.BillingAddressTextField = BillingAddressTextField;
        this.DeliveryAddressTextField = DeliveryAddressTextField;
        

        initialize();
    }

    @FXML
    public void initialize() {
        // Ensure that FXML components are initialized
        PaymentModeChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
            handlePaymentModeChange((int) new_value);
        });

        DeliveryOptionChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
            handleDeliveryModeChange((int) new_value);
        });

        CreditCardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(CreditCardNumberField, CREDIT_CARD_NUM_REGEX, "Numéro de carte de crédit");
        });

        CreditCardExpirationField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(CreditCardExpirationField, CREDIT_CARD_EXP_REGEX, "Expiration de la carte de crédit");
        });

        CreditCardCVCField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateField(CreditCardExpirationField, CREDIT_CARD_CVC_REGEX, "Expiration de la carte de crédit");
        });

        SameAdressesCheckbox.setOnAction(e -> {
            handleSameAdressesChange();
        });
    }

    @Override
    public void handlePaymentModeChange(int index) {
        switch (index) {
            case 0: // Carte cadeau
                CreditCardSection.setVisible(false);
                CreditCardSection.setManaged(false);
                GiftCardSection.setVisible(true);
                GiftCardSection.setManaged(true);

                toggleContactlessDeliveryOption();
                break;
            case 1: // Carte de crédit
                CreditCardSection.setVisible(true);
                CreditCardSection.setManaged(true);
                GiftCardSection.setVisible(false);
                GiftCardSection.setManaged(false);

                toggleContactlessDeliveryOption();
                break;
            case 2: // Paiement à la livraison
                CreditCardSection.setVisible(false);
                CreditCardSection.setManaged(false);
                GiftCardSection.setVisible(false);
                GiftCardSection.setManaged(false);

                this.DeliveryOptionChoiceBox.getItems().remove(2);
                this.DeliveryOptionChoiceBox.setValue(DELIVERY_OPTIONS[0]);
                break;
            default:
                break;
        }
    }

    private void toggleContactlessDeliveryOption() {
        if (!this.DeliveryOptionChoiceBox.getItems().contains("Laisser à la porte")) {
            this.DeliveryOptionChoiceBox.getItems().add(DELIVERY_OPTIONS[2]);
        }
    }

    @Override
    public void handleDeliveryModeChange(int index) {
        
    }

    @Override
    public void handleSameAdressesChange() {
        BillingAddressTextField.setText(
                SameAdressesCheckbox.isSelected() ?
                    DeliveryAddressTextField.getText() : ""
        );
        BillingAddressSection.getContent().setDisable(!BillingAddressSection.getContent().isDisabled());
    }

    @Override
    public void displayErrorMessage(String field) {
        ErrorLabel.setVisible(true);
        ErrorLabel.setManaged(true);
        ErrorLabel.setText("Le champ " + field + " est invalide. Veuillez réessayer.");
    }

    @Override
    public void hideErrorMessage() {
        System.out.println("HIDE ERROR MESSAGE");
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
