package org.example.laboratoire3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FormulairePaiement extends Application {
    private final String[] PAYMENT_OPTIONS = {
            "Carte cadeau",
            "Carte de crédit",
            "Paiement à la livraison"
    };

    private final String[] DELIVERY_OPTIONS = {
            "Livraison en main propre",
            "Se retrouver à l’extérieur",
            "Laisser à la porte"
    };

    private MediateurFormulairePaiement mediator;

    @FXML
    private VBox VBoxLayout;

    @FXML
    private ChoiceBox<String> PaymentModeChoiceBox;

    @FXML
    private ChoiceBox<String> DeliveryOptionChoiceBox;

    @FXML
    private TitledPane CreditCardSection;

    @FXML
    private TitledPane GiftCardSection;

    @FXML
    private TitledPane BillingAddressSection;

    @FXML
    private RadioButton SameAdressesCheckbox;

    @FXML
    private TextField BillingAddressTextField;

    @FXML
    private TextField DeliveryAddressTextField;

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField CreditCardNumberField;

    @FXML
    private TextField CreditCardExpirationField;

    @FXML
    private TextField CreditCardCVCField;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FormulairePaiement.class.getResource("FormulairePaiement.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Formulaire de paiement");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void initialize() {
        PaymentModeChoiceBox.getItems().addAll(PAYMENT_OPTIONS);
        DeliveryOptionChoiceBox.getItems().addAll(DELIVERY_OPTIONS);

        mediator = new MediateurFormulairePaiement(
            this.PaymentModeChoiceBox, 
            this.DeliveryOptionChoiceBox,
            this.GiftCardSection,
            this.CreditCardSection,
            this.BillingAddressSection,
            this.CreditCardNumberField,
            this.CreditCardExpirationField,
            this.CreditCardCVCField,
            this.ErrorLabel,
            this.SameAdressesCheckbox,
            this.BillingAddressTextField,
            this.DeliveryAddressTextField
        );

        PaymentModeChoiceBox.setValue(PAYMENT_OPTIONS[0]);
        DeliveryOptionChoiceBox.setValue(DELIVERY_OPTIONS[0]);
    }

    public static void main(String[] args) {
        launch();
    }
}