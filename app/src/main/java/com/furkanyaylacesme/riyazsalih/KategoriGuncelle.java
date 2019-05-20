package com.furkanyaylacesme.riyazsalih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class KategoriGuncelle extends AppCompatActivity {

    DatabaseHelper db;

    private EditText editText;
    private Button button_Guncelle;
    private Button button_Sil;
    private Spinner spinner_show;
    private List<String> kategori_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_guncelle);

        editText = findViewById(R.id.degisecekText);
        button_Guncelle = findViewById(R.id.guncelleButon);
        button_Sil = findViewById(R.id.silButon);
        spinner_show = findViewById(R.id.kategorispinner);

        fetchSpinnerValues();


    }

    private void fetchSpinnerValues() {
        db = new DatabaseHelper(this);

        kategori_list = db.spinnerlaKategorilerAl();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,kategori_list);

        spinner_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner_show.setAdapter(spinner_adapter);
    }
}
