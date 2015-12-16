package tennis;

import java.awt.*;

/**
 *
 * @author Dani i KiKe
 */
public class Pista extends Pantalla
{
    public static final int AMPLE_CAMP = 550;
    public static final int ALT_CAMP = 610;

    private Main app;
    
    private Escenari escena;
    private Jugador jugador1;
    private Jugador jugador2;
    private Pilota pilota;
    private int puntsJug1;
    private int puntsJug2;
    private String nomJugador1;
    private String nomJugador2;
    private int jocsJug1;
    private int jocsJug2;

    private boolean isServer;
    private boolean elMeuTorn;

    private Connexio c = new Connexio();

    public Pista(Main _app)
    {
        app = _app;

        jugador1 = new Jugador(this, Jugador.BACK, c);
        jugador2 = new Jugador(this, Jugador.FRONT, c);
        pilota = new Pilota(jugador1.getPosX(), jugador1.getPosY(), this, c);
        pilota.setVisible(false);
        puntsJug1 = puntsJug2 = jocsJug1 = jocsJug2 = 0;
        nomJugador1 = "Usuari";
        nomJugador2 = "Contrincant";

        if(isServer())
        {
            int torn = (int) Math.round(Math.random());
            
            if(torn == 1)   elMeuTorn = true;
            else            elMeuTorn = false;
        }
    }

    public void afegeixTeclat()
    {
        app.addKeyListener( new ListenerTeclat(jugador1, pilota) );
        app.setFocusable(true);
    }

    public void estableixConnexio()
    {
        if(conf.getServ_Client())
        {
            ThRebreDades thConnexio = new ThRebreDades(c,Connexio.SERVER,this);
            thConnexio.start();
        }
        else
        {
            ThRebreDades thConnexio = new ThRebreDades(c,Connexio.CLIENT,this);
            thConnexio.start();
        }
    }

    public void preparaPista()
    {
        if(conf.getAparensa() == 1) escena = new Escenari("pista.png");
        else                        escena = new Escenari("pista2.png");

        this.add(escena);
    }

    public void preparaJugadors()
    {
        this.add(pilota);
        this.add(jugador1);
        this.add(jugador2);
        jugador1.initJugador(conf.getServ_Client());
        jugador2.initJugador(conf.getServ_Client());
        nomJugador1 = conf.getNomUsuari();
        isServer = conf.getServ_Client();
        
        pilota.setMidaPersonatge(jugador1.getAmple(),jugador1.getLlargada());
    }

    public void posicionaJugadors()
    {
        jugador1.colocaPosInicial();
        jugador2.colocaPosInicial();
    }

    public void paquetRebut(Paquet p)
    {
        switch(p.getTipus())
        {
            case 1: 
                jugador2.setDireccio(p.getDireccio());
                jugador2.setCopDreta(p.getCopDreta());
                jugador2.setCopReves(p.getCopReves());
                break;
            case 2:
                pilota.setVisible(true);
                pilota.setPos(p.getPilotaPosX(), p.getPilotaPosY(), p.getPilotaPosZ());
                pilota.setVelocitat(p.getVelX(), p.getVelY(), p.getVelZ());
                pilota.setAcceleracio(0.0, 0.0, -0.4);
                break;
            case 3:
                puntsJug1 = p.getPuntsJug1();
                puntsJug2 = p.getPuntsJug2();
                jocsJug1 = p.getJocsJug1();
                jocsJug2 = p.getJocsJug2();
                elMeuTorn = p.getTorn();
                jugador1.colocaPosInicial();
                jugador2.colocaPosInicial();
                break;
        }
    }

    public void setNomContrincant(String nom)
    {
        nomJugador2 = nom;
    }

    public void setPuntsJug1(int val)
    {
        puntsJug1 = val;
    }

    public void setPuntsJug2(int val)
    {
        puntsJug2 = val;
    }

    public void setJocsJug1(int val)
    {
        jocsJug1 = val;
    }

    public void setJocsJug2(int val)
    {
        jocsJug2 = val;
    }

    public void setTorn(boolean b)
    {
        elMeuTorn = b;
    }

    public void setTorn(String torn)
    {
        if( Integer.parseInt(torn) == 1)    elMeuTorn = false;
        else                                elMeuTorn = true;
    }

    public int getPuntsJug1()
    {
        return puntsJug1;
    }

    public int getPuntsJug2()
    {
        return puntsJug2;
    }

    public int getJocsJug1()
    {
        return jocsJug1;
    }

    public int getJocsJug2()
    {
        return jocsJug2;
    }   

    public boolean isServer()
    {
        return isServer;
    }

    public int getTorn()
    {
        if(elMeuTorn)   return 1;
        else            return 0;
    }

    public String administraPunts(int puntsJug)
    {
        String puntuacio = new String();

        switch(puntsJug)
        {
            case 0: puntuacio = "Puntuacio: 0";
                break;
            case 1: puntuacio = "Puntuacio: 15";
                break;
            case 2: puntuacio = "Puntuacio: 30";
                break;
            case 3: puntuacio = "Puntuacio: 40";
                break;
            case 4: puntuacio = "Puntuacio: avantatge";
                break;
            default: puntuacio = "Puntuacio: Error";
                break;
        }
        return puntuacio;
    }

    public void win(int guanyador)
    {
        conf.setGuanyador(guanyador);
        conf.setNomContrincant(nomJugador2);
        pilota.paraThread();
        jugador1.paraThread();
        jugador2.paraThread();
        c.disconnect();
        app.setPantalla(3);
    }

    @Override
    public void paintComponent( Graphics g )
    {
        //Sprites
        escena.update(g);
        jugador1.update(g);
        jugador2.update(g);
        pilota.update(g);
        
        //Lletra
        if(conf.getAparensa()==1)   g.setColor(Color.WHITE);
        fontEstandard = new Font("Arial",Font.BOLD,20);
        
        g.setFont(fontEstandard);
        g.drawString(nomJugador1, 20, 40);
        g.drawString("Jocs: "+Integer.toString(jocsJug1), 20, 60);
        g.drawString(administraPunts(puntsJug1), 20, 80);
        g.drawString(nomJugador2, 310, 40);
        g.drawString("Jocs: "+Integer.toString(jocsJug2), 310, 60);
        g.drawString(administraPunts(puntsJug2), 310, 80);
    }
}
