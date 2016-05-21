package clinet;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ashiq on 5/21/2016.
 */
public class getmsz implements  Runnable{
    Socket socket;
    DataInputStream din;
    clientmain clientm;
    Thread t;
    getmsz(Socket s, clientmain cm)
    {
        socket=s;
        clientm=cm;
        try {
            din=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                String s=din.readUTF();
                if(true)
                    Platform.runLater(() -> clientm.settext(s));
            } catch (IOException e) {

                try {
                    din.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
                // e.printStackTrace();
            }
        }
    }
}
