package rubrica;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ALDO on 20/06/2017.
 Il File di caricamento deve essere selezionato?
 e quello di salvataggio?
 Formato Persona su File:
 Nome;Cognome;Nickname;Mail1;Mail2;....MailN;
 */
public class Rubrica {
    private List<Persona> persone;
    private File file;

    public Rubrica(String nomeFile){
        persone = new LinkedList<Persona>();
        file = new File(nomeFile);
        caricaRubrica();
    }

    private void caricaRubrica(){

    }

    private void salvaRubrica(){

    }

    public void getByNome(){

    }

    public void getByCognome(){

    }

    public void getByNickname(){

    }

    public void getByMail(){

    }
}
