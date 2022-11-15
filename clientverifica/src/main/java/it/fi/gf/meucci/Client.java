package it.fi.gf.meucci;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
    
    String nomeServer = "localhost"; 
    int porta = 9843;
    Socket socket;
    String stringaRicevuta;
    String stringainviata;
    BufferedReader tastiera;
    BufferedReader inDalServer;
    DataOutputStream outVersoIlServer;
    ArrayList <Persona> lP = new ArrayList<>();

    public Socket connetti(){
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, porta);

            outVersoIlServer = new DataOutputStream(socket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader( socket.getInputStream() ));

        } catch (Exception e) {
            System.out.println("c'è stato un errore");
        }

        return socket;
    }

    public void comunica(){
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                System.out.println("Scrivi il nome");
                String n = tastiera.readLine();
                String nom[] = n.split(" ");
                System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*");
                System.out.println("Scrivi il cognome");
                String c = tastiera.readLine();
                String cognom[] = n.split(" ");
                System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*");
                System.out.println("Scrivi la nazionalità");
                String naz = tastiera.readLine();
                String nazRes[] = n.split(" ");
                System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*");
                System.out.println("Scrivi la nazionalità richiesta");
                String nR= tastiera.readLine();
                Messaggio m = new Messaggio(nR, lP);
                for (int i = 0; i < cognom.length; i++) {
                     Persona per = new Persona(nom[i],cognom[i], nazRes[i]);
                     m.getPersone().add(per);
                }
                
                outVersoIlServer.writeBytes(objectMapper.writeValueAsString(m) + "\n"); //serializzo il messaggio da inviare al server

                String risposta = inDalServer.readLine();
                Messaggio messaggioricevuto = objectMapper.readValue(risposta, Messaggio.class); //deserializzo il messaggio arrivato dal server
                System.out.println("*Risposta richiesta:*");
                for (int i = 0; i < messaggioricevuto.getPersone().size(); i++) {
                    System.out.println("-Nome: "+messaggioricevuto.getPersone().get(i).getNome()+ " / -Cognome: "+messaggioricevuto.getPersone().get(i).getCognome()+ " / -Nazionalità di Residenza"+ messaggioricevuto.getPersone().get(i).getNazioneResidenza());
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    


    


}
