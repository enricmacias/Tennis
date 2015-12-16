/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Dani i KiKe
 */
public class Configuracio extends JPanel
{
    private Main app;

    private JLabel labelLogin;
    private JLabel labelIP;
    private JLabel labelPort;
    private JLabel labelServClient;
    private JLabel labelAparensa;

    private JTextField TFlogin;
    private JTextField TFip;
    private JTextField TFport;

    private CheckboxGroup cbServClient;
    private CheckboxGroup cbAparensa;

    private String nomUsuari;
    private String nomContrincant;
    private String IP;
    private int port;
    private boolean serv_client;  //TRUE->SERVER FALSE->CLIENT
    private int aparensa;

    private int guanyador;

    public Configuracio(Main _app,Font estandard)
    {
        app = _app;

        setLayout(new GridLayout(5,2));

        //Nom usuari
        labelLogin = new JLabel("Nom usuari:");
        labelLogin.setFont(estandard);
        JPanel pLabelLogin = new JPanel();
        pLabelLogin.add(labelLogin);
        add(pLabelLogin);
        TFlogin = new JTextField("KiKe",10);
        TFlogin.setFont(estandard);
        JPanel pLogin = new JPanel();
        pLogin.add(TFlogin);
        add(pLogin);

        //IP
        labelIP = new JLabel("IP:");
        labelIP.setFont(estandard);
        JPanel pLabelIP = new JPanel();
        pLabelIP.add(labelIP);
        add(pLabelIP);
        TFip = new JTextField("127.1.0.0",10);
        TFip.setFont(estandard);
        JPanel pIP = new JPanel();
        pIP.add(TFip);
        add(pIP);

        //Port
        labelPort = new JLabel("Port:");
        labelPort.setFont(estandard);
        JPanel pLabelPort = new JPanel();
        pLabelPort.add(labelPort);
        add(pLabelPort);
        TFport = new JTextField("5000",10);
        TFport.setFont(estandard);
        JPanel pPort = new JPanel();
        pPort.add(TFport);
        add(pPort);

        //Servidor/Client
        Font fontCheckbox = new Font("OCR A Extended",Font.BOLD,20);

        labelServClient = new JLabel("Escull:");
        labelServClient.setFont(estandard);
        JPanel pLabelServClient = new JPanel();
        pLabelServClient.add(labelServClient);
        add(pLabelServClient);
        cbServClient = new CheckboxGroup();
        Checkbox opcio1 = new Checkbox("Servidor", cbServClient, true);
        opcio1.setFont(fontCheckbox);
        Checkbox opcio2 = new Checkbox("Client", cbServClient, false);
        opcio2.setFont(fontCheckbox);
        JPanel pServClient = new JPanel(new GridLayout(2,1));
        pServClient.add(opcio1);
        pServClient.add(opcio2);
        add(pServClient);

         //Opcions d'aparença
        labelAparensa = new JLabel("Aparença:");
        labelAparensa.setFont(estandard);
        JPanel pLabelAparensa = new JPanel();
        pLabelAparensa.add(labelAparensa);
        add(pLabelAparensa);
        cbAparensa = new CheckboxGroup();
        Checkbox opcio3 = new Checkbox("Terra", cbAparensa, true);
        opcio3.setFont(fontCheckbox);
        Checkbox opcio4 = new Checkbox("Gespa", cbAparensa, false);
        opcio4.setFont(fontCheckbox);
        JPanel pAparensa = new JPanel(new GridLayout(2,1));
        pAparensa.add(opcio3);
        pAparensa.add(opcio4);
        add(pAparensa);
    }

    public void guardaConfiguracio()
    {
        nomUsuari = TFlogin.getText();
        IP = TFip.getText();
        port = Integer.parseInt(TFport.getText());

        if( cbServClient.getSelectedCheckbox().getLabel().compareTo("Servidor") == 0 )  serv_client = true;
        else                                                                            serv_client = false;

        if( cbAparensa.getSelectedCheckbox().getLabel().compareTo("Terra") == 0 )       aparensa = 1;
        else                                                                            aparensa = 2;
    }

    public String getNomUsuari()
    {
        return nomUsuari;
    }

    public String getNomContrincant()
    {
        return nomContrincant;
    }

    public String getIP()
    {
        return IP;
    }

    public int getPort()
    {
        return port;
    }

    public boolean getServ_Client()
    {
        return serv_client;
    }

    public int getAparensa()
    {
        return aparensa;
    }

    public int getGuanyador()
    {
        return guanyador;
    }

    public void setGuanyador(int guanyador)
    {
        this.guanyador = guanyador;
    }

    public void setNomContrincant(String nom)
    {
        nomContrincant = nom;
    }

    @Override
    public void paintComponent( Graphics g )
    {
        labelLogin.update(g);
        labelIP.update(g);
        labelPort.update(g);
        labelServClient.update(g);
        labelAparensa.update(g);
        TFlogin.update(g);
        TFip.update(g);
        TFport.update(g);
    }
}
