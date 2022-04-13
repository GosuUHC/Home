package lab4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;


class Serv {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1111);
            System.out.println("Waiting connection...");
            Socket s = ss.accept();
            System.out.println("Local port: " + s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());


            while (!s.isClosed()){

            String firstN = in.readUTF();
            String what = in.readUTF();
            String secondN = in.readUTF();
            System.out.println("READ from client 1 :" + firstN);
            System.out.println("READ from client 2 :" + what);
            System.out.println("READ from client 3 :" + secondN);

            Double firstNumber = Double.valueOf(firstN);
            Double secondNumber = Double.valueOf(secondN);
            double result = 1;
            if (what.equals("+")) {
                result = firstNumber + secondNumber;
            } else if (what.equals("-")) {
                result = firstNumber - secondNumber;
            } else if (what.equals("*")) {
                result = firstNumber * secondNumber;
            } else if (what.equals("/")) {
                result = firstNumber / secondNumber;
            }

            out.writeUTF("" + result);
            out.flush();

            Thread.sleep(10000);

            }


            in.close();
            out.close();
            s.close();
            ss.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
