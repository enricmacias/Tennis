/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

/**
 *
 * @author Alumne
 */
public class Escenari extends JComponent
{
    private BufferedImage textura;

    public Escenari( String path )
    {
        try
        {
            textura = ImageIO.read( new File(path) );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paintComponent( Graphics g )
    {
        //System.out.println("pinto escena");
        g.drawImage(textura, 0, 0, this);
    }
}
