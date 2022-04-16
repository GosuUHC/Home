package lab_4.num1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Net {
    DataInputStream in;
    DataOutputStream out;
    public Net() {
        try {
            System.out.println("Net init");
            Socket s = new Socket("127.0.0.1", 1111);
            System.out.println("Local port: " + s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());

            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(String clientData[]){
       
        for (String text : clientData){
            try {
                out.writeUTF(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String receiveFromServ(){
        String calcResult = "";
        try{
            calcResult = in.readUTF();
        } catch (IOException e){
            e.printStackTrace();
        }
        return calcResult;
    }
}
