package com.example.bankproject_fx.views;

import com.example.bankproject_fx.HelloApplication;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {


    private final ObjectProperty<CurrentChoice> currentChoice;

    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;

    public ViewFactory() {
        this.currentChoice = new SimpleObjectProperty<>();
    }



    public ObjectProperty<CurrentChoice> getCurrentChoiceProperty() {
        return currentChoice;
    }

    public void setCurrentChoice(CurrentChoice currentChoice) {
        this.currentChoice.set(currentChoice);
    }

    public void showLoginWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        createStage(fxmlLoader);
    }

    public void showClientWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Client.fxml"));

        createStage(fxmlLoader);

    }


    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(HelloApplication.class.getResource("ClientMainPage.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null) {
            try {
                transactionsView = new FXMLLoader(HelloApplication.class.getResource("TransactionsPage.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getEditView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(HelloApplication.class.getResource("ClientMainPage.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
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
