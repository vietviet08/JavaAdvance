import java.io.*;
import java.net.Socket;

public class ChatClient {


    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 9189);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true );
            System.out.println("Client");

            boolean done = false;
            while (!done) {
                System.out.print("Client: ");
                String fromClient = kbd.readLine();
                out.println("Client: " + fromClient);
                String fromServer = in.readLine();
                System.out.println(fromServer);
                if (fromServer.trim().equals("Server: BYE") || fromClient.equals("BYE"))
                    done = true;
            }
            s.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
