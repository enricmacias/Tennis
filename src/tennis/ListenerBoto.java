/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.event.*;

/**
 *
 * @author Dani i KiKe
 */
public class ListenerBoto implements MouseListener
{
    private Main app;
    private int numPantalla;
    private Configuracio conf;

    public ListenerBoto(Main _app, int _numPantalla)
    {
        app = _app;
        numPantalla = _numPantalla;
    }

    //Opcions
    public ListenerBoto(Main _app, Configuracio _conf)
    {
        app = _app;
        conf = _conf;
        numPantalla = 1;
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e)
    {
        switch(numPantalla)
        {
            case 0: app.setPantalla(numPantalla+1); break;
            case 1:
                conf.guardaConfiguracio();
                app.setPantalla(2);
                break;
            case 2: app.setPantalla(numPantalla+1); break;
        }
    }
}
