/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Alumno
 */
public class Opcions extends Pantalla
{
    private JLabel titol;
    private JButton botoSeguir;

    public Opcions(Main _app)
    {
        app = _app;

        setLayout(new BorderLayout());

        //Titol
        JPanel p = new JPanel();
        titol = new JLabel("OPCIONS");
        titol.setFont(fontTitol);
        p.add(titol);
        add(p,BorderLayout.NORTH);

        //Opcions
        conf = new Configuracio(app,fontEstandard);
        add(conf,BorderLayout.CENTER);

        //Boto
        botoSeguir = new JButton("Seguir");
        botoSeguir.setFont(fontBoto);
        botoSeguir.addMouseListener(new ListenerBoto(app,conf));
        add(botoSeguir,BorderLayout.SOUTH);

        setOpaque(true);
    }

    @Override
    public void paintComponent( Graphics g )
    {
        //titol.update(g);
        //botoSeguir.update(g);
        //pOpcions.update(g);
    }
}
