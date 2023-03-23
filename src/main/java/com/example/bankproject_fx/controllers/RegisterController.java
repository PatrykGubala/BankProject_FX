package com.example.bankproject_fx.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.Icon;
import org.kordamp.ikonli.javafx.IkonResolver;


import java.net.URL;
import java.nio.file.Path;
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
    private FontIcon lightSwitchIcon;

    private enum Mode {light, dark};
    private Mode mode;
    String registerLightModeCss = getClass().getResource("/com/example/bankproject_fx/Styles/registerLightMode.css").toExternalForm();
    String registerDarkModeCss = getClass().getResource("/com/example/bankproject_fx/Styles/registerDarkMode.css").toExternalForm();



    @FXML
    private Tooltip tooltip;

    @FXML
    void onSignUpButtonClicked(MouseEvent event) {

    }

    @FXML
    void onMouseMoved(MouseEvent event) {
        TooltipWrapper<Button> signUpWrapper = new TooltipWrapper<>(
                signUpButton,
                this.validator.containsErrorsProperty(),
                Bindings.concat("Cannot sign up:\n", this.validator.createStringBinding())
        );
    }




    public void setLightMode(){
        System.out.println(mainPane.getStylesheets());
        mainPane.getStylesheets().remove(0);
        //System.out.println(mainPane.getStylesheets());
        mainPane.getStylesheets().add(registerLightModeCss);
        mode = Mode.light;
        //System.out.println(mainPane.getStylesheets());
    }

    public void setDarkMode(){



        System.out.println(mainPane.getStylesheets());
        mainPane.getStylesheets().remove(0);
        System.out.println(mainPane.getStylesheets());
        //System.out.println(mainPane.getStylesheets());
        mainPane.getStylesheets().add(registerDarkModeCss);
        System.out.println(mainPane.getStylesheets());
       // mainPane.getStylesheets().remove("registerLightMode.css");
        //System.out.println(mainPane.getStylesheets());
        //mainPane.getStylesheets().add("registerDarkMode.css");
        mode = Mode.dark;
        //System.out.println(mainPane.getStylesheets());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mode = Mode.light;
        System.out.println(resourceBundle);



        Button button = new Button();


        validatorCheck(firstName, "^[A-Za-z]+$");
        validatorCheck(telefon, "^(\\+48|48)?[\\s-]?(\\d{3}[\\s-]?\\d{3}[\\s-]?\\d{3}|\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2})$");
        validatorCheck(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        System.out.println(validator.containsErrorsProperty());




    }

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
        if (mode.equals(Mode.light)){
            setDarkMode();
        }
        else {
            setLightMode();
        }

    }
}
