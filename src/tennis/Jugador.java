package tennis;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author KiKe i Dani
 */
public class Jugador extends JComponent implements Runnable
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static final int FRONT = 10;
    public static final int BACK = 11;

    public static final int POSX_INICIAL_BACK = 310;
    public static final int POSX_INICIAL_FRONT = 240;
    public static final int POSY_INICIAL_BACK = 490;
    public static final int POSY_INICIAL_FRONT = 185;

    private boolean direccio[];
    private boolean copDreta;
    private boolean copReves;

    private Pista pista;

    private Animacio animacions;
    private Pilota pilota;

    private int posX;
    private int posY;

    private Connexio conn;

    public int front_back;

    private boolean ultimEnviament;

    private boolean run = true;

    public Jugador( Pista _pista , int front_back , Connexio c)
    {
        pista = _pista;
        this.front_back = front_back;
        conn = c;

        direccio = new boolean[4];
        direccio[RIGHT] = false;
        direccio[LEFT] = false;
        direccio[UP] = false;
        direccio[DOWN] = false;
        copDreta = false;
        copReves = false;
    }

    public void initJugador(boolean personatge)
    {
        animacions = new Animacio(personatge);
        colocaPosInicial();
        new Thread(this).start();
    }

    public void colocaPosInicial()
    {
        if(front_back == BACK)
        {
            posX = POSX_INICIAL_BACK-(animacions.getBackFrame().getWidth()/2);
            posY = POSY_INICIAL_BACK-(animacions.getBackFrame().getHeight()/2);
        }
        else
        {
            posX = POSX_INICIAL_FRONT-(animacions.getFrontFrame().getWidth()/2);
            posY = POSY_INICIAL_FRONT-(animacions.getFrontFrame().getHeight()/2);
        }
    }

    public void paraThread()
    {
        run = false;
    }

    public void setPos(int _posX, int _posY)
    {
        posX = _posX - animacions.getFrontFrame().getWidth();
        posY = _posY - animacions.getFrontFrame().getHeight();
    }

    public void setAnimacio(int animacio)
    {
        animacions.setAnimacioActual(animacio);
    }
    
    public void setCopDreta(boolean b)
    {
        copDreta = b;
    }

    public void setCopReves(boolean b)
    {
        copReves = b;
    }

    public void setDireccio(boolean[] _direccio)
    {
        direccio = _direccio;
    }
    
    public boolean[] getDireccio()
    {
        return direccio;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public int getAmple()
    {
        return animacions.getBackFrame().getWidth();
    }

    public int getLlargada()
    {
        return animacions.getBackFrame().getHeight();
    }

    public boolean getTorn()
    {
        if(pista.getTorn()==1)  return true;
        else            return false;
    }

    private void comprovaAnimacio()
    {
        if(front_back == BACK)
        {
            if( direccio[LEFT] )    setAnimacio(Animacio.CAMINAR_ESQUERRA);
            if( direccio[RIGHT] )   setAnimacio(Animacio.CAMINAR_DRETA);
            if( direccio[DOWN] )    setAnimacio(Animacio.CAMINAR_AVALL);
            if( direccio[UP])       setAnimacio(Animacio.CAMINAR_AMUNT);
        }
        else
        {
            if( direccio[LEFT] )    setAnimacio(Animacio.CAMINAR_DRETA);
            if( direccio[RIGHT] )   setAnimacio(Animacio.CAMINAR_ESQUERRA);
            if( direccio[DOWN] )    setAnimacio(Animacio.CAMINAR_AMUNT);
            if( direccio[UP])       setAnimacio(Animacio.CAMINAR_AVALL);
        }

        if(direccio[DOWN] & direccio[RIGHT])    setAnimacio(Animacio.CAMINAR_DIAGONAL_DRETA);
        if(direccio[DOWN] & direccio[LEFT])     setAnimacio(Animacio.CAMINAR_DIAGONAL_ESQUERRA);
        if(direccio[UP] & direccio[RIGHT])      setAnimacio(Animacio.CAMINAR_DIAGONAL_ESQUERRA);
        if(direccio[UP] & direccio[LEFT])       setAnimacio(Animacio.CAMINAR_DIAGONAL_DRETA);

        if(!direccio[LEFT] & !direccio[RIGHT] & !direccio[UP] & !direccio[DOWN])    setAnimacio(Animacio.QUIET);

        if(copDreta)    setAnimacio(Animacio.COP_DRET);
        if(copReves)    setAnimacio(Animacio.COP_REVES);
    }

    private void comprovaLimits()
    {
        if(front_back == BACK)
        {
            if( animacions.getBackFrame() != null )
            {
                //Parets
                if(posX<=0)                                         posX = 0;
                if(posX+animacions.getBackFrame().getWidth()>=550)  posX = 550-animacions.getBackFrame().getWidth();
                if(posY+animacions.getBackFrame().getHeight()>=580) posY = 580-animacions.getBackFrame().getHeight();
                
                //Red
                if(posY<=330)   posY = 330;
            }
        }
        else
        {
            if( animacions.getFrontFrame() != null )
            {
                //Parets
                if(posX<=0)                                         posX = 0;
                if(posX+animacions.getFrontFrame().getWidth()>=550) posX = 550-animacions.getFrontFrame().getWidth();
                if(posY<=90)                                        posY = 90;

                //Red
                if(posY+animacions.getFrontFrame().getHeight()>=340)   posY = 340-animacions.getFrontFrame().getHeight();
            }
        }
    }

    @Override
    public void paintComponent( Graphics g )
    {
        if( front_back == FRONT )   g.drawImage(animacions.getFrontFrame(), posX, posY, this);
        else                        g.drawImage(animacions.getBackFrame(), posX, posY,  this);
    }

    @Override
    public void run()
    {
        while(run)
        {
            if(front_back == BACK)
            {
                if( direccio[RIGHT] )   this.posX += 3;
                if( direccio[LEFT] )    this.posX -= 3;
                if( direccio[UP] )      this.posY -= 3;
                if( direccio[DOWN] )    this.posY += 3;
            }
            else
            {
                if( direccio[RIGHT] )   this.posX -= 3;
                if( direccio[LEFT] )    this.posX += 3;
                if( direccio[UP] )      this.posY += 3;
                if( direccio[DOWN] )    this.posY -= 3;
            }

            if((direccio[RIGHT] | direccio[LEFT] | direccio[UP] | direccio[DOWN] | copDreta | copReves )& conn.isConnected() & front_back == BACK)
            {
                //Enviament de paquet
                Paquet p = new Paquet(1);
                p.afegeixPosJugador((int)posX, (int)posY);
                p.afegeixDireccio(direccio);
                p.afegeixCops(copDreta,copReves);
                String str = p.tancaPaquet();
                conn.write(str);
                ultimEnviament = true;
            }
            else
            {
                if(conn.isConnected() & front_back == BACK & ultimEnviament)
                {
                    //Enviament ultim paquet
                    Paquet p = new Paquet(1);
                    p.afegeixPosJugador((int) posX, (int)posY);
                    p.afegeixDireccio(direccio);
                    p.afegeixCops(copDreta,copReves);
                    String str = p.tancaPaquet();
                    conn.write(str);
                    ultimEnviament = false;
                }
            }

            comprovaAnimacio();
            comprovaLimits();
            
            pista.repaint();
            
            try
            {
                Thread.sleep(33);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
