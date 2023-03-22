package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.dao.JdbcDAO;
import com.example.bankproject_fx.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private Validator validator = new Validator();
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;




    @FXML
    void signInUser(MouseEvent event) throws SQLException {
        String fullName = loginField.getText();
        String emailId = passwordField.getText();
        String password = "123";

        JdbcDAO jdbcDao = new JdbcDAO();
        jdbcDao.insertRecord(fullName, emailId, password);

        Window owner = signInButton.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + loginField.getText());
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
    private Label errorLabel;






    @FXML
    void signUpUser(MouseEvent event) {
        ViewFactory views = new ViewFactory();
        views.showRegisterWindow();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
