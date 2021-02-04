package com.technic.ludogame1;

public class Percorso {
    private int pedinaOcc;//numero da 0 a 16
    private boolean occupata;

    public Percorso(){}
    public Percorso(boolean f)
    {
        occupata=f;
        pedinaOcc=17;//ovvero nessuna pedina la occupa
    }
    public void setOcuupata(boolean f)
    { occupata=f; }
    public boolean getOccupata()
    {return occupata;}
    public void setPedinaOcc(int f)
    { pedinaOcc=f; }
    public int getPedinaOcc()
    {return pedinaOcc;}
}
