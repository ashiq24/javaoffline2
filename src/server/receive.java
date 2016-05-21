package server;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ashiq on 5/21/2016.
 */
public class receive implements  Runnable{
    private servermain serverm;
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;
    int id;
    Thread t;

    public receive(Socket s, servermain serverm, int i) throws IOException {
        this.serverm=serverm;
        socket=s;
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        id=i;
        t=new Thread(this);
        t.start();

    }
    void sendmsz(String s)
    {

        try {
            dout.writeUTF(s.toUpperCase());
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(true)
        {
            try {
                String s=din.readUTF();
                if(!s.equals("END"))sendmsz(s);

                if(true)
                {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(s.equals("END"))
                            {
                                serverm.gettext("has left",id);

                                try {
                                    dout.close();
                                    din.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else serverm.gettext(s,id);
                        }
                    });

                }
                if(s.equals("END")) break;
            } catch (IOException e) {
                //e.printStackTrace();
                break;
            }


        }

    }
}
