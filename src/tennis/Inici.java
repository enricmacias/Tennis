/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author KiKe i Dani
 */
public class Inici extends Pantalla
{
    private JLabel titol;
    private JButton botoSeguir;

    public Inici(Main _app)
    {
        app = _app;
        setLayout(new BorderLayout());

        JPanel pImatge = new JPanel();
        ImageIcon imatge = new ImageIcon("portada.png");
        JLabel etiquetaImatge = new JLabel ("",imatge, JLabel.CENTER);
        pImatge.add(etiquetaImatge);
        add(pImatge,BorderLayout.CENTER);

        //Boto
        botoSeguir = new JButton("Jugar");
        botoSeguir.setFont(fontBoto);
        botoSeguir.addMouseListener(new ListenerBoto(app, 0));
        add(botoSeguir,BorderLayout.SOUTH);

        setOpaque(true);
    }

    @Override
    public void paintComponent( Graphics g )
    {
        //titol.update(g);
        //botoSeguir.update(g);
    }
}
