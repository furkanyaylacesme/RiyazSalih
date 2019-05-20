package com.furkanyaylacesme.riyazsalih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void calistir(View v){
        Intent i = null;

        if(v.getId()==R.id.ktgrEkle){
            i = new Intent(Menu.this,KategoriEkle.class);
            startActivity(i);
        }

        else if (v.getId()==R.id.hdslistele){
            i = new Intent(Menu.this,Kategoriler.class);
            startActivity(i);
        }

        else if (v.getId()==R.id.hadisekle)
        {
            i = new Intent(Menu.this,EkleHadis.class);
            startActivity(i);
        }


    }

}
