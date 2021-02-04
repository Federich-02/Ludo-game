package com.technic.ludogame1;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.Intent;
import android.os.Handler;
import android.widget.*;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.content.Context;

public class Dado extends AppCompatActivity {

    private TextView txtV,txtR,txtG,txtB;
    private ImageView dice,pedR1,pedR2,pedR3,pedR4,pedV1,pedV2,pedV3,pedV4,pedG1,pedG2,pedG3,pedG4,pedB1,pedB2,pedB3,pedB4;
    private ImageView pedRIn1,pedRIn2,pedRIn3,pedRIn4,pedVIn1,pedVIn2,pedVIn3,pedVIn4,pedGIn1,pedGIn2,pedGIn3,pedGIn4;
    private ImageView pedBIn1,pedBIn2,pedBIn3,pedBIn4;
    private Random rand = new Random();
    private Button ready;
    int f=0;
    private int partenza=0;
    private static int turno=0,turni=0,dado=0,giallo=0,verde=0,blu=0,rosso=0;
    private static boolean flag,flagPedina,avanzaPedina;
    //oggetti----------------------------------
    Giocatore vett[];
    Pedina pedina[];
    Percorso percorso[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioco);
        ready= (Button) findViewById(R.id.btnready);
        Intent intent = getIntent();
        //vettore giocatori---------------------
        vett=new Giocatore[4];
        for(int i=0;i<4;i++)
        { vett[i]=new Giocatore(i); }
        //vettore pedine------------------------
        pedina=new Pedina[16];
        for(int i=0;i<16;i++)
        { pedina[i]=new Pedina(i); }
        //vettore percorso----------------------
        percorso=new Percorso[76];
        for(int g=0;g<76;g++)
        { percorso[g]=new Percorso(true); }
        //etichette giocatori-------------------
        InizializzaEtichette();
        //figure--------------------------------
        InizializzaImmagini();
        for (int i=0;i<16;i++)
        {
            pedina[i].Spostamento();
        }
        Invisibili();
        //chi inizia per primo-------------------
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(partenza==0) {
                  dice.setVisibility(View.VISIBLE);
                   partenza++;
                   flag=true;
                   flagPedina=false;
                   avanzaPedina=false;
                   //PosizionaPedine();//posiziona alle coordinate giuste le pedine del gioco
               }else
              switch(turno)
                   {
                       case 0:showToastBlu(0); flag=true;vett[0].SetgiroDado(true);vett[1].SetgiroDado(false);vett[2].SetgiroDado(false);vett[3].SetgiroDado(false);
                            break;
                       case 1:showToastBlu(1); flag=true;vett[1].SetgiroDado(true);vett[0].SetgiroDado(false);
                           vett[2].SetgiroDado(false);vett[3].SetgiroDado(false);
                           break;
                       case 2:showToastBlu(2);flag=true;vett[2].SetgiroDado(true);vett[0].SetgiroDado(false);
                           vett[1].SetgiroDado(false);vett[3].SetgiroDado(false);
                           break;
                       case 3:showToastBlu(3);flag=true;vett[3].SetgiroDado(true);vett[0].SetgiroDado(false);
                           vett[2].SetgiroDado(false);vett[1].SetgiroDado(false);
                           break;
                       default:;
                   }
            }
        });
        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(flag)
               {
                   if(vett[turno].GetgiroDado() && vett[turno].GetmaxGiro()<=2 )
                   {
                       rollDice();
                        flagPedina=true;
                       vett[turno].SetmaxGiro();            //maxgiro=1
                       vett[turno].SetgiroDado(false);   //blocco giro
                       if(dado==6)
                       {
                                   if(vett[turno].GetmaxGiro()<=2)
                                   {
                                       flag=true;
                                       flagPedina=true;
                                       vett[turno].SetgiroDado(true);
                                       vett[turno].SetAttPed(true);//attiva la possibilità di cliccare una delle quattro
                                       showToast(1);
                                   }
                                   else
                                   {
                                       showToast(0); //ha già tirato tre volte
                                       flag=false;
                                       flagPedina=false;
                                       vett[turno].SetmaxGiro1(0);
                                            turno++;
                                            turni++;
                                            if(turno==4)
                                            turno=0;
                                   }
                       }
                       else
                       {
                           //showTurno();
                           flag=false;
                           if(vett[turno].GetPedinaInterna()==4)
                               avanzaPedina=true;
                           else
                               avanzaPedina=false;
                           if(avanzaPedina) {
                               vett[turno].SetmaxGiro1(0);
                               turno++;
                               turni++;
                               if (turno == 4)
                                   turno = 0;
                           }
                       }
                   }
               }else
                   showToast(0);
            }
        });
    }
    //pedine-----------------------------------
    public void AttivaPedina(int pedina)
    {
        int diff=0;
        int p=pedina;
            switch (pedina) {
                //blu
                case 1:
                    if (vett[turno].GetColore() == 1 && vett[turno].GetgiroDado() && vett[turno].GetAttPed()) {
                        pedB1.setVisibility(View.INVISIBLE);
                        vett[turno].SetAttPed(false);
                        PedinaVisibileIn(1);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);//do numero pedina da rendere visibile
                    }
                    break;
                case 2:
                    if (vett[turno].GetColore() == 1 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    {pedB2.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);
                        PedinaVisibileIn(2);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                case 3:
                    if (vett[turno].GetColore() == 1 && vett[turno].GetgiroDado() && vett[turno].GetAttPed()) {
                        pedB3.setVisibility(View.INVISIBLE);
                        vett[turno].SetAttPed(false);
                        PedinaVisibileIn(3);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                case 4:
                    if (vett[turno].GetColore() == 1 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedB4.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);
                        PedinaVisibileIn(4);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                //rosso
                case 5:
                    if (vett[turno].GetColore() == 2 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedR1.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);
                        PedinaVisibileIn(1);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                case 6:
                    if (vett[turno].GetColore() == 2 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    {   pedR2.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);
                        PedinaVisibileIn(2);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                case 7:
                    if (vett[turno].GetColore() == 2 && vett[turno].GetgiroDado() && vett[turno].GetAttPed()) {
                        pedR3.setVisibility(View.INVISIBLE);
                        vett[turno].SetAttPed(false);
                        PedinaVisibileIn(3);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                case 8:
                    if (vett[turno].GetColore() == 2 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedR4.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);
                        PedinaVisibileIn(4);}
                    diff=vett[turno].GetPedinaInterna();
                    vett[turno].SetCPedinaInterna(diff-1);

                    break;
                //verde
                case 9:
                    if (vett[turno].GetColore() == 3 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    {  pedV1.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(1);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                case 10:
                    if (vett[turno].GetColore() == 3 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedV2.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(2);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                case 11:
                    if (vett[turno].GetColore() == 3 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    {  pedV3.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(3);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                case 12:
                    if (vett[turno].GetColore() == 3 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedV4.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(4);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                //giallo
                case 13:
                    if (vett[turno].GetColore() == 4 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedG1.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(1);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                case 14:
                    if (vett[turno].GetColore() == 4 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    {  pedG2.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(2);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                case 15:
                    if (vett[turno].GetColore() == 4 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    { pedG3.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(3);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }

                    break;
                case 16:
                    if (vett[turno].GetColore() == 4 && vett[turno].GetgiroDado() && vett[turno].GetAttPed())
                    {  pedG4.setVisibility(View.INVISIBLE);
                    vett[turno].SetAttPed(false);PedinaVisibileIn(4);
                        diff=vett[turno].GetPedinaInterna();
                        vett[turno].SetCPedinaInterna(diff-1);
                    }
                    break;
                default:showTurno();flagPedina=true;break;
            }
    }
    public void PedinaVisibileIn(int p)
    {
        int ped=p;
        switch(vett[turno].GetColore())
        {
            case 1:switch (ped)
            {
                case 1:pedina[0].setCasella(0);pedBIn1.setVisibility(View.VISIBLE);
                percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(0);break;
                case 2:pedina[1].setCasella(0);pedBIn2.setVisibility(View.VISIBLE);
                    percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(1);break;
                case 3:pedina[2].setCasella(0);pedBIn3.setVisibility(View.VISIBLE);
                    percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(2);break;
                case 4:pedina[3].setCasella(0);pedBIn4.setVisibility(View.VISIBLE);
                    percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(3);break;
            }break;
            case 2:switch (ped)
            {
                case 1:pedina[4].setCasella(13);pedRIn1.setVisibility(View.VISIBLE);
                    percorso[13].setOcuupata(false);percorso[13].setPedinaOcc(4);break;
                case 2:pedina[5].setCasella(13);pedRIn2.setVisibility(View.VISIBLE);
                    percorso[13].setOcuupata(false);percorso[13].setPedinaOcc(5);break;
                case 3:pedina[6].setCasella(13);pedRIn3.setVisibility(View.VISIBLE);
                    percorso[13].setOcuupata(false);percorso[13].setPedinaOcc(6);break;
                case 4:pedina[7].setCasella(13);pedRIn4.setVisibility(View.VISIBLE);
                    percorso[13].setOcuupata(false);percorso[13].setPedinaOcc(7);break;
            }break;
            case 3:switch (ped)
            {
                case 1:pedina[8].setCasella(26);pedVIn1.setVisibility(View.VISIBLE);
                    percorso[26].setOcuupata(false);percorso[26].setPedinaOcc(8);break;
                case 2:pedina[9].setCasella(26);pedVIn2.setVisibility(View.VISIBLE);
                    percorso[26].setOcuupata(false);percorso[26].setPedinaOcc(9);break;
                case 3:pedina[10].setCasella(26);pedVIn3.setVisibility(View.VISIBLE);
                    percorso[26].setOcuupata(false);percorso[26].setPedinaOcc(10);break;
                case 4:pedina[11].setCasella(26);pedVIn4.setVisibility(View.VISIBLE);
                    percorso[26].setOcuupata(false);percorso[26].setPedinaOcc(11);break;
            }break;
            case 4:switch (ped)
            {
                case 1:pedina[12].setCasella(39);pedGIn1.setVisibility(View.VISIBLE);
                    percorso[39].setOcuupata(false);percorso[39].setPedinaOcc(12);break;
                case 2:pedina[13].setCasella(39);pedGIn2.setVisibility(View.VISIBLE);
                    percorso[39].setOcuupata(false);percorso[39].setPedinaOcc(13);break;
                case 3:pedina[14].setCasella(39);pedGIn3.setVisibility(View.VISIBLE);
                    percorso[39].setOcuupata(false);percorso[39].setPedinaOcc(14);break;
                case 4:pedina[15].setCasella(39);pedGIn4.setVisibility(View.VISIBLE);
                    percorso[39].setOcuupata(false);percorso[39].setPedinaOcc(15);break;
            }break;
        }
        flagPedina=false;
    }
    //toast------------------------------------
    private void showToast(int c)
    {
        int h=c;
        Context context = getApplicationContext();
        CharSequence text;
        if(h==0)
            text = "Non puoi girare il dado!";//+dado+" "+vett[turno].GetmaxGiro()+" "+turno+" "+f;
        else
            //if(h==1)
                text = "hai fatto sei !";//+dado+" "+vett[turno].GetmaxGiro()+" "+turno;
           // else
             //   text = "uscito il ciclo"+f;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private void showToast1()
    {
        Context context = getApplicationContext();
        CharSequence text = "pedina si deve muovere"+turno;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 300, 100);
        toast.show();
    }
    private void showAtttivaPedina()
    {
        /*Context context1 = getApplicationContext();
        CharSequence text1 = " pedina non corretta,scegli quella del tuo colore";
        int duration1 = Toast.LENGTH_SHORT;
        Toast toast1 = Toast.makeText(context1, text1, duration1);
        toast1.setGravity(Gravity.CENTER, 100, 100);
        toast1.show();*/
    }
    public void showTurno()
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_avviso, (ViewGroup) findViewById(R.id.toast_root2));
        TextView toastText = layout.findViewById(R.id.toast_text2);
        ImageView toastImage = layout.findViewById(R.id.toast_image2);
        toastImage.setImageResource(R.drawable.ic_alert);
        Toast toast1 = new Toast(getApplicationContext());
        toast1.setGravity(Gravity.CENTER, 20, 20);
        toast1.setDuration(Toast.LENGTH_SHORT);
        toast1.setView(layout);
        toast1.show();
    }
    private void showToastBlu(int i) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layuot, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        switch(i)
        {
            case 0: toastText.setText("Tocca al blu"); break;
            case 1: toastText.setText("Tocca al rosso");break;
            case 2: toastText.setText("Tocca al verde");break;
            case 3: toastText.setText("Tocca al giallo");break;
        }
        toastImage.setImageResource(R.drawable.ic_toasticon);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 20, 20);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    //giro del dado----------------------------
    private void rollDice()
    {
        int num;
        num=rand.nextInt(6)+1;
        //num=6;
        dado=num;
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.rotate);
        dice.startAnimation(anim);
            switch(num)
            {
                case 1:dice.setImageResource(R.drawable.uno); break;
                case 2:dice.setImageResource(R.drawable.due); break;
                case 3:dice.setImageResource(R.drawable.tre); break;
                case 4:dice.setImageResource(R.drawable.quattro); break;
                case 5:dice.setImageResource(R.drawable.cinque); break;
                case 6:dice.setImageResource(R.drawable.sei); break;
            }
    }
    //etichette ed immagini -------------------
    public void InizializzaEtichette(){
        txtB=findViewById(R.id.txtblu);
        txtG=findViewById(R.id.txtgialla);
        txtR=findViewById(R.id.txtrossa);
        txtV=findViewById(R.id.txtverde);
    }
    public void InizializzaImmagini()
    {
        //pedina da scegliere
        pedR1=findViewById(R.id.pedR1);
        pedR2=findViewById(R.id.pedR2);
        pedR3=findViewById(R.id.pedR3);
        pedR4=findViewById(R.id.pedR4);
        pedV1=findViewById(R.id.pedV1);
        pedV2=findViewById(R.id.pedV2);
        pedV3=findViewById(R.id.pedV3);
        pedV4=findViewById(R.id.pedV4);
        pedG1=findViewById(R.id.pedG1);
        pedG2=findViewById(R.id.pedG2);
        pedG3=findViewById(R.id.pedG3);
        pedG4=findViewById(R.id.pedG4);
        pedB1=findViewById(R.id.pedB1);
        pedB2=findViewById(R.id.pedB2);
        pedB3=findViewById(R.id.pedB3);
        pedB4=findViewById(R.id.pedB4);
        //prime pedine
        pedRIn1=findViewById(R.id.pedRIn);
        pedRIn2=findViewById(R.id.pedRIn2);
        pedRIn3=findViewById(R.id.pedRIn3);
        pedRIn4=findViewById(R.id.pedRIn4);
        pedVIn1=findViewById(R.id.pedVIn);
        pedVIn2=findViewById(R.id.pedVIn2);
        pedVIn3=findViewById(R.id.pedVIn3);
        pedVIn4=findViewById(R.id.pedVIn4);
        pedGIn1=findViewById(R.id.pedGIn);
        pedGIn2=findViewById(R.id.pedGIn2);
        pedGIn3=findViewById(R.id.pedGIn3);
        pedGIn4=findViewById(R.id.pedGIn4);
        pedBIn1=findViewById(R.id.pedBIn);
        pedBIn2=findViewById(R.id.pedBIn2);
        pedBIn3=findViewById(R.id.pedBIn3);
        pedBIn4=findViewById(R.id.pedBIn4);
        PosizionaPedine();
        //dado
        dice= findViewById(R.id.dado);
        dice.setVisibility(View.INVISIBLE);
        //pedine da scegliere con click
        pedR2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(6);
            }
        });
        pedR3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(7);
            }
        });
        pedR4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(8);
            }
        });
        pedR1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(5);
            }
        });
        pedV2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(10);
            }
        });
        pedV1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(9);
            }
        });
        pedV3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(11);
            }
        });
        pedV4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(12);
            }
        });
        pedG1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(13);
            }
        });
        pedG2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(14);
            }
        });
        pedG3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(15);
            }
        });
        pedG4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(16);
            }
        });
        pedB1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(1);
            }
        });
        pedB2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(2);
            }
        });
        pedB3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(3);
            }
        });
        pedB4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AttivaPedina(4);
            }
        });

        //quando pedine vengono cliccate
        pedBIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vett[turno].GetColore()==1 && flagPedina)
                { percorso[pedina[0].getCasella()].setOcuupata(true);
                    percorso[pedina[0].getCasella()].setPedinaOcc(17);
                    pedina[0].SommaCasella(dado,vett[turno].GetColore());
                pedBIn1.setX(pedina[0].getX());
                pedBIn1.setY(pedina[0].getY());
                    EliminaPedina(0);
                    if(pedina[0].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[0].getCasella()].setOcuupata(false);
                percorso[pedina[0].getCasella()].setPedinaOcc(0);}
                flagPedina=false;
                //showToast1();
                if(!avanzaPedina && dado!=6)
                {
                    vett[turno].SetmaxGiro1(0);
                    turno++;
                    turni++;
                    if(turno==4)
                        turno=0;
                }
                }
                else
                showTurno();
            }
        });
        pedBIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vett[turno].GetColore()==1 && flagPedina)
                { percorso[pedina[1].getCasella()].setOcuupata(true);
                    percorso[pedina[1].getCasella()].setPedinaOcc(17);
                    pedina[1].SommaCasella(dado,vett[turno].GetColore());
                pedBIn2.setX(pedina[1].getX());
                pedBIn2.setY(pedina[1].getY());
                    EliminaPedina(1);
                    if(pedina[1].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[1].getCasella()].setOcuupata(false);
                percorso[pedina[1].getCasella()].setPedinaOcc(1);}
                flagPedina=false;
                //showToast1();
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                else
                    showTurno();
            }
        });
        pedBIn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(vett[turno].GetColore()==1 && flagPedina)
                {
                    percorso[pedina[2].getCasella()].setOcuupata(true);
                    percorso[pedina[2].getCasella()].setPedinaOcc(17);
                    pedina[2].SommaCasella(dado,vett[turno].GetColore());
                pedBIn3.setX(pedina[2].getX());
                pedBIn3.setY(pedina[2].getY());
                    EliminaPedina(2);
                    if(pedina[2].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[2].getCasella()].setOcuupata(false);
                percorso[pedina[2].getCasella()].setPedinaOcc(2);}
                flagPedina=false;//showToast1();
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }}
                 else
                     showTurno();
            }
        });
        pedBIn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(vett[turno].GetColore()==1 && flagPedina)
                {
                    percorso[pedina[3].getCasella()].setOcuupata(true);
                    percorso[pedina[3].getCasella()].setPedinaOcc(17);
                    pedina[3].SommaCasella(dado,vett[turno].GetColore());
                pedBIn4.setX(pedina[3].getX());
                pedBIn4.setY(pedina[3].getY());
                    EliminaPedina(3);
                    if(pedina[3].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[3].getCasella()].setOcuupata(false);
                percorso[pedina[3].getCasella()].setPedinaOcc(3);}
                flagPedina=false;//showToast1();
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                  else
                      showTurno();
            }
        });
        pedRIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vett[turno].GetColore()==2 && flagPedina)
                { percorso[pedina[4].getCasella()].setOcuupata(true);
                    percorso[pedina[4].getCasella()].setPedinaOcc(17);
                    pedina[4].SommaCasella(dado,vett[turno].GetColore());
                pedRIn1.setX(pedina[4].getX());
                pedRIn1.setY(pedina[4].getY());
                    EliminaPedina(4);
                    if(pedina[4].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[4].getCasella()].setOcuupata(false);
                percorso[pedina[4].getCasella()].setPedinaOcc(4);}
                    flagPedina=false;
                    //showToast1();
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                else
                    showTurno();
            }
        });
        pedRIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vett[turno].GetColore()==2 && flagPedina)
                { percorso[pedina[5].getCasella()].setOcuupata(true);
                    percorso[pedina[5].getCasella()].setPedinaOcc(17);
                    pedina[5].SommaCasella(dado,vett[turno].GetColore());
                pedRIn2.setX(pedina[5].getX());
                pedRIn2.setY(pedina[5].getY());
                    EliminaPedina(5);
                    if(pedina[5].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[5].getCasella()].setOcuupata(false);
                percorso[pedina[5].getCasella()].setPedinaOcc(5);}
                flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                else
                    showTurno();
            }
        });
        pedRIn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(vett[turno].GetColore()==2 && flagPedina)
                {
                    percorso[pedina[6].getCasella()].setOcuupata(true);
                    percorso[pedina[6].getCasella()].setPedinaOcc(17);
                    pedina[6].SommaCasella(dado,vett[turno].GetColore());
                pedRIn3.setX(pedina[6].getX());
                pedRIn3.setY(pedina[6].getY());
                    EliminaPedina(6);
                    if(pedina[6].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                    percorso[pedina[6].getCasella()].setOcuupata(false);
                    percorso[pedina[6].getCasella()].setPedinaOcc(6);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                  else
                      showTurno();
            }
        });
        pedRIn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(vett[turno].GetColore()==2 && flagPedina)
                { percorso[pedina[7].getCasella()].setOcuupata(true);
                    percorso[pedina[7].getCasella()].setPedinaOcc(17);
                    pedina[7].SommaCasella(dado,vett[turno].GetColore());
                pedRIn4.setX(pedina[7].getX());
                pedRIn4.setY(pedina[7].getY());
                    EliminaPedina(7);
                    if(pedina[7].getCasella()==58)
                    {
                        blu++;
                        txtB.setText(""+blu);
                    }
                    else{
                percorso[pedina[7].getCasella()].setOcuupata(false);
                percorso[pedina[7].getCasella()].setPedinaOcc(7);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                 else
                     showTurno();
            }
        });
        pedVIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vett[turno].GetColore()==3 && flagPedina)
                { percorso[pedina[8].getCasella()].setOcuupata(true);
                    percorso[pedina[8].getCasella()].setPedinaOcc(17);
                    pedina[8].SommaCasella(dado,vett[turno].GetColore());
                pedVIn1.setX(pedina[8].getX());
                pedVIn1.setY(pedina[8].getY());
                    EliminaPedina(8);
                    if(pedina[8].getCasella()==58)
                    {
                        verde++;
                        txtB.setText(""+verde);
                    }
                    else{
                percorso[pedina[8].getCasella()].setOcuupata(false);
                percorso[pedina[8].getCasella()].setPedinaOcc(8);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                  else
                      showTurno();
            }
        });
        pedVIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vett[turno].GetColore()==3 && flagPedina)
                {
                    percorso[pedina[9].getCasella()].setOcuupata(true);
                    percorso[pedina[9].getCasella()].setPedinaOcc(17);
                    pedina[9].SommaCasella(dado,vett[turno].GetColore());
                pedVIn2.setX(pedina[9].getX());
                pedVIn2.setY(pedina[9].getY());
                    EliminaPedina(9);
                    if(pedina[9].getCasella()==58)
                    {
                        verde++;
                        txtB.setText(""+verde);
                    }
                    else{
                percorso[pedina[9].getCasella()].setOcuupata(false);
                percorso[pedina[9].getCasella()].setPedinaOcc(9);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                  else
                      showTurno();

            }
        });
        pedVIn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vett[turno].GetColore()==3 && flagPedina)
                {
                    percorso[pedina[10].getCasella()].setOcuupata(true);
                    percorso[pedina[10].getCasella()].setPedinaOcc(17);
                    pedina[10].SommaCasella(dado,vett[turno].GetColore());
                pedVIn3.setX(pedina[10].getX());
                pedVIn3.setY(pedina[10].getY());
                    EliminaPedina(10);
                    if(pedina[10].getCasella()==58)
                    {
                        verde++;
                        txtB.setText(""+verde);
                    }
                    else{
                percorso[pedina[10].getCasella()].setOcuupata(false);
                percorso[pedina[10].getCasella()].setPedinaOcc(10);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                 else
                     showTurno();
            }
        });
        pedVIn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vett[turno].GetColore()==3 && flagPedina)
                {
                    percorso[pedina[11].getCasella()].setOcuupata(true);
                    percorso[pedina[11].getCasella()].setPedinaOcc(17);
                    pedina[11].SommaCasella(dado,vett[turno].GetColore());
                     pedVIn4.setX(pedina[11].getX());
                     pedVIn4.setY(pedina[11].getY());
                    EliminaPedina(11);
                    if(pedina[11].getCasella()==58)
                    {
                        verde++;
                        txtB.setText(""+verde);
                    }
                    else{
                     percorso[pedina[11].getCasella()].setOcuupata(false);
                       percorso[pedina[11].getCasella()].setPedinaOcc(11);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                  else
                      showTurno();
            }
        });
        pedGIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (vett[turno].GetColore() == 4 && flagPedina) {
                        percorso[pedina[12].getCasella()].setOcuupata(true);
                        percorso[pedina[12].getCasella()].setPedinaOcc(17);
                        pedina[12].SommaCasella(dado,vett[turno].GetColore());
                        pedGIn1.setX(pedina[12].getX());
                        pedGIn1.setY(pedina[12].getY());
                        EliminaPedina(11);
                        if(pedina[12].getCasella()==58)
                        {
                            giallo++;
                            txtB.setText(""+giallo);
                        }
                        else{
                        percorso[pedina[12].getCasella()].setOcuupata(false);
                        percorso[pedina[12].getCasella()].setPedinaOcc(12);}
                        flagPedina = false;
                        if(!avanzaPedina && dado!=6)
                        {
                            vett[turno].SetmaxGiro1(0);
                            turno++;
                            turni++;
                            if(turno==4)
                                turno=0;
                        }
                    } else
                        showTurno();

            }
        });
        pedGIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vett[turno].GetColore()==4 && flagPedina)
                {
                    percorso[pedina[13].getCasella()].setOcuupata(true);
                    percorso[pedina[13].getCasella()].setPedinaOcc(17);
                    pedina[13].SommaCasella(dado,vett[turno].GetColore());
                pedGIn2.setX(pedina[13].getX());
                pedGIn2.setY(pedina[13].getY());
                    EliminaPedina(13);
                    if(pedina[13].getCasella()==58)
                    {
                        giallo++;
                        txtB.setText(""+giallo);
                    }
                    else{
                      percorso[pedina[13].getCasella()].setOcuupata(false);
                      percorso[pedina[13].getCasella()].setPedinaOcc(13);}
                    flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                   else
                       showTurno();


            }
        });
        pedGIn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vett[turno].GetColore()==4 && flagPedina)
                {
                    percorso[pedina[14].getCasella()].setOcuupata(true);
                    percorso[pedina[14].getCasella()].setPedinaOcc(17);
                    pedina[14].SommaCasella(dado,vett[turno].GetColore());
                pedGIn3.setX(pedina[14].getX());
                pedGIn3.setY(pedina[14].getY());
                    EliminaPedina(14);
                    if(pedina[14].getCasella()==58)
                    {
                        giallo++;
                        txtB.setText(""+giallo);
                    }
                    else
                    {percorso[pedina[14].getCasella()].setOcuupata(false);
                    percorso[pedina[14].getCasella()].setPedinaOcc(14);}
                      flagPedina=false;
                    if(!avanzaPedina && dado!=6)
                    {
                        vett[turno].SetmaxGiro1(0);
                        turno++;
                        turni++;
                        if(turno==4)
                            turno=0;
                    }
                }
                   else
                       showTurno();
            }
        });
        pedGIn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   if(vett[turno].GetColore()==4 && flagPedina)
                    {
                        percorso[pedina[15].getCasella()].setOcuupata(true);
                        percorso[pedina[15].getCasella()].setPedinaOcc(17);
                        pedina[15].SommaCasella(dado,vett[turno].GetColore());
                         pedGIn4.setX(pedina[15].getX());
                         pedGIn4.setY(pedina[15].getY());
                         EliminaPedina(15);
                        if(pedina[15].getCasella()==58)
                        {
                            giallo++;
                            txtB.setText(""+giallo);
                        }
                        else{
                          percorso[pedina[15].getCasella()].setOcuupata(false);
                          percorso[pedina[15].getCasella()].setPedinaOcc(15);}
                        flagPedina=false;
                        if(!avanzaPedina && dado!=6)
                        {
                            vett[turno].SetmaxGiro1(0);
                            turno++;
                            turni++;
                            if(turno==4)
                                turno=0;
                        }
                    }
                      else
                          showTurno();
            }
        });
    }
    public void EliminaPedina( int i)
    {

        if(!percorso[pedina[i].getCasella()].getOccupata()  && (pedina[i].getCasella()!=0 && pedina[i].getCasella()!=13 && pedina[i].getCasella()!=26 && pedina[i].getCasella()!=39 ))//elimino la pedina presente
        {
                if(vett[turno].GetColore()!=pedina[percorso[pedina[i].getCasella()].getPedinaOcc()].getColore())
                {
                    switch(percorso[pedina[i].getCasella()].getPedinaOcc())//rende visibili le quattro peine iniziali dentro il quadrato
                    {
                        case 0: pedB1.setVisibility(View.VISIBLE);break;
                        case 1: pedB2.setVisibility(View.VISIBLE);break;
                        case 2: pedB3.setVisibility(View.VISIBLE);break;
                        case 3: pedB4.setVisibility(View.VISIBLE);break;
                        case 4: pedR1.setVisibility(View.VISIBLE);break;
                        case 5: pedR2.setVisibility(View.VISIBLE);break;
                        case 6: pedR3.setVisibility(View.VISIBLE);break;
                        case 7: pedR4.setVisibility(View.VISIBLE);break;
                        case 8: pedV1.setVisibility(View.VISIBLE);break;
                        case 9: pedV2.setVisibility(View.VISIBLE);break;
                        case 10:pedV3.setVisibility(View.VISIBLE);break;
                        case 11:pedV4.setVisibility(View.VISIBLE);break;
                        case 12:pedG1.setVisibility(View.VISIBLE);break;
                        case 13:pedG2.setVisibility(View.VISIBLE);break;
                        case 14:pedG3.setVisibility(View.VISIBLE);break;
                        case 15:pedG4.setVisibility(View.VISIBLE);break;
                    }
                    EliminaPedInv(percorso[pedina[i].getCasella()].getPedinaOcc());
                }
        }

    }
    public void EliminaPedInv(int c)//faccio diventare la pedina uccisa invisibile
    {
        switch(c)
        {
            case 0: pedBIn1.setVisibility(View.INVISIBLE);pedina[0].setCasella(0);
                percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(0);vett[0].IncrementaCPedinaInterna();
                pedBIn1.setX(pedina[0].getX());
                pedBIn1.setY(pedina[0].getY());break;
            case 1: pedBIn2.setVisibility(View.INVISIBLE);;pedina[1].setCasella(0);
                percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(1);vett[0].IncrementaCPedinaInterna();
                pedBIn2.setX(pedina[1].getX());
                pedBIn2.setY(pedina[1].getY());break;
            case 2: pedBIn3.setVisibility(View.INVISIBLE);;pedina[2].setCasella(0);
                percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(2);vett[0].IncrementaCPedinaInterna();
                pedBIn3.setX(pedina[2].getX());
                pedBIn3.setY(pedina[2].getY());break;
            case 3: pedBIn4.setVisibility(View.INVISIBLE);;pedina[3].setCasella(0);
                percorso[0].setOcuupata(false);percorso[0].setPedinaOcc(3);vett[0].IncrementaCPedinaInterna();
                pedBIn4.setX(pedina[3].getX());
                pedBIn4.setY(pedina[3].getY());break;
            case 4: pedRIn1.setVisibility(View.INVISIBLE);;pedina[4].setCasella(13);
                percorso[13].setOcuupata(false);percorso[0].setPedinaOcc(4);vett[1].IncrementaCPedinaInterna();
                pedRIn1.setX(pedina[4].getX());
                pedRIn1.setY(pedina[4].getY());break;
            case 5: pedRIn2.setVisibility(View.INVISIBLE);;pedina[5].setCasella(13);
                percorso[13].setOcuupata(false);percorso[0].setPedinaOcc(5);vett[1].IncrementaCPedinaInterna();
                pedRIn2.setX(pedina[5].getX());
                pedRIn2.setY(pedina[5].getY());break;
            case 6: pedRIn3.setVisibility(View.INVISIBLE);;pedina[6].setCasella(13);
                percorso[13].setOcuupata(false);percorso[0].setPedinaOcc(6);vett[1].IncrementaCPedinaInterna();
                pedRIn3.setX(pedina[6].getX());
                pedRIn3.setY(pedina[6].getY());break;
            case 7: pedRIn4.setVisibility(View.INVISIBLE);;pedina[7].setCasella(13);
                percorso[13].setOcuupata(false);percorso[0].setPedinaOcc(7);vett[1].IncrementaCPedinaInterna();
                pedRIn4.setX(pedina[7].getX());
                pedRIn4.setY(pedina[7].getY());break;
            case 8: pedVIn1.setVisibility(View.INVISIBLE);;pedina[8].setCasella(26);
                percorso[26].setOcuupata(false);percorso[0].setPedinaOcc(8);vett[2].IncrementaCPedinaInterna();
                pedVIn1.setX(pedina[8].getX());
                pedVIn1.setY(pedina[8].getY());break;
            case 9: pedVIn2.setVisibility(View.INVISIBLE);;pedina[9].setCasella(26);
                percorso[26].setOcuupata(false);percorso[0].setPedinaOcc(9);vett[2].IncrementaCPedinaInterna();
                pedVIn2.setX(pedina[9].getX());
                pedVIn2.setY(pedina[9].getY());break;
            case 10: pedVIn3.setVisibility(View.INVISIBLE);;pedina[10].setCasella(26);
                percorso[26].setOcuupata(false);percorso[0].setPedinaOcc(10);vett[2].IncrementaCPedinaInterna();
                pedVIn3.setX(pedina[10].getX());
                pedVIn3.setY(pedina[10].getY());break;
            case 11: pedVIn4.setVisibility(View.INVISIBLE);;pedina[11].setCasella(26);
                percorso[26].setOcuupata(false);percorso[0].setPedinaOcc(11);vett[2].IncrementaCPedinaInterna();
                pedVIn4.setX(pedina[11].getX());
                pedVIn4.setY(pedina[11].getY());break;
            case 12: pedGIn1.setVisibility(View.INVISIBLE);;pedina[12].setCasella(39);
                percorso[39].setOcuupata(false);percorso[0].setPedinaOcc(12);vett[3].IncrementaCPedinaInterna();
                pedGIn1.setX(pedina[12].getX());
                pedGIn1.setY(pedina[12].getY());break;
            case 13: pedGIn2.setVisibility(View.INVISIBLE);;pedina[13].setCasella(39);
                percorso[39].setOcuupata(false);percorso[0].setPedinaOcc(13);vett[3].IncrementaCPedinaInterna();
                pedGIn2.setX(pedina[13].getX());
                pedGIn2.setY(pedina[13].getY());break;
            case 14: pedGIn3.setVisibility(View.INVISIBLE);;pedina[14].setCasella(39);
                percorso[39].setOcuupata(false);percorso[0].setPedinaOcc(14);vett[3].IncrementaCPedinaInterna();
                pedGIn3.setX(pedina[14].getX());
                pedGIn3.setY(pedina[14].getY());break;
            case 15: pedGIn4.setVisibility(View.INVISIBLE);;pedina[15].setCasella(39);
                percorso[39].setOcuupata(false);percorso[0].setPedinaOcc(15);vett[3].IncrementaCPedinaInterna();
                pedGIn4.setX(pedina[15].getX());
                pedGIn4.setY(pedina[15].getY());break;
        }

    }
    public void Invisibili()
    {
        //prime pedine inizio sono invisibili
        pedRIn1.setVisibility(View.INVISIBLE);
        pedRIn2.setVisibility(View.INVISIBLE);
        pedRIn3.setVisibility(View.INVISIBLE);
        pedRIn4.setVisibility(View.INVISIBLE);
        pedBIn1.setVisibility(View.INVISIBLE);
        pedBIn2.setVisibility(View.INVISIBLE);
        pedBIn3.setVisibility(View.INVISIBLE);
        pedBIn4.setVisibility(View.INVISIBLE);
        pedVIn1.setVisibility(View.INVISIBLE);
        pedVIn2.setVisibility(View.INVISIBLE);
        pedVIn3.setVisibility(View.INVISIBLE);
        pedVIn4.setVisibility(View.INVISIBLE);
        pedGIn1.setVisibility(View.INVISIBLE);
        pedGIn2.setVisibility(View.INVISIBLE);
        pedGIn3.setVisibility(View.INVISIBLE);
        pedGIn4.setVisibility(View.INVISIBLE);
    }
    public void PosizionaPedine()
    {
        //pedina[0].SommaCasella(0);
        pedBIn1.setX(pedina[0].getX());
        pedBIn1.setY(pedina[0].getY());
        //pedina[1].SommaCasella(0);
        pedBIn2.setX(pedina[1].getX());
        pedBIn2.setY(pedina[1].getY());
        // pedina[2].SommaCasella(0);
        pedBIn3.setX(pedina[2].getX());
        pedBIn3.setY(pedina[2].getY());
        // pedina[3].SommaCasella(0);
        pedBIn4.setX(pedina[3].getX());
        pedBIn4.setY(pedina[3].getY());
        //rosso-------------
        // pedina[4].SommaCasella(0);
        pedRIn1.setX(pedina[4].getX());
        pedRIn1.setY(pedina[4].getY());
        // pedina[5].SommaCasella(0);
        pedRIn2.setX(pedina[5].getX());
        pedRIn2.setY(pedina[5].getY());
        //pedina[6].SommaCasella(0);
        pedRIn3.setX(pedina[6].getX());
        pedRIn3.setY(pedina[6].getY());
        // pedina[7].SommaCasella(0);
        pedRIn4.setX(pedina[7].getX());
        pedRIn4.setY(pedina[7].getY());
        //verde-------------
        //pedina[8].SommaCasella(0);
        pedVIn1.setX(pedina[8].getX());
        pedVIn1.setY(pedina[8].getY());
        // pedina[9].SommaCasella(0);
        pedVIn2.setX(pedina[9].getX());
        pedVIn2.setY(pedina[9].getY());
        // pedina[10].SommaCasella(0);
        pedVIn3.setX(pedina[10].getX());
        pedVIn3.setY(pedina[10].getY());
        // pedina[11].SommaCasella(0);
        pedVIn4.setX(pedina[11].getX());
        pedVIn4.setY(pedina[11].getY());
        //giallo-----------
        // pedina[12].SommaCasella(0);
        pedGIn1.setX(pedina[12].getX());
        pedGIn1.setY(pedina[12].getY());
        // pedina[13].SommaCasella(0);
        pedGIn2.setX(pedina[13].getX());
        pedGIn2.setY(pedina[13].getY());
        // pedina[14].SommaCasella(0);
        pedGIn3.setX(pedina[14].getX());
        pedGIn3.setY(pedina[14].getY());
        //  pedina[15].SommaCasella(0);
        pedGIn4.setX(pedina[15].getX());
        pedGIn4.setY(pedina[15].getY());
    }

}
