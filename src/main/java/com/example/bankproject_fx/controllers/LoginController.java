package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.CurrentChoice;
import com.example.bankproject_fx.views.CurrentWindow;
import com.example.bankproject_fx.views.Mode;
import com.example.bankproject_fx.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private ViewFactory viewFactory = new ViewFactory();
    private Validator validator = new Validator();

    @FXML
    private Button lightSwitch;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private AnchorPane mainWindowAnchorPaneId;

    @FXML
    private Label errorLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    void signInUser(MouseEvent event) throws SQLException {
        String login = loginField.getText();
        String password = passwordField.getText();
        if(Session.getInstance().getBankDatabase().credentialsConfirmation2(login, password)){

            Session.getInstance().getViewFactory().getCurrentWindowProperty().set(CurrentWindow.CUSTOMER);
        }else {
            System.out.println("too bad doog");

        }

    }

    @FXML
    void signUpUser(MouseEvent event) {
        Session.getInstance().getViewFactory().getCurrentWindowProperty().set(CurrentWindow.REGISTER);
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    void onLightSwitch(MouseEvent event) {
        Session.getInstance().getViewFactory().changeMode();
    }





}
