package com.example.bankproject_fx.views;

import com.example.bankproject_fx.HelloApplication;
import com.example.bankproject_fx.controllers.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {




    public void showLoginWindow(){

    }

    public void showClientWindow(){

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
