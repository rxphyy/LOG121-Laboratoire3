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
    private VBox PaymentContextVBox;

    @FXML
    private ChoiceBox PaymentModeChoiceBox;

    @FXML
    private ChoiceBox DeliveryOptionChoiceBox;

    @FXML
    private Label ErrorLabel;


    TitledPane creditCardPane;
    CreditCardSection creditCardSection;

    TitledPane giftCardPane;
    GiftCardSection giftCardSection;

    TitledPane billingAddressPane;
    BillingAddressSection billingAddressSection;

    TitledPane deliveryAddressPane;
    DeliveryAddressSection deliveryAddressSection;

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
    public void initialize() throws IOException {
        FXMLLoader creditCardLoader = new FXMLLoader(getClass().getResource("CreditCardSection.fxml"));
        creditCardPane = creditCardLoader.load();
        creditCardSection = creditCardLoader.getController();

        FXMLLoader giftCardLoader = new FXMLLoader(getClass().getResource("GiftCardSection.fxml"));
        giftCardPane = giftCardLoader.load();
        giftCardSection = giftCardLoader.getController();

        FXMLLoader billingAddrLoader = new FXMLLoader(getClass().getResource("BillingAddressSection.fxml"));
        billingAddressPane = billingAddrLoader.load();
        billingAddressSection = billingAddrLoader.getController();

        FXMLLoader deliveryAddrLoader = new FXMLLoader(getClass().getResource("DeliveryAddressSection.fxml"));
        deliveryAddressPane = deliveryAddrLoader.load();
        deliveryAddressSection = deliveryAddrLoader.getController();

        PaymentContextVBox.getChildren().addAll(creditCardPane, giftCardPane, deliveryAddressPane, billingAddressPane);

        PaymentModeChoiceBox.getItems().addAll(PAYMENT_OPTIONS);
        DeliveryOptionChoiceBox.getItems().addAll(DELIVERY_OPTIONS);

        PaymentModeChoiceBox.setValue(PAYMENT_OPTIONS[0]);
        DeliveryOptionChoiceBox.setValue(DELIVERY_OPTIONS[0]);

        this.mediator = new MediateurFormulairePaiement(this, giftCardSection, creditCardSection, deliveryAddressSection, billingAddressSection, ErrorLabel);
    }

    @FXML
    private void handlePaymentModeChange() {
        int selectedIndex = PaymentModeChoiceBox.getSelectionModel().getSelectedIndex();
        if (mediator != null) {
            mediator.handlePaymentModeChange(selectedIndex);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public ChoiceBox getDeliveryOptionChoiceBox() {
        return DeliveryOptionChoiceBox;
    }

    public ChoiceBox getPaymentModeChoiceBox() {
        return PaymentModeChoiceBox;
    }
}