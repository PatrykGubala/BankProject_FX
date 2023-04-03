package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.BankCard2;
import com.example.bankproject_fx.model.BankUser;
import com.example.bankproject_fx.model.BankUser2;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.Mode;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.Icon;
import org.kordamp.ikonli.javafx.IkonResolver;


import java.net.URL;
import java.nio.file.Path;
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
    private PasswordField confirmPasswordField;
    @FXML
    private FontIcon lightSwitchIcon;

    //private enum Mode {light, dark};
    private Mode mode;
    String registerLightModeCss = getClass().getResource("/com/example/bankproject_fx/Styles/registerLightMode.css").toExternalForm();
    String registerDarkModeCss = getClass().getResource("/com/example/bankproject_fx/Styles/registerDarkMode.css").toExternalForm();



    @FXML
    private Tooltip tooltip;

    @FXML
    void onSignUpButtonClicked(MouseEvent event) {

       // BankUser bankUser = new BankUser(firstName.getText(),secondName.getText(),email.getText(),telefon.getText(), 100,ulica.getText(),miasto.getText(),kodPocztowy.getText(),Session.getInstance().birthDateFormat.format(data));
        String date = data.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(date);
        BankUser2 bankUser2 = new BankUser2("99124010193008221210601001",firstName.getText(),secondName.getText(),email.getText(),passwordField.getText(),telefon.getText(), ulica.getText(),miasto.getText(),kodPocztowy.getText(),"11.11.1111",100);
        //System.out.println(bankUser.toString());
        System.out.println(bankUser2.toString());
        System.out.println(validator.containsErrorsProperty());


        //Włącz aby uruchomić dodawanie użytkownika do bazy danych
        Session.getInstance().getBankDatabase().addBankAccount2(bankUser2);
        BankCard2 bankCard2 = new BankCard2("1111 1111 1111 1111", "EUR", "2028/04", 100,"99124010193008221210601001" );
        //Session.getInstance().getBankDatabase().addBankCard2(bankCard2);
    }




    @FXML
    void onMouseMoved(MouseEvent event) {

    }





    public void changeMode(){
        if(Session.getInstance().getChoosenMode().equals(Mode.light)){
            System.out.println(mainPane.getStylesheets());
            mainPane.getStylesheets().remove(0);
            mainPane.getStylesheets().add(registerDarkModeCss);
            Session.getInstance().setChoosenMode(Mode.dark);
        }
        else{
            System.out.println(mainPane.getStylesheets());
            mainPane.getStylesheets().remove(0);
            mainPane.getStylesheets().add(registerLightModeCss);
            Session.getInstance().setChoosenMode(Mode.light);
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println(resourceBundle);

        validatorCheck(firstName, "^[A-Za-z]+$");
        validatorCheck(telefon, "^(\\+48|48)?[\\s-]?(\\d{3}[\\s-]?\\d{3}[\\s-]?\\d{3}|\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2})$");
        validatorCheck(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        passwordValidator(passwordField,confirmPasswordField,"^(?=.*[0-9])(?=.*[A-Z]).+$");


        //Włącz aby uruchomić wyłączanie przycisku signUpButton
        //signUpButton.disableProperty().bind(validator.containsErrorsProperty());
        System.out.println(validator.containsErrorsProperty());








    }

    //Sprawdzenie czy hasła są identyczne oraz czy hasło 1 ma jedną dużą literę oraz 1 cyfrę
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
    //funkcja sprawdzająca poprawność danych
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
        changeMode();

    }
}
