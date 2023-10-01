package com.example.project_boricue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class splash extends AppCompatActivity {

    private int Coco = 0;
    private ProgressBar Barra;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Barra = findViewById(R.id.progressBar);

        Correr();
        Contar();

    }
    //metodo para hacer que el progressbar se llene
    public void Correr(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(i=0 ; i<=100 ; i++){
                    Coco = Coco + 10;
                    Barra.setProgress(Coco);
                    Barra.setMax(100);
                }


            }
        },3000);
    }

    //metodo para determinar tiempo para pasar al siguiente activity
    public void Contar(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent runlogin = new Intent(splash.this, MainActivity.class);
                runlogin.putExtra("usuario","Empresario");
                startActivity(runlogin);
            }
        },4000);


    }
}