package com.technic.ludogame1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Menu extends AppCompatActivity {
    private static int tempo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button play=(Button) findViewById(R.id.btnplay);
        Button regole=(Button) findViewById(R.id.btnRegole);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,Dado.class);
                startActivity(i);
            }
        });
        regole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o=new Intent(Menu.this,Regole.class);
                startActivity(o);
            }
        });
    }
}
