package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    static int biglietto=2;
    ArrayList<ClientHandler>clients;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ClientHandler(Socket socket,ArrayList<ClientHandler>clients) {
        this.socket = socket;
        this.clients=clients;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Client connesso");
         String scelta;
        boolean  ciclo=true;
        while(ciclo){
            try {
                scelta=in.readLine();
                if(scelta.equalsIgnoreCase("D")){
                    out.println("disponiblita biglietto è di :"+biglietto+" biglietti");
                }else if(biglietto==0){
                    out.println("Imposibile acquistare un biglietto");
                    out.println("Disconessione dal server");
                    sendToAll("@");
                    ciclo = false;


                }else if(scelta.equalsIgnoreCase("A")){
                    out.println("Biglietto acquistato");
                    biglietto--;
                }else if(scelta.equalsIgnoreCase("Q")){
                    out.println("chiusura");
                    socket.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }
    }
    private void sendToAll(String msg) {
        for (ClientHandler client : clients) {
            System.out.println(client.getName());
            client.out.println(msg);
        }
    }


}