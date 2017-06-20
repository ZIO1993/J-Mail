package rubrica;

import javax.swing.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
    private String nomeFile = "Rubrica.txt";
    private String formato;

    public Rubrica(File f){
        persone = new LinkedList<Persona>();
        if(f!=null){
            file=f;
        }else{
            file = new File(nomeFile);
        }
        caricaRubrica();
    }

    private void caricaRubrica(){
        try {
            Scanner scanner = new Scanner(file);
            if(!scanner.hasNext()){
                throw new Exception("vuoto");
            }
            formato = scanner.nextLine();
            String temp;
            Persona p;
            while(scanner.hasNext()){
                temp = scanner.nextLine();
                p = getPersonaByFormato(formato, temp);
                if(p!=null && !persone.contains(p))
                    persone.add(p);
            }
        }catch (Exception e){
            if(e.getMessage().equals("vuoto") && !file.getName().equals(nomeFile)){
                JOptionPane.showMessageDialog(null, "Il file scelto è vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Errore nel caricamento della rubrica!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Persona getPersonaByFormato(String formato, String riga){
        Persona ret = null;
        String separatore = formato.substring(0,1);

        return ret;
    }

    public void salvaRubrica(){

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
