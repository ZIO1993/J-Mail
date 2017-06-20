package gui;

import licenza.Licenza;
import mail.Mail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class Finestra extends Thread{
    private String titolo="J-Mail";
    private JFrame frm;

    private JButton cambiaServer;
    private JButton pulisci;
    private JButton invia;

    private JTextArea dest;
    private JTextArea mitt;
    private JTextArea ogg;
    private JTextArea text;
    private JTextField textFile;

	private JTextField loop;
    private JButton open;
	static JLabel debug;

    private Color rosso = new Color(255,0,0);
    private Color verde = new Color(0,255,0);

    private File file;

    private String hostServer = "smtp.net.vodafone.it";
	
	private Finestra() {
		frm = creaFinestra(titolo);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
	}
	
	public static void main(String[] args) {
		Licenza l = new Licenza();
		l.start();
	}

	public static void accettata(Licenza l){
		l.nascondi();
		Finestra frame = new Finestra();
		frame.start();
	}

	private JFrame creaFinestra(String titolo){
		frm = new JFrame(titolo);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frm.setSize(700, 600);
		
		//frm.setLocale(null);
		Container frmContainer= frm.getContentPane();
		frmContainer.setLayout(new BorderLayout());
		
		JPanel Centro = creaCentro();
		JPanel Sud = creaSud();
		
		frmContainer.add(Centro, BorderLayout.CENTER);
		frmContainer.add(Sud, BorderLayout.SOUTH);
		
		frm.pack();
		return frm;
	}
	
	private JPanel creaCentro(){
		JPanel cx = new JPanel();
		cx.setLayout(new GridLayout(5, 1));
		
		//Prima parte
		JPanel primo = new JPanel(new BorderLayout());
		JLabel labelDest = new JLabel("Destinatari");
		labelDest.setHorizontalAlignment(SwingConstants.CENTER);
		primo.add(labelDest, BorderLayout.NORTH);
		dest = new JTextArea("esempio@gmail.com", 1, 30);
		JScrollPane scrollDest = new JScrollPane(dest);
		primo.add(scrollDest);
		cx.add(primo);
		primo.add(new JLabel("   "), BorderLayout.SOUTH);

		//Seconda parte
		JPanel secondo = new JPanel(new BorderLayout());
		JLabel labelMitt = new JLabel("Mittenti");
		labelMitt.setHorizontalAlignment(SwingConstants.CENTER);
		secondo.add(labelMitt, BorderLayout.NORTH);
		mitt = new JTextArea("esempio@gmail.com", 1, 30);
		JScrollPane scrollMitt = new JScrollPane(mitt);
		secondo.add(scrollMitt);
		cx.add(secondo);
        secondo.add(new JLabel("   "), BorderLayout.SOUTH);

        //terza parte
		JPanel terzo = new JPanel(new BorderLayout());
		JLabel oggetto=new JLabel("Oggetto");
		oggetto.setHorizontalAlignment(SwingConstants.CENTER);
		terzo.add(oggetto, BorderLayout.NORTH);
		ogg = new JTextArea("Inserisci l'oggetto", 1, 30);
		JScrollPane scrollOgg = new JScrollPane(ogg);
		terzo.add(scrollOgg);
		cx.add(terzo);
        terzo.add(new JLabel("   "), BorderLayout.SOUTH);

		//quarta parte
        JPanel quarto = new JPanel(new BorderLayout());
        JLabel labelFile = new JLabel("Allegati");
        labelFile.setHorizontalAlignment(JLabel.CENTER);
        quarto.add(labelFile, BorderLayout.NORTH);
        textFile = new JTextField("Nessun file inserito.", 30);
        textFile.setForeground(rosso);
        textFile.setHorizontalAlignment(JTextField.CENTER);
        textFile.setEditable(false);
        JScrollPane scrollFile = new JScrollPane(textFile);
        quarto.add(scrollFile);
        cx.add(quarto);
        open = new JButton("Seleziona File");
        open.setToolTipText("Consente di seleziona un file");
        //open.setMaximumSize(new Dimension(10,3));
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apri();
            }
        });
        JPanel x = new JPanel(new FlowLayout());
        x.add(open);
        quarto.add(x, BorderLayout.SOUTH);

		//quinta parte
		JPanel quinto = new JPanel(new BorderLayout());
		JLabel labelText = new JLabel("Testo");
		labelText.setHorizontalAlignment(SwingConstants.CENTER);
        quinto.add(labelText, BorderLayout.NORTH);
		text = new JTextArea("Inserisci qui il testo..", 5, 30);
		JScrollPane scrollText = new JScrollPane(text);
        quinto.add(scrollText);
		cx.add(quinto);
		
		return cx;
	}
	
	private JPanel creaSud(){
		JPanel sud = new JPanel();
		sud.setLayout(new GridLayout(2, 1));
		JPanel sudx = new JPanel(new FlowLayout());
		loop = new JTextField("001");
		loop.setToolTipText("Permette di indicare la quantità di volte che la stessa mail verrà inviata, Non può essere negativo!");
		cambiaServer = new JButton("Cambia Server");
		pulisci = new JButton("Pulisci");
		invia = new JButton("Invia");
		
		debug = new JLabel("Benvenuto in J-Mail! Ricorda che io non mi assumero nessuna responsabilità per le mail inviate. Buon invio!");
		debug.setHorizontalAlignment(SwingConstants.CENTER);
		cambiaServer.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String host = JOptionPane.showInputDialog("Inserisci il nuovo indirizzo", hostServer);
				if(host != null && !host.equals(hostServer)){
					hostServer=host;
				}
			}
		});
		
		pulisci.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dest.setText("");
				mitt.setText("");
				ogg.setText("");
				text.setText("");
				file=null;
				textFile.setText("Seleziona file");
				textFile.setForeground(rosso);
				debug.setText("Pulito");
			}
		});
		
		invia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				invia();
			}
		});
		sudx.add(new JLabel("Mail da inviare:"));
		sudx.add(loop);
		//sudx.add(new JSeparator(JSeparator.VERTICAL));
		sudx.add(cambiaServer);
		sudx.add(pulisci);
		sudx.add(invia);
		sud.add(sudx);
		sud.add(debug);
		return sud;
	}

    private void invia(){
		String[] destinatari = dest.getText().replaceAll(" ", "").split(";");
		String[] mittenti = mitt.getText().replaceAll(" ", "").split(";");
        for(int i=0; i<destinatari.length; i++){
            if(!destinatari[i].contains("@") || !destinatari[i].contains(".")){
                destinatari[i] = "NULL";
            }
		}

        for(int i=0; i<mittenti.length; i++){
            if(!mittenti[i].contains("@") || !mittenti[i].contains(".")){
                mittenti[i] = "NULL";
            }
        }


		String temp =loop.getText();
        int l = Integer.parseInt(temp);
		if(l<0){
			debug.setText("Il numero inserito non è consentito");
			return;
		}
		for(int k=0; k<l; k++){
			for(int j=0; j<mittenti.length && mittenti[j]!=null; j++){
				for(int i=0; i<destinatari.length && destinatari[i]!=null; i++){
				    if(destinatari[i].equals("NULL")){
                        debug.setText("Destinatario non valido!");
                    }else if(mittenti[j].equals("NULL")){
                        debug.setText("Mittente non valido!");
                    }else {
                        Mail mail = new Mail(this, destinatari[i], mittenti[j], text.getText(), ogg.getText(), hostServer);
                        if(file != null)
                            mail.setFile(file);

                        mail.run();
                    }
				}
			}
		}
	}

	public synchronized static void Degub(int deb, String dest, String mitt){
        if(deb == 0){
            debug.setText("Mail inviata correttamente a "+ dest+" da "+ mitt);
        }else{
            debug.setText("Errore nella mail da inviare a "+ dest+" da "+ mitt);
        }
    }

    private void apri(){
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setApproveButtonText("Apri");
        int ret = fileChooser.showOpenDialog(null);
        if(ret == JFileChooser.ERROR_OPTION){
            textFile.setText("Errore nell'aprire il file!");
            textFile.setForeground(rosso);
        }else if(ret == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            textFile.setForeground(verde);
            textFile.setText("Selezionato: " + file.getName());
        }

    }
}
