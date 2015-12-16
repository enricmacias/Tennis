package tennis;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Dani i KiKe
 */
public class Main extends JFrame
{
    private static Main app;

    private static Pantalla pantalla;
    private static Inici pInici;
    private static Opcions pOpcions;
    private static Joc pJoc;
    private static Final pFinal;

    public Main()
    {
        super("Tennis LA SALLE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        pInici = new Inici(this);
        pOpcions = new Opcions(this);
        pJoc = new Joc(this);
        pFinal = new Final();

        setPantalla(0);
        
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        app = new Main();
    }

    public void setPantalla(int numPantalla)
    {
        switch(numPantalla)
        {
            case 0: pantalla = pInici;      break;
            case 1: pantalla = pOpcions;    break;
            case 2:
                pJoc.initJoc();
                pantalla = pJoc;
                break;
            case 3:
                pFinal.pintaResultats();
                pantalla = pFinal;
                break;
            default: System.out.println("Numero de pantalla no existent"); break;
        }
        setContentPane(pantalla);
        pack();
        setBounds( 350, 80, 550, 620 );
    }
}
