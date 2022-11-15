package it.fi.gf.meucci;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public ServerSocket socketserver;      
    public static ArrayList<Persona> listaPersone = new ArrayList<>();

    public Server(){
    }

    public void avvia(){
        try {
            System.out.println("Server in esecuzione...");
            this.socketserver = new ServerSocket(9843); 
            while (true) {                              
                Socket socket = socketserver.accept(); 
                ServerThread thread = new ServerThread(socket);
                thread.start();                
            }

        } catch (Exception e) {
            System.out.println("Il server è stato chiuso");
        }        
    }
}
