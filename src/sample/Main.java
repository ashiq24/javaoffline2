package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application  {
    Stage window;
    BorderPane layout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setMinWidth(500);
        window.setMinHeight(600);



        window.show();

    }
    public static void print(ChoiceBox< String> v)
    {
        System.out.println(v.getValue());
    }
    public static void main(String[] args) {

        launch(args);
    }


}
