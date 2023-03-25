package com.example.bankproject_fx.views;

import com.example.bankproject_fx.HelloApplication;
import com.example.bankproject_fx.controllers.BankCustomerController;
import com.example.bankproject_fx.controllers.ClientController;
import com.example.bankproject_fx.controllers.RegisterController;
import com.example.bankproject_fx.model.BankUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {




    public void showLoginWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        createStage(fxmlLoader);
    }

    public void showClientWindow(BankUser bankUser){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bankCustomer.fxml"));
        BankCustomerController bankCustomerController = new BankCustomerController();
        bankCustomerController.setUserInformation(bankUser);
        fxmlLoader.setController(bankCustomerController);
        createStage(fxmlLoader);

    }
    public void showClientWindow2(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Client.fxml"));

        createStage(fxmlLoader);

    }
    public void showTransactionsWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Transactions.fxml"));

        createStage(fxmlLoader);

    }

    public void showRegisterWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));

        createStage(fxmlLoader);




    }
    public void createStage(FXMLLoader fxmlLoader) {
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load());
        }catch(Exception e){
            e.printStackTrace();

        }


        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("BANKK");
        stage.setResizable(false);
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();

    }


}
