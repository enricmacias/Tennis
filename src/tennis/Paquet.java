package tennis;

/**
 *
 * @author Dani i KiKe
 */
public class Paquet
{
    private String paquet;

    //Informació a enviar
    private int eJugadorPosX;
    private int eJugadorPosY;
    private int eDireccio[];
    private int eCopDreta;
    private int eCopReves;
    private double eVelX;
    private double eVelY;
    private double eVelZ;
    private int ePilotaPosX;
    private int ePilotaPosY;
    private int ePilotaPosZ;
    private int ePuntsJug1;
    private int ePuntsJug2;
    private int eJocsJug1;
    private int eJocsJug2;
    private int eTorn;

    //Informació rebuda
    private int rJugadorPosX;
    private int rJugadorPosY;
    private int rDireccio[];
    private int rCopDreta;
    private int rCopReves;
    private double rVelX;
    private double rVelY;
    private double rVelZ;
    private int rPilotaPosX;
    private int rPilotaPosY;
    private int rPilotaPosZ;
    private int rPuntsJug1;
    private int rPuntsJug2;
    private int rJocsJug1;
    private int rJocsJug2;
    private int rTorn;

    private int tipus;

    public Paquet()
    {
        this.tipus = tipus;

        eDireccio = new int[4];
        rDireccio = new int[4];
    }

    public Paquet(int tipus)
    {
        this.tipus = tipus;

        eDireccio = new int[4];
        rDireccio = new int[4];
    }

    public void afegeixPosJugador(int jugPosX, int jugPosY)
    {
        eJugadorPosX = jugPosX;
        eJugadorPosY = jugPosY;
    }

    public void afegeixDireccio(boolean[] dir)
    {
        for(int i=0;i<4;i++)
        {
            if(dir[i])  eDireccio[i] = 1;
            else        eDireccio[i] = 0;
        }
    }

    public void afegeixCops(boolean copDreta, boolean copReves)
    {
        if(copDreta)    eCopDreta = 1;
        else            eCopDreta = 0;

        if(copReves)    eCopReves = 1;
        else            eCopReves = 0;
    }

    public void afegeixPosPilota(int posX, int posY, int posZ)
    {
        ePilotaPosX = posX;
        ePilotaPosY = posY;
        ePilotaPosZ = posZ;
    }

    public void afegeixVelocitat(double velX, double velY, double velZ)
    {
        eVelX = velX;
        eVelY = velY;
        eVelZ = velZ;
    }

    public void afegeixPunts(int punts1, int punts2)
    {
        ePuntsJug1 = punts1;
        ePuntsJug2 = punts2;
    }

    public void afegeixJocs(int jocs1, int jocs2)
    {
        eJocsJug1 = jocs1;
        eJocsJug2 = jocs2;
    }

    public void afegeixTorn(int torn)
    {
        eTorn = torn;
    }

    public String tancaPaquet()
    {
        switch(tipus)
        {
            case 1:
                paquet = "1#"+eJugadorPosX+"#"+eJugadorPosY+"#"+eDireccio[0]+"#"+eDireccio[1]+"#"+eDireccio[2]+"#"+eDireccio[3]+"#"+eCopDreta+"#"+eCopReves;
                break;
            case 2:
                paquet = "2#"+ePilotaPosX+"#"+ePilotaPosY+"#"+ePilotaPosZ+"#"+eVelX+"#"+eVelY+"#"+eVelZ;
                break;
            case 3:
                paquet = "3#"+ePuntsJug1+"#"+ePuntsJug2+"#"+eJocsJug1+"#"+eJocsJug2+"#"+eTorn;
                break;
        }
        return paquet;
    }

    public void obrePaquet(String _paquet)
    {
        String[] info = _paquet.split("#");
        tipus = Math.abs(Integer.parseInt(info[0]));

        switch(tipus)
        {
            case 1:
                rJugadorPosX = Math.abs(Integer.parseInt(info[1]) - Pista.AMPLE_CAMP);
                rJugadorPosY = Math.abs(Integer.parseInt(info[2]) - Pista.ALT_CAMP);
                rDireccio[0] = Integer.parseInt(info[3]);
                rDireccio[1] = Integer.parseInt(info[4]);
                rDireccio[2] = Integer.parseInt(info[5]);
                rDireccio[3] = Integer.parseInt(info[6]);
                rCopDreta = Integer.parseInt(info[7]);
                rCopReves = Integer.parseInt(info[8]);
                break;

            case 2:
                rPilotaPosX = Math.abs(Integer.parseInt(info[1]) - Pista.AMPLE_CAMP);
                rPilotaPosY = Math.abs(Integer.parseInt(info[2]) - Pista.ALT_CAMP)+100;
                rPilotaPosZ = Integer.parseInt(info[3]);
                rVelX = -Double.parseDouble(info[4]);
                rVelY = -Double.parseDouble(info[5]);
                rVelZ = Double.parseDouble(info[6]);
                break;
            case 3:
                rPuntsJug2 = Integer.parseInt(info[1]);
                rPuntsJug1 = Integer.parseInt(info[2]);
                rJocsJug2 = Integer.parseInt(info[3]);
                rJocsJug1 = Integer.parseInt(info[4]);
                rTorn = Integer.parseInt(info[5]);
                break;
        }
    }

    public int getJugadorPosX()
    {
        return rJugadorPosX;
    }

    public int getJugadorPosY()
    {
        return rJugadorPosY;
    }

    public boolean[] getDireccio()
    {
        boolean dir[];
        dir = new boolean[4];

        for(int i=0;i<4;i++)
        {
            if(rDireccio[i] == 1)   dir[i] = true;
            else                    dir[i] = false;
        }
        
        return dir;
    }

    public boolean getCopDreta()
    {
        if(rCopDreta == 1)      return true;
        else                    return false;
    }

    public boolean getCopReves()
    {
        if(rCopReves == 1)      return true;
        else                    return false;
    }

    public double getVelX()
    {
        return rVelX;
    }

    public double getVelY()
    {
        return rVelY;
    }

    public double getVelZ()
    {
        return rVelZ;
    }

    public int getPilotaPosX()
    {
        return rPilotaPosX;
    }

    public int getPilotaPosY()
    {
        return rPilotaPosY;
    }

    public int getPilotaPosZ()
    {
        return rPilotaPosZ;
    }

    public int getPuntsJug1()
    {
        return rPuntsJug1;
    }

    public int getPuntsJug2()
    {
        return rPuntsJug2;
    }

    public int getJocsJug1()
    {
        return rJocsJug1;
    }

    public int getJocsJug2()
    {
        return rJocsJug2;
    }

    public int getTipus()
    {
        return tipus;
    }

    public boolean getTorn()
    {
        if(rTorn == 1)  return false;
        else            return true;
    }
}
