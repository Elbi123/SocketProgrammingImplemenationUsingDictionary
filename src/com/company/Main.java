package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Main {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
	// write your code here
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex){
            System.out.println("Unable to setup the port!");
            System.exit(1);
        }

        do {
            Socket client = serverSocket.accept();
            System.out.println("\nNew Client Accepted.\n");
            ClientHandler handler = new ClientHandler(client);
            handler.start();
        } while (true);
    }
}

class ClientHandler extends Thread {
    private Socket client;
    private Scanner input;
    private PrintWriter output;

    public  ClientHandler(Socket socket){
        client = socket;

        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String received;
        do {
            received = input.nextLine();
            output.println("ECHO: " + received);
        } while(!received.equalsIgnoreCase("QUIT"));
        try {
            if (client != null){
                System.out.println("Closing down connection...");
                client.close();
            }
        } catch(IOException ex){
            System.out.println("Unable to disconnect!");
        }
    }
}

