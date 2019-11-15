package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex){
            System.out.println("\nHost ID not found!\n");
            System.exit(1);
        }
        sendMessage();
    }

    private static void sendMessage(){
        Socket socket = null;
        try {
            socket = new Socket(host, PORT);
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner userEntry = new Scanner(System.in);
            String message, received;

            do {
                System.out.println("Enter message ('QUIT') to exit");
                message = userEntry.nextLine();
                output.println(message);
                received = input.nextLine();
                System.out.println("\nSERVER >>> " + received);
            } while (!message.equalsIgnoreCase("QUIT"));
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("\nClosing connection...");
                socket.close();
            } catch (IOException ex){
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
