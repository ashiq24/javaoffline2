package sample;

/**
 * Created by Ashiq on 5/5/2016.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AlartBox {
    public  static void display(String title , String mass)
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMaxHeight(400);
        Label label=new Label(mass);
        Button close=new Button("Close");
        close.setOnAction(e-> window.close());
        VBox layout=new VBox();
        layout.getChildren().addAll(label,close);
        Scene sen=new Scene(layout);
        window.setScene(sen);
        window.showAndWait();



    }
}
