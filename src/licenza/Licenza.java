package licenza;

import gui.Finestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ALDO on 19/06/2017.
 */
public class Licenza extends Thread {
    private String titolo = "Accetta per proseguire...";
    private String testo = "L'autore del programma non si assumerà nessuna responsabilità in merito ai danni arrecati a cose e/o persone" +
                            "\nprovocati dall'uso di questo programma e all'invio dei file e/o contenuti nelle e-mail inviate dal medesimo," +
                            "\ninoltre ricorda che l'invio di e-mail contenenti malware e/o minaccie a cose e/o persone" +
                            "\nè un reato penale punibile con la reclusione, così come è anche reato penale lo stalking." +
                            "\nInoltre ricordo che l'utilizzo di questo programma non garantisce il corretto invio delle e-mail" +
                            "\nDetto ciò vi auguro un buon invio!\n\nAldo D'Introno";

    private static JFrame frame;
    private JButton accetto;
    private JButton declino;
    private JTextArea areaTesto;
    private JLabel labelTitolo;

    public void run() {
        //avvio
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Licenza() {
        //Set iniziali
        frame = new JFrame("J-Mail");
        Container c = frame.getContentPane();
        JPanel pane = new JPanel(new BorderLayout());
        c.add(pane);

        //creazione modello
        //nord
        labelTitolo = new JLabel(titolo);
        labelTitolo.setHorizontalAlignment(JLabel.CENTER);
        pane.add(labelTitolo, BorderLayout.NORTH);
        //centro
        areaTesto = new JTextArea(testo, 8, 55);
        areaTesto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTesto);
        pane.add(scroll, BorderLayout.CENTER);
        //sud
        JPanel sx = new JPanel();
        accetto = new JButton("Accetto");
        declino = new JButton("Declino");
        sx.add(declino);
        sx.add(accetto);
        pane.add(sx, BorderLayout.SOUTH);

        //pulsanti
        accetto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accettato();
            }
        });

        declino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void nascondi(){
        frame.setVisible(false);
    }

    private void accettato() {
        Finestra.accettata(this);
    }
}