package it.fi.gf.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread extends Thread{
    Socket client = null;

    BufferedReader inDalClient = null;
    DataOutputStream outVersoIlClient = null;
    ArrayList <Persona> anagrafe = new ArrayList<>();

    ServerThread(Socket c ){
        this.client = c;
    }

    public void run(){
        try {
            this.comunica();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void comunica() throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoIlClient = new DataOutputStream(client.getOutputStream());
        
        ObjectMapper objectMapper = new ObjectMapper();

        while(true){
            String stringa_ricevuta = inDalClient.readLine();
            Messaggio messaggio = objectMapper.readValue(stringa_ricevuta, Messaggio.class); //deserializzo il messaggio che mi arriva dal client
            if(messaggio.getNazioneRichiesta() == null && messaggio.getListaPersone()!= null){
                for (int i = 0; i < messaggio.getListaPersone().size(); i++) {
                    anagrafe.add(messaggio.getListaPersone().get(i));
                }
                Messaggio msgRisposta = new Messaggio();
                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(msgRisposta) + "\n");
            }
            else if(messaggio.getNazioneRichiesta() != null && messaggio.getListaPersone()== null){
                Messaggio msgRisposta = new Messaggio();
                msgRisposta.setNazioneRichiesta(messaggio.getNazioneRichiesta());
                for (int i = 0; i < anagrafe.size(); i++) {
                    if(anagrafe.get(i).nazioneResidenza == msgRisposta.getNazioneRichiesta()){
                        msgRisposta.getListaPersone().add(anagrafe.get(i));
                    }
                }

                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(msgRisposta) + "\n");
            }
            else if (messaggio.getNazioneRichiesta() == null && messaggio.getListaPersone()== null) {
                Messaggio msgRisposta = new Messaggio();
                for (int i = 0; i < anagrafe.size(); i++) {
                    msgRisposta.getListaPersone().add(anagrafe.get(i));
                }
                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(msgRisposta) + "\n");
            }
                
            }
        }
    }  
//}
