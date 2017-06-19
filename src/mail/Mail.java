package mail;

import gui.Finestra;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import  java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail extends Thread{
    Finestra finestra;
	String destinatario;
	String mittente;
	String testo;
	String oggetto;
	
	String hostServer = "smtp.net.vodafone.it";
	Properties props;
	Session sessione;
	MimeMessage mail;

	List<File> lista;

	int debug = 1;
	
	public Mail(Finestra finestra, String destinatario, String mittente, String testo, String oggetto){
        this.finestra=finestra;
	    this.destinatario=destinatario;
		this.mittente=mittente;
		this.testo=testo;
		this.oggetto=oggetto;
		
		props = new Properties();
		props.put("mail.smtp.host", hostServer);
		props.put("port", 25);
		
		sessione = Session.getDefaultInstance(props);
		
		mail = new MimeMessage(sessione);
	}

	public Mail(Finestra finestra, String destinatario, String mittente, String testo, String oggetto, String hostServer){
        this.finestra=finestra;
	    this.destinatario=destinatario;
		this.mittente=mittente;
		this.testo=testo;
		this.oggetto=oggetto;
		this.hostServer=hostServer;
		props = new Properties();
		props.put("mail.smtp.host", hostServer);
		props.put("port", 25);

		sessione = Session.getDefaultInstance(props);
		mail = new MimeMessage(sessione);
	}

	public Mail(Finestra finestra, String destinatario, String mittente, String testo, String oggetto, String hostServer, List<File> lista){
		this.finestra=finestra;
	    this.destinatario=destinatario;
		this.mittente=mittente;
		this.testo=testo;
		this.oggetto=oggetto;
		this.hostServer=hostServer;
		this.lista=lista;
		props = new Properties();
		props.put("mail.smtp.host", hostServer);
		props.put("port", 25);

		sessione = Session.getDefaultInstance(props);
		mail = new MimeMessage(sessione);
	}
	
	public void run(){
		try{
			mail.setFrom(new InternetAddress(mittente));
			mail.addRecipients(Message.RecipientType.TO, destinatario);
			mail.setSubject(oggetto);
			mail.setText(testo);
			if(lista!=null){
			    Iterator<File> it = lista.iterator();
                while(it.hasNext()){
                    mail.setFileName(it.next().getName());
                }
			}
			Transport.send(mail);	
			debug = 0;
		}catch (Exception e) {
		    e.printStackTrace();
			debug = -1;
		}
        Finestra.Degub(debug, destinatario, mittente);
	}

	public void setFile(File f){
	    lista = new LinkedList<File>();
	    lista.add(f);
    }
}
