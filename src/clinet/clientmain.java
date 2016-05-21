package clinet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Ashiq on 5/21/2016.
 */
public class clientmain extends Application {
    clientmain clientm;
    Button connect;
    Button send;
    TextField wmsz;
    TextArea msz;
    Socket socket;
    Writemsz writer;
    Button discon;

    @Override
    public void start(Stage primaryStage) throws Exception {
        clientm=this;
        connect=new Button("Connect");
        send=new Button("send");
        wmsz=new TextField();
        msz=new TextArea();
        discon=new Button("disconnetc");
        VBox box=new VBox();
        box.getChildren().addAll(msz,wmsz,send,connect,discon);
        Scene scene =new Scene(box,400,400);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        connect.setOnAction(event -> {
            try {
                InetAddress ip=InetAddress.getLocalHost();
                System.out.println(ip.toString());
                socket=new Socket("127.0.0.1",6000);
                writer=new Writemsz(socket);
                new getmsz(socket,clientm);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        send.setOnAction(event -> {
            String s=wmsz.getText();
            wmsz.clear();
            try {
                writer.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        discon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    writer.write("END");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public static void main(String[] args) {
        Application.launch();
    }
    void settext(String s)
    {
        msz.setText(msz.getText()+"\n"+"server :"+s);
    }
}
