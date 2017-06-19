package update;

/**
 * Created by ALDO on 19/06/2017.
 */
public class Versione {
    private static String[] vers;

    public Versione(String x){
        vers = x.split(".");
    }

    public static String getVersioneCorrente(){
        String ret ="";
        for(int i=0; i<vers.length; i++){
            if(i != 0) ret +=".";
            ret+=vers[i];
        }
        return ret;
    }
}
