package tennis;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 *
 * @author Dani i KiKe
 */
public class Pilota extends JComponent implements Runnable
{
    public boolean direccio[];

    private Pista pista;

    private int posX;
    private int posY;
    private int posZ;

    private double velX;
    private double velY;
    private double velZ;
    private double accX;
    private double accY;
    private double accZ;

    private int width;
    private int height;

    private BufferedImage img;

    public static boolean visible;

    private int widthPersonatge;
    private int heightPersonatge;

    private Connexio conn;
    private boolean copPilota;

    private boolean botAnterior;

    private int puntJugador;

    private boolean run = true;

    public Pilota(int posJX, int posJY, Pista pista, Connexio c)
    {
        this.pista = pista;
        this.conn = c;

        copPilota = false;
        width = 10;
        height= 10;

        try
        {
            img =  ImageIO.read( new File("./img/bola.png"));
        }
        catch(Exception e)
        {
            System.out.println("No s'ha pogut carregar la imatge de la pilota");
        }

        new Thread(this).start();
    }

    public void saque(int posJugX, int posJugY)
    {
        copPilota = true;
        botAnterior = false;
        setPosSaque(posJugX,posJugY,0);
        setVisible(true);
        setVelocitat(0, 0, 3.0);
        setAcceleracio(0,0, -0.4);
    }

    public void copReves(int posJugX,int posJugY)
    {
        
        if( (posX >= posJugX && posX <= posJugX+widthPersonatge+(widthPersonatge/2) ) )
        {
            if( (posY >= posJugY-(heightPersonatge/2)) && posY <= posJugY+(heightPersonatge/2) )
            {
                copPilota = true;
                if(posZ >2 )
                {
                    setVelocitat(5.0,-12.0, velZ+2.1);
                    setAcceleracio(0.0,0.0,-0.4);
                }
                else
                {
                    System.out.println("You fail at making a reves");
                }
            }
        }else   System.out.println("No toques bola");
    }

    public void copDreta(int posJugX,int posJugY)
    {
        
        if( (posX >= posJugX-(widthPersonatge/2)) && posX <= posJugX+(widthPersonatge) )
        {
            if( (posY >= posJugY-(heightPersonatge/2)) && posY <= posJugY+(heightPersonatge/2) )
            {
                copPilota = true;
                if(posZ >2 )
                {
                    setVelocitat(-5.0,-12.0, velZ+2.1);
                    setAcceleracio(0.0,0.0,-0.4);
                }
                else
                {
                    System.out.println("You fail at making a cop de dreta");
                }
            }
        }else   System.out.println("No toques bola");
    }

    public void paraThread()
    {
        run = false;
    }

    public void setPos(int posX, int posY , int posZ)
    {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public void setPosSaque(int posX, int posY , int posZ)
    {
        this.posX = posX+(widthPersonatge/2)-(width/2);
        this.posY = posY+(heightPersonatge/2)-(height/2);
        this.posZ = posZ;
    }

    public void setVelocitat(double velX, double velY, double velZ)
    {
        this.velX = velX;
        this.velY = velY;
        this.velZ = velZ;
    }

     public void setAcceleracio(double accX, double accY, double accZ)
    {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }

     public void setMidaPersonatge(int width, int height)
     {
         this.widthPersonatge = width;
         this.heightPersonatge = height;
     }

    @Override
    public void paintComponent( Graphics g )
    {
        g.drawImage(img, posX, posY-3*Math.round(posZ), width+posZ, height+posZ, this);
    }

    private boolean calculaLimitsCamp()
    {
        boolean dintre = false;//Fora de camp
        if(velY < 0)
        {
            if(posX > 61 && posX < 486)
            {
                if(posY > 212 && posY < 358)// 503 es el total del camp
                {
                    dintre = true;                    
                }
            }
        }
        if(velY > 0)
        {
             if(posX > 61 && posX < 486)
            {
                if(posY > 358 && posY < 503)// 503 es el total del camp
                {
                    dintre = true;
                }
            }
              
        }
        return dintre;
    }

    public void mouPilota()
    {
        posX = (int)Math.round(velX + posX + 1/2 * accX);
        posY = (int)Math.round(velY + posY + 1/2 * accY);
        posZ = (int)Math.round(velZ + posZ + 1/2 * accZ);
        velX = velX + accX;
        velY = velY + accY;
        velZ = velZ + accZ;
        if(Math.abs(velX) == 0) accX = 0;
        if(Math.abs(velY) == 0) accY = 0;
        if(posZ<=0)
        {
            velZ = (velZ * -1) - 1;
        }
        else
        {
           if((posZ <=1)  && (Math.abs(velZ)<= 2))
            {
                velZ = 0;
                accZ = 0;

            }
        }

    }

    public int calculaPunts()
    {
        boolean dintreCamp;
        
        int punt = 0;
        if(posZ <= 0)
        {
            dintreCamp = calculaLimitsCamp();
            if(botAnterior == false)
            {
                if(dintreCamp == true)          botAnterior = true; 
                else//Ha anat fora
                {
                    if(velY < 0)//L'ultim en tocar bola ha estat jugador 1
                    {
                        //Punt pel jugador 2
                        punt = 2;
                        pista.setTorn(false);
                        pista.posicionaJugadors();
                    }
                    else
                    {
                        if(velY >0)
                        {
                            //punt pel jugador 1
                            punt = 1;
                            pista.setTorn(true);
                            pista.posicionaJugadors();
                        }
                    }
                }
            }
            else
            {
                if(velY < 0)//L'ultim en tocar bola ha estat jugador 1
                {
                    //Punt pel jugador 1
                    punt = 1;
                    pista.setTorn(true);
                    pista.posicionaJugadors();
                }
                else
                {
                    //punt pel jugador 2
                    punt = 2;
                    pista.setTorn(false);
                    pista.posicionaJugadors();
                }
            }
        }

        return punt;
    }

    public void aturaPilota()
    {
        setVisible(false);
        velX = 0.0;
        velY = 0.0;
        velZ = 0.0;
        accX = 0.0;
        accY = 0.0;
        accZ = 0.0;
        posX = 1000;
        posY = 1000;
    }

    private void sumaPunt()
    {
        int aux;
        if(puntJugador==1)
        {
            aux = pista.getPuntsJug1();
            pista.setPuntsJug1(++aux);
            if(aux == 4)//Si tenim avantatge
            {
                if(pista.getPuntsJug2() == 4 )//Si el contrincant té avantatge
                {
                    pista.setPuntsJug2(3);
                    pista.setPuntsJug1(3);
                }
                else
                {
                    if(pista.getPuntsJug2() < 3) //Si el contrincant no esta a 40
                    {
                        pista.setPuntsJug1(0);// 5 = win
                        pista.setPuntsJug2(0);// 5 = win
                        pista.setJocsJug1(pista.getJocsJug1()+1);
                    }
                }
            }
            if(aux==5)
            {
                pista.setPuntsJug1(0);// 5 = win
                pista.setPuntsJug2(0);// 5 = win
                pista.setJocsJug1(pista.getJocsJug1()+1);// 5 = win
            }
        }
        else 
        {
            if(puntJugador == 2)
            {
                aux = pista.getPuntsJug2();
                pista.setPuntsJug2(++aux);
                if(aux == 4)//Si tenim avantatge
                {
                    if(pista.getPuntsJug1() == 4 )//Si el contrincant té avantatge
                    {
                        pista.setPuntsJug1(3);
                        pista.setPuntsJug2(3);
                    }
                    else
                    {
                        if(pista.getPuntsJug1() < 3) //Si el contrincant no esta a 40
                        {
                            pista.setPuntsJug1(0);// 5 = win
                            pista.setPuntsJug2(0);// 5 = win
                            pista.setJocsJug2(pista.getJocsJug2()+1);// 5 = win
                        }
                    }
                 }
                if(aux==5)
                {
                    pista.setPuntsJug1(0);// 5 = win
                    pista.setPuntsJug2(0);// 5 = win
                    pista.setJocsJug2(pista.getJocsJug2()+1);// 5 = win
                }
            }
        }
    }

    private void enviaPuntuacio()
    {
        //Enviament de paquet
        Paquet p = new Paquet(3);
        p.afegeixPunts(pista.getPuntsJug1(), pista.getPuntsJug2());
        p.afegeixJocs(pista.getJocsJug1(), pista.getJocsJug2());
        p.afegeixTorn(pista.getTorn());
        String str = p.tancaPaquet();

        conn.write(str);
    }

    public void run()
    {
        while(run)
        {
            this.mouPilota();
            if(pista.isServer()) puntJugador = calculaPunts();
            if(puntJugador!=0)
            {
                if(pista.isServer()) botAnterior = false;
                this.aturaPilota();
                if(pista.isServer())
                {
                    sumaPunt();
                    enviaPuntuacio();
                }
            }
            if(pista.isServer())
            {
                if(pista.getJocsJug1()>= 6 )  pista.win(1);
                if(pista.getJocsJug2()>=6 )    pista.win(2);
            }
            else
            {
                if(pista.getJocsJug1()>= 6 )  pista.win(1);
                if(pista.getJocsJug2()>=6 )    pista.win(2);
            }
        
            pista.repaint();

            if(copPilota)
            {
                //Enviament de paquet
                Paquet p = new Paquet(2);
                p.afegeixPosPilota(posX, posY, posZ);
                p.afegeixVelocitat(velX,velY,velZ);
                String str = p.tancaPaquet();

                conn.write(str);
                copPilota = false;
            }

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
