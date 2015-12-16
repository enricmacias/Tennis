/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

/**
 *
 * @author Dani i KiKe
 */
public class ThRebreDades extends Thread
{
    private Connexio conn;
    private int mode;
    private Pista pista;

    public ThRebreDades(Connexio _c, int _mode, Pista _pista)
    {
        conn = _c;
        mode = _mode;
        pista = _pista;
    }

    @Override
    public void run()
    {
        if(mode == Connexio.SERVER )
        {
            conn.waitForConnection(5555);
        }
        else
        {
            conn.connect("127.1.0.0", 5555);
        }
        conn.write(pista.conf.getNomUsuari()+"#"+Integer.toString(pista.getTorn()));
        String infoInici = conn.read();
        String[] info = infoInici.split("#");
        pista.setNomContrincant(info[0]);
        if(!pista.isServer())   pista.setTorn(info[1]);
        
        while(conn.isConnected())
        { 
            String str = conn.read();

            if(str.compareTo("exit") == 0)
            {
                conn.disconnect();
            }
            else
            {
                //System.out.println("Rebent...");
                //Tractament paquet rebut
                Paquet p = new Paquet();
                p.obrePaquet(str);
                pista.paquetRebut(p);
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
