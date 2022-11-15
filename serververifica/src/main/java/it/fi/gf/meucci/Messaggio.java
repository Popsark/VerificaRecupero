package it.fi.gf.meucci;

import java.util.ArrayList;

public class Messaggio {
    String nazioneRichiesta;
    ArrayList<Persona> persone = new ArrayList<>();
    
    public Messaggio(ArrayList<Persona> persone, String nazioneRichiesta) {
        this.persone = persone;
        this.nazioneRichiesta = nazioneRichiesta;
    }

    public Messaggio (){}

    public ArrayList<Persona> getListaPersone() {
        return persone;
    }

    public void setListaPersone(ArrayList<Persona> persone) {
        this.persone = persone;
    }

    public String getNazioneRichiesta() {
        return nazioneRichiesta;
    }

    public void setNazioneRichiesta(String nazioneRichiesta) {
        this.nazioneRichiesta = nazioneRichiesta;
    }
}
