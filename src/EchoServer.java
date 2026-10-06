import java.net.*;
import java.io.*;

public class EchoServer
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("You must enter a port number between 1025 and 65535");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        byte[] buffer = new byte[256];

        DatagramPacket dpReceive = null;
        DatagramSocket serverSocket = null;

        try { serverSocket = new DatagramSocket(portNumber); }
        catch (SocketException exception) { System.out.println("The port could not be opened."); System.exit(1); }

        while (true)
        {
            System.out.println("Waiting...");
            dpReceive = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(dpReceive);
            System.out.println(new String(dpReceive.getData()));
        }
    }
}