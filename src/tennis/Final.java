package tennis;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Dani i KiKe
 */
public class Final extends Pantalla
{
    private JLabel titol;
    
    public Final()
    {
    }

    public void pintaResultats()
    {
        setLayout( new BorderLayout() );

        //Titol
        JPanel p = new JPanel();
        if(conf.getGuanyador()==1)  titol = new JLabel("WIN!");
        else                        titol = new JLabel("LOSE!");

        titol.setFont(fontTitol);
        p.add(titol);
        add(p,BorderLayout.NORTH);

        //Puntuacions
        JPanel pPuntuacions = new JPanel(new GridLayout(3,2));

        //Nom Jugador 1
        JLabel nomJugador1 = new JLabel(conf.getNomUsuari());
        nomJugador1.setFont(fontEstandard);
        JPanel pNomJugador1 = new JPanel();
        pNomJugador1.add(nomJugador1);
        pPuntuacions.add(pNomJugador1);

        //Nom Jugador 2
        JLabel nomJugador2 = new JLabel(conf.getNomContrincant());
        nomJugador2.setFont(fontEstandard);
        JPanel pNomJugador2 = new JPanel();
        pNomJugador2.add(nomJugador2);
        pPuntuacions.add(pNomJugador2);

        if(conf.getGuanyador()==1)
        {
            //Guanyar
            JLabel guanya = new JLabel("WIN");
            guanya.setFont(fontEstandard);
            JPanel pGuanya = new JPanel();
            pGuanya.add(guanya);
            pPuntuacions.add(pGuanya);

            //Perd
            JLabel perd = new JLabel("LOSE");
            perd.setFont(fontEstandard);
            JPanel pPerd = new JPanel();
            pPerd.add(perd);
            pPuntuacions.add(pPerd);
        }
        else
        {
            //Perd
            JLabel perd = new JLabel("LOSE");
            perd.setFont(fontEstandard);
            JPanel pPerd = new JPanel();
            pPerd.add(perd);
            pPuntuacions.add(pPerd);

            //Guanyar
            JLabel guanya = new JLabel("WIN");
            guanya.setFont(fontEstandard);
            JPanel pGuanya = new JPanel();
            pGuanya.add(guanya);
            pPuntuacions.add(pGuanya);
        }

        add(pPuntuacions,BorderLayout.CENTER);

        setOpaque(true);
    }

}
