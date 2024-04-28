import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(8989);
            Socket s = ss.accept();
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            while (true) {

                int sum = 0;
                while (!in.readUTF().equals("done")) {
                    int num = in.read();
                    sum += num;
                }

                out.writeInt(sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
