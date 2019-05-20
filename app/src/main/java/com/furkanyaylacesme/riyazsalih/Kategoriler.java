package com.furkanyaylacesme.riyazsalih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Kategoriler extends AppCompatActivity {


    DatabaseHelper db;

    Button add_data;
    EditText add_kat;

    RecyclerView rvKategori;

    ArrayList<Kategori> listItem;
    KategoriAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriler);

        rvKategori = findViewById(R.id.hdsListesi);

        db = new DatabaseHelper(this);

        listItem = db.kategorileriAl();
        if(listItem.size()==0){
            Log.e("YYYY","LİSt item null eşit");
        }
        rvKategori.setLayoutManager(new LinearLayoutManager(this));
        adapter=new KategoriAdapter(this,listItem);
        rvKategori.setAdapter(adapter);
        Log.e("YYYY","Adapter Bağlandı...");
    }




}

