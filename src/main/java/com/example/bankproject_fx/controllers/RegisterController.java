package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.BankUser2;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.CurrentWindow;
import com.example.bankproject_fx.views.Mode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import net.synedra.validatorfx.Validator;
import org.kordamp.ikonli.javafx.FontIcon;


import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private Validator validator = new Validator();


    @FXML
    private DatePicker data;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField kodPocztowy;

    @FXML
    private TextField miasto;

    @FXML
    private TextField secondName;

    @FXML
    private TextField telefon;

    @FXML
    private TextField ulica;

    @FXML
    private Button lightSwitch;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button exitButton;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private FontIcon lightSwitchIcon;

    @FXML
    void onExitButtonClicked(MouseEvent event) {
        Session.getInstance().getViewFactory().getCurrentWindowProperty().set(CurrentWindow.LOGIN);

    }
    @FXML
    void onSignUpButtonClicked(MouseEvent event) {


        if(data.getValue() == null) {
            System.out.println("Data nie została wybrana");
        }
        else{
            String date = data.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            BankUser2 bankUser2 = new BankUser2("1",firstName.getText(),secondName.getText(),email.getText(),passwordField.getText(),telefon.getText(), ulica.getText(),miasto.getText(),kodPocztowy.getText(),date,100);
            if(!Session.getInstance().getBankDatabase().addBankAccount2(bankUser2)){
                System.out.println("Nie dodano użytkownika");
            }else{
                Session.getInstance().getViewFactory().getCurrentWindowProperty().set(CurrentWindow.LOGIN);
            }



        }


        System.out.println(validator.containsErrorsProperty());

    }

    @FXML
    void onMouseMoved(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println(resourceBundle);

        validatorCheck(firstName, "^[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]+$");
        validatorCheck(telefon, "^(\\+48|48)?[\\s-]?(\\d{3}[\\s-]?\\d{3}[\\s-]?\\d{3}|\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2})$");
        validatorCheck(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        validatorCheck(kodPocztowy, "^\\d{2}-\\d{3}$");
        validatorCheck(miasto, "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+$");
        validatorCheck(ulica, "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+$");
        validatorCheck(secondName, "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+(-[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+)?(?:\\s[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+(-[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+)?)?$");
        System.out.println(data.getEditor().toString());
        passwordValidator(passwordField,confirmPasswordField,"^(?=.*[0-9])(?=.*[A-ZĄĆĘŁŃÓŚŹŻ]).+$");

        Tooltip tooltip = new Tooltip("Please fill out all required fields");


        // Turn on if you want to disable signUpButton
        signUpButton.disableProperty().bind(validator.containsErrorsProperty());
        System.out.println(validator.containsErrorsProperty());







    }

    // Check for the password, do they have 1 UpperCase letter and 1 digit
    private void passwordValidator(PasswordField field, PasswordField field2,String regex) {
        this.validator.createCheck()
                .dependsOn("field", field.textProperty()).dependsOn("field2", field2.textProperty())
                .withMethod(c -> {
                    String text1 = c.get("field");
                    String text2 = c.get("field2");
                    if (!(text1.matches(regex) && text1.equals(text2))) {
                        c.error("Haslo ma posiadac 1 dużą literę oraz cyfrę");
                    }
                })
                .decorates(field2)
                .immediate();


    }

    // Function to check if the fields are correct
    private void validatorCheck(TextField field, String regex) {
        this.validator.createCheck()
                .dependsOn("field", field.textProperty())
                .withMethod(c -> {
                    String field1 = c.get("field");
                    if (!field1.matches(regex)) {
                        c.error("Zle dane");
                    }
                })
                .decorates(field)
                .immediate();


    }
    @FXML
    public void onLightSwitch(MouseEvent mouseEvent) {
        Session.getInstance().getViewFactory().changeMode();


    }
}
