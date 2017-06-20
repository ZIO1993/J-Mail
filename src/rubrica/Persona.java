package rubrica;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ALDO on 20/06/2017.
 */
public class Persona {
    private String nome;
    private String cognome;
    private List<String> mails;
    private String nickname;

    public Persona(String mail){
        mails = new LinkedList<String>();
        mails.add(mail);
    }
}
