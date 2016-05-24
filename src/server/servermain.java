package server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Created by Ashiq on 5/21/2016.
 */
public class servermain extends Application {
    Button start;
    TextArea msz;
    servermain smain;
    ServerSocket server;

    @Override
    public void start(Stage primaryStage) throws Exception {
        smain=this;
        start=new Button("launch");
        VBox box=new VBox();
        msz=new TextArea();
        box.getChildren().addAll(msz,start);
        Scene scene=new Scene(box,400,300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.show();
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    InetAddress ip=InetAddress.getLocalHost();
                    System.out.println(ip);
                    server= new ServerSocket(8000);
                    System.out.println(server.getLocalPort());
                    new acceptor(smain,server);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void main(String[] args) {
        Application.launch();
    }
    void gettext(String s,int id)
    {
        msz.setText(msz.getText()+"\n"+"clinet["+id+"]:"+s);
    }
    void gettext(String s)
    {
        msz.setText(msz.getText()+"\n"+s);
    }
}
