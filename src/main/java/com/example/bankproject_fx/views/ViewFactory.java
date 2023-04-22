package com.example.bankproject_fx.views;

import com.example.bankproject_fx.HelloApplication;
import com.example.bankproject_fx.model.Session;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {


    private final ObjectProperty<CurrentChoice> currentChoice;
    private final ObjectProperty<CurrentWindow> currentWindow;




    String darkModeCss = getClass().getResource("/com/example/bankproject_fx/Styles/DarkMode.css").toExternalForm();
    String lightModeCss = getClass().getResource("/com/example/bankproject_fx/Styles/LightMode.css").toExternalForm();


    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane yourCardsView;
    private AnchorPane accountsView;
    private AnchorPane clientWindowView;
    private AnchorPane loginWindowView;
    private AnchorPane registerWindowView;

    private AnchorPane mainWindow;

    private Mode choosenMode;


    public AnchorPane getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(AnchorPane mainWindow) {
        this.mainWindow = mainWindow;
    }

    public ViewFactory() {
        this.currentChoice = new SimpleObjectProperty<>();
        this.currentWindow = new SimpleObjectProperty<>();
    }



    public ObjectProperty<CurrentChoice> getCurrentChoiceProperty() {
        return currentChoice;
    }

    public ObjectProperty<CurrentWindow> getCurrentWindowProperty() {
        return currentWindow;
    }

    public void setCurrentWindow(CurrentWindow currentWindow) {
        this.currentWindow.set(currentWindow);
    }

    public void setCurrentChoice(CurrentChoice currentChoice) {
        this.currentChoice.set(currentChoice);
    }

    public void showAppWindow(){

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("App.fxml"));
        createStage(fxmlLoader);
                //loginWindowView = fxmlLoader.load();



    }


    public AnchorPane getClientWindow() {
        if (clientWindowView == null) {
            try {
                clientWindowView = new FXMLLoader(HelloApplication.class.getResource("Client.fxml")).load();
                getLightningMode(clientWindowView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientWindowView;
    }

    public AnchorPane getLoginWindow() {
        if (loginWindowView == null) {
            try {
                loginWindowView = new FXMLLoader(HelloApplication.class.getResource("Login.fxml")).load();
                getLightningMode(loginWindowView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return loginWindowView;
    }

    public AnchorPane getRegisterWindow() {
        if (registerWindowView == null) {
            try {
                registerWindowView = new FXMLLoader(HelloApplication.class.getResource("Register.fxml")).load();
                getLightningMode(registerWindowView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return registerWindowView;
    }


    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(HelloApplication.class.getResource("ClientMainPage.fxml")).load();
                getLightningMode(dashboardView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    public AnchorPane getYourCardsView() {
        if (yourCardsView == null) {
            try {
                yourCardsView = new FXMLLoader(HelloApplication.class.getResource("YourCardsPage.fxml")).load();
                getLightningMode(yourCardsView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return yourCardsView;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null) {
            try {
                transactionsView = new FXMLLoader(HelloApplication.class.getResource("TransactionsPage.fxml")).load();
                getLightningMode(transactionsView);

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
                getLightningMode(dashboardView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    public void getLightningMode(AnchorPane anchorpane) {
        if (Session.getInstance().getChoosenMode().equals(Mode.light)) {
            anchorpane.getStylesheets().clear();
            anchorpane.getStylesheets().add(lightModeCss);
        }
        else if(Session.getInstance().getChoosenMode().equals(Mode.dark)){
            anchorpane.getStylesheets().clear();
            anchorpane.getStylesheets().add(darkModeCss);
        }
    }

    public void showRegisterWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Register.fxml"));

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
    public void changeMode(){
        if(Session.getInstance().getChoosenMode().equals(Mode.light)){
            Session.getInstance().setChoosenMode(Mode.dark);
            getDashboardView().getStylesheets().clear();
            getDashboardView().getStylesheets().add(darkModeCss);
            getEditView().getStylesheets().clear();
            getEditView().getStylesheets().add(darkModeCss);
            getClientWindow().getStylesheets().clear();
            getClientWindow().getStylesheets().add(darkModeCss);
            getRegisterWindow().getStylesheets().clear();
            getRegisterWindow().getStylesheets().add(darkModeCss);
            getLoginWindow().getStylesheets().clear();
            getLoginWindow().getStylesheets().add(darkModeCss);
            getTransactionsView().getStylesheets().clear();
            getTransactionsView().getStylesheets().add(darkModeCss);
            getYourCardsView().getStylesheets().clear();
            getYourCardsView().getStylesheets().add(darkModeCss);

        }
        else{
            Session.getInstance().setChoosenMode(Mode.light);
            getDashboardView().getStylesheets().clear();
            getDashboardView().getStylesheets().add(lightModeCss);
            getEditView().getStylesheets().clear();
            getEditView().getStylesheets().add(lightModeCss);
            getClientWindow().getStylesheets().clear();
            getClientWindow().getStylesheets().add(lightModeCss);
            getRegisterWindow().getStylesheets().clear();
            getRegisterWindow().getStylesheets().add(lightModeCss);
            getLoginWindow().getStylesheets().clear();
            getLoginWindow().getStylesheets().add(lightModeCss);
            getTransactionsView().getStylesheets().clear();
            getTransactionsView().getStylesheets().add(lightModeCss);
            getYourCardsView().getStylesheets().clear();
            getYourCardsView().getStylesheets().add(lightModeCss);

        }

    }










}
