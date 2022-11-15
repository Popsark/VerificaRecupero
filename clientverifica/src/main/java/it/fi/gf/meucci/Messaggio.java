package it.fi.gf.meucci;


import java.util.ArrayList;

public class Messaggio {
        String nazioneRichiesta;
        ArrayList<Persona> persone = new ArrayList<>();
        
        public Messaggio(String nazioneRichiesta, ArrayList<Persona> persone) {
            this.nazioneRichiesta = nazioneRichiesta;
            this.persone = persone;
        }

        public Messaggio (){
            
        }
    
        public ArrayList<Persona> getPersone() {
            return persone;
        }
    
        public void setPersone(ArrayList<Persona> persone) {
            this.persone = persone;
        }

        public String getNazioneRichiesta() {
            return nazioneRichiesta;
        }

        public void setNazioneRichiesta(String nazioneRichiesta) {
            this.nazioneRichiesta = nazioneRichiesta;
        }
}
