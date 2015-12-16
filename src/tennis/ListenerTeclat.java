/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Dani i KiKe
 */
public class ListenerTeclat implements KeyListener
{
    private Jugador j;
    private Pilota pilota;

    public ListenerTeclat(Jugador j, Pilota pilota)
    {
        this.j = j;
        this.pilota = pilota;
    }

    public  void keyPressed(KeyEvent e)
    {
        boolean direccio[] = j.getDireccio();

        if(e.getKeyCode() == KeyEvent.VK_LEFT )     direccio[j.LEFT] =  true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT )    direccio[j.RIGHT] =  true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN )     direccio[j.DOWN] =  true;
        if(e.getKeyCode() == KeyEvent.VK_UP )       direccio[j.UP] =  true;

        if( e.getKeyCode() == KeyEvent.VK_A )
        {
            j.setCopDreta(true);
            pilota.copDreta(j.getPosX(), j.getPosY());
        }

        if( e.getKeyCode() == KeyEvent.VK_S )
        {
            j.setCopReves(true);
            pilota.copReves(j.getPosX(),j.getPosY());
        }

        if( e.getKeyCode() == KeyEvent.VK_SPACE )
        {
            if(j.getTorn()) pilota.saque(j.getPosX(), j.getPosY());
        }

        j.setDireccio(direccio);
    }
    
    public void keyReleased(KeyEvent e)
    {
        boolean direccio[] = j.getDireccio();
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT )     direccio[j.LEFT] =  false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT )    direccio[j.RIGHT] =  false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN )     direccio[j.DOWN] =  false;
        if(e.getKeyCode() == KeyEvent.VK_UP )       direccio[j.UP] =  false;

        if( e.getKeyCode() == KeyEvent.VK_A )       j.setCopDreta(false);
        if( e.getKeyCode() == KeyEvent.VK_S )       j.setCopReves(false);

        j.setDireccio(direccio);
    }

    public void keyTyped(KeyEvent e){}
}
