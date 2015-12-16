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
public class Joc extends Pantalla
{
    private JButton botoSeguir;
    private Pista pista;

    public Joc(Main _app)
    {
        app = _app;

        setLayout(new BorderLayout());

        //Pista de tennis
        pista = new Pista(app);
        add(pista,BorderLayout.CENTER);
        setOpaque(true);
    }

    public void initJoc()
    {
        pista.afegeixTeclat();
        pista.estableixConnexio();
        pista.preparaPista();
        pista.preparaJugadors();
    }

    @Override
    public void paintComponent( Graphics g )
    {
        //botoSeguir.update(g);
        //pista.update(g);
    }
}
