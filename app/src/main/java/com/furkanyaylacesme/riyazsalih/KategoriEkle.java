package com.furkanyaylacesme.riyazsalih;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class KategoriEkle extends AppCompatActivity {
    DatabaseHelper db;


    private Button btnSil;
    private EditText editText;
    private Button button_kaydet;
    private Spinner spinner_show;
    private EditText yeniName;
    private Button btnGncl;
    private List<String> kategori_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_ekle);

        editText = findViewById(R.id.editText);
        button_kaydet = findViewById(R.id.button_save);
        spinner_show = findViewById(R.id.spinner_show);
        btnSil = findViewById(R.id.button4);
        yeniName = findViewById(R.id.editText3);
        btnGncl = findViewById(R.id.button2);



        fetchSpinnerValues();

        button_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategori_adi = editText.getText().toString();

                if(kategori_adi.toString().isEmpty())
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(KategoriEkle.this).create();
                    alertDialog.setTitle("UYARI");
                    alertDialog.setMessage("Kategori Adını Giriniz!");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    /*alertDialog.setButton("TAMAM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                }
                            });*/

                    alertDialog.show();
                }
                else
                {
                    db = new DatabaseHelper(getApplicationContext());
                    db.insertData(kategori_adi);

                    deleteValue();

                    fetchSpinnerValues();

                }
            }
        });


        btnGncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kategoriAdi = spinner_show.getSelectedItem().toString();

                String yeni = yeniName.getText().toString();
                db.kategoriGuncelle(kategoriAdi,yeni);
                fetchSpinnerValues();
            }
        });
        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kategoriAdi = spinner_show.getSelectedItem().toString();


                db.kategoriSil(kategoriAdi);

                fetchSpinnerValues();

            }
        });


/*
        db = new DatabaseHelper(this);
        SQLiteDatabase database=db.getWritableDatabase();


        katEkle = findViewById(R.id.kategoriEkle);
        katAdi = findViewById(R.id.kategoriAdi);


        db.spinnerlaGoster();
        katEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategoriAdi = katAdi.getText().toString();
                if (!kategoriAdi.equals("") && db.insertData(kategoriAdi)) {
                    Toast.makeText(KategoriEkle.this, kategoriAdi + " Kategorilere Eklendi ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(KategoriEkle.this, "Eklenemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void fetchSpinnerValues() {

        db = new DatabaseHelper(getApplicationContext());

        kategori_list = db.spinnerlaKategorilerAl();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,kategori_list);

        spinner_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner_show.setAdapter(spinner_adapter);
    }

    private void deleteValue(){
        editText.setText("");
    }


}
