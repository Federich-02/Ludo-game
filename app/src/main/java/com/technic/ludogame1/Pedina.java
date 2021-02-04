package com.technic.ludogame1;

public class Pedina {
    private int x;
    private int y;
    private int casella;
    private int colore;
    private int giro;
    private int jolly;

    public Pedina(int i) {
        switch(i)
        {
            case 0: setX(434);setY(1363);colore=1;break;
            case 1: setX(434);setY(1363);colore=1;break;
            case 2: setX(434);setY(1363);colore=1;break;
            case 3: setX(434);setY(1363);colore=1;break;
            case 4: setX(74);setY(865);colore=2;break;
            case 5: setX(74);setY(865);colore=2;break;
            case 6: setX(74);setY(865);colore=2;break;
            case 7: setX(74);setY(865);colore=2;break;
            case 8: setX(576);setY(507);colore=3;break;
            case 9: setX(576);setY(507);colore=3;break;
            case 10: setX(576);setY(507);colore=3;break;
            case 11: setX(576);setY(507);colore=3;break;
            case 12: setX(933);setY(1009);colore=4;break;
            case 13: setX(933);setY(1009);colore=4;break;
            case 14: setX(933);setY(1009);colore=4;break;
            case 15: setX(933);setY(1009);colore=4;break;
        }
        giro=0;
    }

    //coordinate --------------
    public void setX(int z) {
        x = z;
    }

    public int getX() {
        return x;
    }

    public void setY(int z) {
        y = z;
    }

    public int getY() {
        return y;
    }
    //giro---------
    public int getGiro() {
        return giro;
    }
    //colore-------------------
    public void setColore(int z) {
        colore = z;
    }

    public int getColore() {
        return colore;
    }

    //casella------------------
    public void setCasella(int z) {
        casella = z;
        Spostamento();
    }
    public int getCasella()
    { return casella; }

    public void SommaCasella(int g, int c) {
        int temporanea=0,precedente=0,entry=0;
        precedente=casella;
        jolly=casella;
        temporanea = casella+g;
        switch(c)
        {
            case 1:
                if(temporanea<=57 && precedente<=50)
                {
                    casella+=g+1;
                }else
                if(temporanea<=57)
                {
                    casella+=g;
                    setCasella(casella);
                }break;
            case 2:if(temporanea>=52)//se passa dal blu
                    {
                        casella=casella-52+g;
                    }else
                    {
                        casella+=g;
                    }
                    if(giro==1)
                    {
                    if(casella>11 && casella<=63)//se non è ancora entrata nei rossi
                    {
                        casella=temporanea-12+58;
                    }else
                    {
                        if(temporanea<=63)
                            casella=temporanea;
                        else
                            casella=precedente;
                    }
                    }

                    setCasella(casella);break;
            case 3:if(temporanea>=52)//se passa dal blu
            {
                casella=casella-52+g;
            }else
            {
                casella+=g;

            }
                if(giro==1)
                {
                    if(casella>24 && casella<=69)//se non è ancora entrata nei rossi
                    {
                        casella=temporanea-25+64;
                    }else
                    {
                        if(temporanea<=69)
                            casella=temporanea;
                        else
                            casella=precedente;
                    }
                }
                setCasella(casella);break;
            case 4:if(temporanea>=52)//se passa dal blu
            {
                casella=casella-52+g;
            }else
            {
                casella+=g;
            }
                if(giro==1)
                {
                    if(casella>37 && casella<=75)//se non è ancora entrata nei rossi
                    {
                        casella=temporanea-38+70;
                    }else
                    {
                        if(temporanea<=75)
                            casella=temporanea;
                        else
                            casella=precedente;
                    }
                }
                setCasella(casella);break;
        }

        Spostamento();
    }

    public void Spostamento() {

        if(casella>75)
            casella=jolly;
        switch (casella) {
            case 0: setX(434);setY(1363);break;
            case 1: setX(434);setY(1293);break;
            case 2: setX(434);setY(1223);giro=1;break;
            case 3: setX(434);setY(1150);giro=1;break;
            case 4: setX(434);setY(1080);giro=1;break;
            case 5: setX(360);setY(1009);giro=1;break;
            case 6: setX(290);setY(1009);giro=1;break;
            case 7: setX(217);setY(1009);giro=1;break;
            case 8: setX(147);setY(1009);giro=1;break;
            case 9: setX(74);setY(1009);giro=1;break;
            case 10: setX(4);setY(1009);break;
            case 11: setX(4);setY(936);break;
            case 12: setX(4);setY(865);break;
            case 13: setX(74);setY(865);break;
            case 14: setX(147);setY(865);break;
            case 15: setX(217);setY(865);break;
            case 16: setX(290);setY(865);break;
            case 17: setX(360);setY(865);break;
            case 18: setX(434);setY(793);break;
            case 19: setX(434);setY(723);break;
            case 20: setX(434);setY(650);break;
            case 21: setX(434);setY(580);break;
            case 22: setX(434);setY(507);break;
            case 23: setX(434);setY(430);break;
            case 24: setX(505);setY(437);break;
            case 25: setX(576);setY(437);break;
            case 26: setX(576);setY(507);break;
            case 27: setX(576);setY(580);break;
            case 28: setX(576);setY(650);break;
            case 29: setX(576);setY(723);break;
            case 30: setX(576);setY(793);break;
            case 31: setX(647);setY(865);break;
            case 32: setX(720);setY(865);break;
            case 33: setX(790);setY(865);break;
            case 34: setX(860);setY(865);break;
            case 35: setX(933);setY(865);break;
            case 36: setX(1003);setY(865);break;
            case 37: setX(1003);setY(936);break;
            case 38: setX(1003);setY(1009);break;
            case 39: setX(933);setY(1009);break;
            case 40: setX(860);setY(1009);break;
            case 41: setX(790);setY(1009);break;
            case 42: setX(720);setY(1009);break;
            case 43: setX(647);setY(1009);break;
            case 44: setX(576);setY(1080);break;
            case 45: setX(576);setY(1150);break;
            case 46: setX(576);setY(1223);break;
            case 47: setX(576);setY(1293);break;
            case 48: setX(576);setY(1363);break;
            case 49: setX(576);setY(1436);break;
            case 50: setX(505);setY(1436);break;
            case 51: setX(434);setY(1436);break;
            case 52: setX(505);setY(1363);break;
            case 53: setX(505);setY(1293);break;
            case 54: setX(505);setY(1223);break;
            case 55: setX(505);setY(1150);break;
            case 56: setX(505);setY(1080);break;
            case 57: setX(505);setY(1009);break;
            case 58: setX(74);setY(936);break;
            case 59: setX(147);setY(936);break;
            case 60: setX(217);setY(936);break;
            case 61: setX(290);setY(936);break;
            case 62: setX(360);setY(936);break;
            case 63: setX(434);setY(936);break;
            case 64: setX(505);setY(507);break;
            case 65: setX(505);setY(580);break;
            case 66: setX(505);setY(650);break;
            case 67: setX(505);setY(723);break;
            case 68: setX(505);setY(793);break;
            case 69: setX(505);setY(865);break;
            case 70: setX(933);setY(936);break;
            case 71: setX(860);setY(936);break;
            case 72: setX(790);setY(936);break;
            case 73: setX(720);setY(936);break;
            case 74: setX(647);setY(936);break;
            case 75: setX(576);setY(936);break;
            default:setX(399);setY(399);break;
        }
    }
}
