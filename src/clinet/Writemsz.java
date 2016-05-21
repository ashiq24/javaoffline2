package clinet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ashiq on 5/21/2016.
 */
public class Writemsz {
    clientmain clientm;
    Socket socket;
    DataOutputStream dout;
    Writemsz(Socket s) throws IOException {
        socket=s;
        dout=new DataOutputStream(s.getOutputStream());
    }
    void write(String s) throws IOException {
        if(s.equals("END"))
        {
            dout.writeUTF(s);
            dout.flush();
            dout.close();
        }
        else
        {
            dout.writeUTF(s);
            dout.flush();
        }

    }

}
