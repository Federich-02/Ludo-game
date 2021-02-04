package com.technic.ludogame1;

public class Giocatore {
    private int punteggio;
    private int pedineInterne;
    private int colore; //rosso=2,verde=3,giallo=4,blu=1
    private boolean giroDado,attivapedina;
    int maxgiro;
    public Giocatore (int c)
    {
        punteggio=0;
        pedineInterne=4;
        colore=c+1;
        giroDado=false;
        maxgiro=0;
        attivapedina=false;//scegliere da posizionare
    }
    //può girare il dado
    public boolean GetgiroDado()
    {return giroDado;}
    public void SetgiroDado(boolean g)
    {  giroDado=g; }
    //numero dei giri
    public int GetmaxGiro()
    {return maxgiro;}
    public void SetmaxGiro()
    {  maxgiro++; }
    public void SetmaxGiro1(int c)
    {  maxgiro=c; }
    //colore del giocatore
    public int GetColore()
    {return colore;}
    public void SetColore(int g)
    {  colore=g; }
    //attivapedina
    public boolean GetAttPed()
    {return attivapedina;}
    public void SetAttPed(boolean g)
    { attivapedina=g; }
    //pedina interna
    public int GetPedinaInterna()
    {return pedineInterne;}
    public void SetCPedinaInterna(int g)
    {  pedineInterne=g; }
    public void IncrementaCPedinaInterna()
    {  pedineInterne++; }
}
