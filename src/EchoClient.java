import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 2)
        {
            System.out.println("You must enter a hostname, and port number between 1025 and 65535");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        InetAddress hostAddress = InetAddress.getByName(hostName);

        DatagramPacket dpReceive = null;
        DatagramSocket clientSocket = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try { clientSocket = new DatagramSocket(); }
        catch (SocketException exception) { System.exit(1); }

        String userInput = null;
        byte[] buffer = null;

        Scanner terminalScanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Send message: ");
            userInput = terminalScanner.nextLine();
            buffer = userInput.getBytes(StandardCharsets.UTF_8);
            DatagramPacket dpSend = new DatagramPacket(buffer, buffer.length, hostAddress, portNumber);
            clientSocket.send(dpSend);
        }
    }
}