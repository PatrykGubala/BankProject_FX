package com.example.bankproject_fx;

import com.example.bankproject_fx.model.Session;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Session.getInstance().getViewFactory().showAppWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}