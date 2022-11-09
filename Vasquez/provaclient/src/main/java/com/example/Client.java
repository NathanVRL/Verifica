package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client 
{
    public static void main( String[] args ) throws Exception
    {
        Socket s = new Socket("localhost", 3000);
        
        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        
        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // per la tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        ServerConnection serverConn = new ServerConnection(s);
        serverConn.start();
        pr.println("Eccomi");
        boolean ciclo=true;
        System.out.println("inserisci 'D' -> richiesta disponibilità 'A' ->  acquista biglietto'Q' -> disconnessione");
        while(ciclo){
        System.out.println(br.readLine()); // ricevo:
        pr.println(tastiera.readLine()); // Invio
        }
        
        pr.close();
        br.close();
        tastiera.close();
        s.close();
    }
}
