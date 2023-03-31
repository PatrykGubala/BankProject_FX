package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.BankUser;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private ViewFactory viewFactory = new ViewFactory();
    private Validator validator = new Validator();
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;
    private List<BankUser> bankCustomers;




    @FXML
    void signInUser(MouseEvent event) throws SQLException {
        String login = loginField.getText();
        String password = passwordField.getText();
        if(Session.getInstance().getBankDatabase().credentialsConfirmation(login, password)){
            Session.getInstance().getViewFactory().showClientWindow();
        }else{
            System.out.println("too bad doog");

        }
        //BankDatabase bankDatabase = new BankDatabase();



//        JdbcDAO jdbcDao = new JdbcDAO();
//        jdbcDao.insertRecord(fullName, emailId, password);
//
//        Window owner = signInButton.getScene().getWindow();
//        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
//                "Welcome " + loginField.getText());
        //BankDatabase bankDatabase = new BankDatabase();
        //List<BankUsers>bankAccounts= new ArrayList<>();
        //bankAccounts=bankDatabase.getBankAccounts("656");
       // for (BankUsers bankAccount : bankAccounts) {
          //  System.out.println(bankAccount.toString());

      //  }




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
