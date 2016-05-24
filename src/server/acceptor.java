package server;

import javafx.application.Platform;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ashiq on 5/21/2016.
 */
public class acceptor implements Runnable {
    static Integer a;
    servermain serverm;
    ServerSocket server;
    Thread t;
    acceptor(servermain sm, ServerSocket s)
    {
        server=s;
        serverm=sm;
        t=new Thread(this);
        t.start();
        a=0;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                System.out.println(a+"connected");
                Socket s= server.accept();
                a++;
                System.out.println(s.getRemoteSocketAddress());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String s="client"+(a)+" is connected";
                        serverm.gettext(s);
                    }
                });
                new receive(s,serverm,a);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
