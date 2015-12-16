/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
 *
 * @author Dani i KiKe
 */
public class Animacio implements Runnable
{
    public static final int QUIET = 0;
    public static final int CAMINAR_DRETA = 1;
    public static final int CAMINAR_ESQUERRA = 2;
    public static final int CAMINAR_AMUNT = 3;
    public static final int CAMINAR_AVALL = 4;
    public static final int CAMINAR_DIAGONAL_DRETA = 5;
    public static final int CAMINAR_DIAGONAL_ESQUERRA = 6;
    public static final int COP_DRET = 7;
    public static final int COP_REVES = 8;

    private BufferedImage imatgesFront[][];
    private BufferedImage imatgesBack[][];
    private int frameAct;
    private int animacioAct;

    public Animacio(boolean personatge)
    {
        imatgesFront = new BufferedImage[10][4];
        imatgesBack = new BufferedImage[10][3];
        
        try
        {
            if(!personatge)
            {
                imatgesFront[QUIET][0] = ImageIO.read( new File("./img/dk_front_quiet1.png"));
                imatgesFront[QUIET][1] = ImageIO.read( new File("./img/dk_front_quiet2.png"));
                imatgesFront[QUIET][2] = ImageIO.read( new File("./img/dk_front_quiet3.png"));
                imatgesFront[CAMINAR_DRETA][0] = ImageIO.read( new File("./img/dk_front_caminarDreta1.png"));
                imatgesFront[CAMINAR_DRETA][1] = ImageIO.read( new File("./img/dk_front_caminarDreta2.png"));
                imatgesFront[CAMINAR_ESQUERRA][0] = ImageIO.read( new File("./img/dk_front_caminarEsq1.png"));
                imatgesFront[CAMINAR_ESQUERRA][1] = ImageIO.read( new File("./img/dk_front_caminarEsq2.png"));
                imatgesFront[CAMINAR_AMUNT][0] = ImageIO.read( new File("./img/dk_front_endavant1.png"));
                imatgesFront[CAMINAR_AMUNT][1] = ImageIO.read( new File("./img/dk_front_endavant2.png"));
                imatgesFront[CAMINAR_AVALL][0] = ImageIO.read( new File("./img/dk_front_endavant1.png"));
                imatgesFront[CAMINAR_AVALL][1] = ImageIO.read( new File("./img/dk_front_endavant2.png"));
                imatgesFront[CAMINAR_DIAGONAL_DRETA][0] = ImageIO.read( new File("./img/dk_front_caminarDiagonalDreta1.png"));
                imatgesFront[CAMINAR_DIAGONAL_DRETA][1] = ImageIO.read( new File("./img/dk_front_caminarDiagonalDreta2.png"));
                imatgesFront[CAMINAR_DIAGONAL_ESQUERRA][0] = ImageIO.read( new File("./img/dk_front_caminarDiagonalEsq1.png"));
                imatgesFront[CAMINAR_DIAGONAL_ESQUERRA][1] = ImageIO.read( new File("./img/dk_front_caminarDiagonalEsq2.png"));
                imatgesFront[COP_DRET][0] = ImageIO.read( new File("./img/dk_front_cDreta1.png"));
                imatgesFront[COP_DRET][1] = ImageIO.read( new File("./img/dk_front_cDreta2.png"));
                imatgesFront[COP_DRET][2] = ImageIO.read( new File("./img/dk_front_cDreta3.png"));
                imatgesFront[COP_REVES][0] = ImageIO.read( new File("./img/dk_front_cReves1.png"));
                imatgesFront[COP_REVES][1] = ImageIO.read( new File("./img/dk_front_cReves2.png"));
                imatgesFront[COP_REVES][2] = ImageIO.read( new File("./img/dk_front_cReves3.png"));

                imatgesBack[QUIET][0] = ImageIO.read( new File("./img/yoshi_back_quiet1.png"));
                imatgesBack[QUIET][1] = ImageIO.read( new File("./img/yoshi_back_quiet2.png"));
                imatgesBack[QUIET][2] = ImageIO.read( new File("./img/yoshi_back_quiet3.png"));
                imatgesBack[CAMINAR_DRETA][0] = ImageIO.read( new File("./img/yoshi_back_caminarDreta1.png"));
                imatgesBack[CAMINAR_DRETA][1] = ImageIO.read( new File("./img/yoshi_back_caminarDreta2.png"));
                imatgesBack[CAMINAR_ESQUERRA][0] = ImageIO.read( new File("./img/yoshi_back_caminarEsq1.png"));
                imatgesBack[CAMINAR_ESQUERRA][1] = ImageIO.read( new File("./img/yoshi_back_caminarEsq2.png"));
                imatgesBack[CAMINAR_AMUNT][0] = ImageIO.read( new File("./img/yoshi_back_endavant1.png"));
                imatgesBack[CAMINAR_AMUNT][1] = ImageIO.read( new File("./img/yoshi_back_endavant2.png"));
                imatgesBack[CAMINAR_AVALL][0] = ImageIO.read( new File("./img/yoshi_back_endavant1.png"));
                imatgesBack[CAMINAR_AVALL][1] = ImageIO.read( new File("./img/yoshi_back_endavant2.png"));
                imatgesBack[CAMINAR_DIAGONAL_DRETA][0] = ImageIO.read( new File("./img/yoshi_back_caminarDiagonalEsq1.png"));
                imatgesBack[CAMINAR_DIAGONAL_DRETA][1] = ImageIO.read( new File("./img/yoshi_back_caminarDiagonalEsq2.png"));
                imatgesBack[CAMINAR_DIAGONAL_ESQUERRA][0] = ImageIO.read( new File("./img/yoshi_back_caminarDiagonalDreta1.png"));
                imatgesBack[CAMINAR_DIAGONAL_ESQUERRA][1] = ImageIO.read( new File("./img/yoshi_back_caminarDiagonalDreta2.png"));
                imatgesBack[COP_DRET][0] = ImageIO.read( new File("./img/yoshi_back_cDreta1.png"));
                imatgesBack[COP_DRET][1] = ImageIO.read( new File("./img/yoshi_back_cDreta2.png"));
                imatgesBack[COP_DRET][2] = ImageIO.read( new File("./img/yoshi_back_cDreta3.png"));
                imatgesBack[COP_REVES][0] = ImageIO.read( new File("./img/yoshi_back_cReves1.png"));
                imatgesBack[COP_REVES][1] = ImageIO.read( new File("./img/yoshi_back_cReves2.png"));
                imatgesBack[COP_REVES][2] = ImageIO.read( new File("./img/yoshi_back_cReves3.png"));
            }
            else
            {
                imatgesFront[QUIET][0] = ImageIO.read( new File("./img/yoshi_front_quiet1.png"));
                imatgesFront[QUIET][1] = ImageIO.read( new File("./img/yoshi_front_quiet2.png"));
                imatgesFront[QUIET][2] = ImageIO.read( new File("./img/yoshi_front_quiet3.png"));
                imatgesFront[CAMINAR_DRETA][0] = ImageIO.read( new File("./img/yoshi_front_caminarDreta1.png"));
                imatgesFront[CAMINAR_DRETA][1] = ImageIO.read( new File("./img/yoshi_front_caminarDreta2.png"));
                imatgesFront[CAMINAR_ESQUERRA][0] = ImageIO.read( new File("./img/yoshi_front_caminarEsq1.png"));
                imatgesFront[CAMINAR_ESQUERRA][1] = ImageIO.read( new File("./img/yoshi_front_caminarEsq2.png"));
                imatgesFront[CAMINAR_AMUNT][0] = ImageIO.read( new File("./img/yoshi_front_endavant1.png"));
                imatgesFront[CAMINAR_AMUNT][1] = ImageIO.read( new File("./img/yoshi_front_endavant2.png"));
                imatgesFront[CAMINAR_AVALL][0] = ImageIO.read( new File("./img/yoshi_front_endavant1.png"));
                imatgesFront[CAMINAR_AVALL][1] = ImageIO.read( new File("./img/yoshi_front_endavant2.png"));
                imatgesFront[CAMINAR_DIAGONAL_DRETA][0] = ImageIO.read( new File("./img/yoshi_front_caminarDiagonalDreta1.png"));
                imatgesFront[CAMINAR_DIAGONAL_DRETA][1] = ImageIO.read( new File("./img/yoshi_front_caminarDiagonalDreta2.png"));
                imatgesFront[CAMINAR_DIAGONAL_ESQUERRA][0] = ImageIO.read( new File("./img/yoshi_front_caminarDiagonalEsq1.png"));
                imatgesFront[CAMINAR_DIAGONAL_ESQUERRA][1] = ImageIO.read( new File("./img/yoshi_front_caminarDiagonalEsq2.png"));
                imatgesFront[COP_DRET][0] = ImageIO.read( new File("./img/yoshi_front_cDreta1.png"));
                imatgesFront[COP_DRET][1] = ImageIO.read( new File("./img/yoshi_front_cDreta2.png"));
                imatgesFront[COP_DRET][2] = ImageIO.read( new File("./img/yoshi_front_cDreta3.png"));
                imatgesFront[COP_REVES][0] = ImageIO.read( new File("./img/yoshi_front_cReves1.png"));
                imatgesFront[COP_REVES][1] = ImageIO.read( new File("./img/yoshi_front_cReves2.png"));
                imatgesFront[COP_REVES][2] = ImageIO.read( new File("./img/yoshi_front_cReves3.png"));

                imatgesBack[QUIET][0] = ImageIO.read( new File("./img/dk_back_quiet1.png"));
                imatgesBack[QUIET][1] = ImageIO.read( new File("./img/dk_back_quiet2.png"));
                imatgesBack[QUIET][2] = ImageIO.read( new File("./img/dk_back_quiet3.png"));
                imatgesBack[CAMINAR_DRETA][0] = ImageIO.read( new File("./img/dk_back_caminarDreta1.png"));
                imatgesBack[CAMINAR_DRETA][1] = ImageIO.read( new File("./img/dk_back_caminarDreta2.png"));
                imatgesBack[CAMINAR_ESQUERRA][0] = ImageIO.read( new File("./img/dk_back_caminarEsq1.png"));
                imatgesBack[CAMINAR_ESQUERRA][1] = ImageIO.read( new File("./img/dk_back_caminarEsq2.png"));
                imatgesBack[CAMINAR_AMUNT][0] = ImageIO.read( new File("./img/dk_back_endavant1.png"));
                imatgesBack[CAMINAR_AMUNT][1] = ImageIO.read( new File("./img/dk_back_endavant2.png"));
                imatgesBack[CAMINAR_AVALL][0] = ImageIO.read( new File("./img/dk_back_endavant1.png"));
                imatgesBack[CAMINAR_AVALL][1] = ImageIO.read( new File("./img/dk_back_endavant2.png"));
                imatgesBack[CAMINAR_DIAGONAL_DRETA][0] = ImageIO.read( new File("./img/dk_back_caminarDiagonalEsq1.png"));
                imatgesBack[CAMINAR_DIAGONAL_DRETA][1] = ImageIO.read( new File("./img/dk_back_caminarDiagonalEsq2.png"));
                imatgesBack[CAMINAR_DIAGONAL_ESQUERRA][0] = ImageIO.read( new File("./img/dk_back_caminarDiagonalDreta1.png"));
                imatgesBack[CAMINAR_DIAGONAL_ESQUERRA][1] = ImageIO.read( new File("./img/dk_back_caminarDiagonalDreta2.png"));
                imatgesBack[COP_DRET][0] = ImageIO.read( new File("./img/dk_back_cDreta1.png"));
                imatgesBack[COP_DRET][1] = ImageIO.read( new File("./img/dk_back_cDreta2.png"));
                imatgesBack[COP_DRET][2] = ImageIO.read( new File("./img/dk_back_cDreta3.png"));
                imatgesBack[COP_REVES][0] = ImageIO.read( new File("./img/dk_back_cReves1.png"));
                imatgesBack[COP_REVES][1] = ImageIO.read( new File("./img/dk_back_cReves2.png"));
                imatgesBack[COP_REVES][2] = ImageIO.read( new File("./img/dk_back_cReves3.png"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        new Thread(this).start();
        animacioAct = 0;
        frameAct = 0;
    }

    public BufferedImage getFrontFrame()
    {
        return imatgesFront[animacioAct][frameAct];
    }

    public BufferedImage getBackFrame()
    {
        return imatgesBack[animacioAct][frameAct];
    }

    public void setAnimacioActual(int animacio)
    {
        animacioAct = animacio;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(animacioAct == COP_DRET | animacioAct == COP_REVES )
            {
                if(imatgesFront[animacioAct][frameAct+1]==null)
                {
                    frameAct = 0;
                    animacioAct = QUIET;
                }
                else
                {
                    frameAct++;
                }
            }
            else
            {
                if(imatgesFront[animacioAct][frameAct+1]==null) frameAct = 0;
                else                                            frameAct++;
            }
            
            try
            {
                if(animacioAct==COP_DRET || animacioAct==COP_REVES) Thread.sleep(33);
                else                                                Thread.sleep(400);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
