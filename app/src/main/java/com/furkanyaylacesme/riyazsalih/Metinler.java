package com.furkanyaylacesme.riyazsalih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Metinler extends AppCompatActivity {


    DatabaseHelper db;

    TextView kitapAdi;
    TextView raviAdi;
    TextView metinAdi;

    Intent kategoriIntent;

    ArrayList<Hadis> hadisler;
    int kategoriId;
    int sayac = 0;

    ArrayList<Hadis> gosterilecekler;
    ArrayList<Kategori> kategoriGosterilecekler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metinler);

        db = new DatabaseHelper(this);

        kitapAdi = findViewById(R.id.kitapText);
        raviAdi = findViewById(R.id.raviText);
        metinAdi = findViewById(R.id.metinText);

        kategoriIntent = getIntent();
        kategoriId = kategoriIntent.getIntExtra("kategoriId",0);

        hadisler = (ArrayList<Hadis>) db.hadisleriAl();
        gosterilecekler = new ArrayList<>();
        kategoriGosterilecekler = new ArrayList<>();

        kategoriGosterilecekler = db.kategorileriAl();
        hadisleriGoster();
        init();






    }
    private void init() {
        Hadis hadis;

        int size=gosterilecekler.size();
        if(sayac<size){
            hadis=gosterilecekler.get(sayac);
            kitapAdi.setText(hadis.getKitapAdi());
            raviAdi.setText(hadis.getRaviAdi());
            metinAdi.setText(hadis.getMetin());


        }
    }

    public void hadisleriGoster(){
        int ID = kategoriId;
        for (Hadis hadis:hadisler){
            if(hadis.getKategoriId()==ID){
                gosterilecekler.add(hadis);
            }
        }
    }


    public void sagaKay(View view)
    {
        int size=gosterilecekler.size();
        if(sayac!=size){
            sayac++;
        }
        Hadis hadis;
        Log.e("TTTT","SAYAC:"+sayac);


        if(sayac>=0){
            if(sayac<size){
                hadis=gosterilecekler.get(sayac);
                kitapAdi.setText(hadis.getKitapAdi());
                raviAdi.setText(hadis.getRaviAdi());
                metinAdi.setText(hadis.getMetin());
                if(sayac<size||sayac!=size){
                    sayac++;

                }

            }
        }

    }

    public void solaKay(View view)
    {
        Hadis hadis;
        Log.e("TTTT","SAYAC:"+sayac);
        if(sayac!=0){
            sayac--;
        }
        if(sayac>=0){
                int size=gosterilecekler.size();
                if(sayac<size){
                    hadis=gosterilecekler.get(sayac);
                    kitapAdi.setText(hadis.getKitapAdi());
                    raviAdi.setText(hadis.getRaviAdi());
                    metinAdi.setText(hadis.getMetin());



                }
            }





    }
}
