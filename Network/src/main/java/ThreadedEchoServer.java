import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {

    public static void main(String[] args) {
        int i = 1;
        try {
            ServerSocket s = new ServerSocket(9189);
            while (true) {
                Socket incoming = s.accept();
                // System.out.println("Spawning " + i);
                new ThreadedEchoHandler(incoming, i).start();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ThreadedEchoHandler extends Thread {
    private Socket incoming;
    private int counter;

    public ThreadedEchoHandler(Socket i, int c) {
        incoming = i;
        counter = c;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */);
            out.println("Hello! Enter BYE to exit.");
            boolean done = false;
            while (!done) {
                String str = in.readLine();
                if (str == null)
                    done = true;
                else {
                    System.out.println(str);
                    out.println("Echo (" + counter + "): " + str);
                    if (str.trim().equals("BYE"))
                        done = true;
                }
            }
            incoming.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

