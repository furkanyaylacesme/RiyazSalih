package com.furkanyaylacesme.riyazsalih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EkleHadis extends AppCompatActivity {



    DatabaseHelper db;

    Button ekle;
    Spinner kategoriSpinner;
    TextView kitapAdiText;
    TextView raviAdiText;
    public int pozisyon;
    private List<String> kategori_list;
    TextView metinText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle_hadis);

        db = new DatabaseHelper(this);


        kategoriSpinner = findViewById(R.id.kategorispinner);
        kitapAdiText = findViewById(R.id.kitapAdi);
        raviAdiText = findViewById(R.id.raviAdi);
        metinText = findViewById(R.id.hadisText);

        ekle = findViewById(R.id.hadisEkle);

        fetchSpinnerValues();

        //ArrayList<String> KategoriListemiz =db.spinnerlaKategorilerAl();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_ekle_hadis,R.id.ktgrlst,KategoriListemiz);

       // kategoriSpinner.setAdapter(adapter);

/*
        kategoriSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pozisyon = (int) (kategoriSpinner.getSelectedItemId());
            }
        });
*/

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int katNo = (int) kategoriSpinner.getSelectedItemId() + 1 ;
                String kategoriAdi = kategoriSpinner.getSelectedItem().toString();
                String kitapAdi = kitapAdiText.getText().toString();
                String raviAdi = raviAdiText.getText().toString();
                String metin = metinText.getText().toString();

               /* boolean isTrue = db.metinEkle(katNo,kitapAdi,raviAdi,metin);
                if(isTrue==-1){
                    Log.e("YYYY","salla");
                }*/
//&& db.metinEkle(katNo,kitapAdi,raviAdi,metin)
                if (!kitapAdi.equals("") && !raviAdi.equals("") && !metin.equals("") && db.metinEkle(katNo,kitapAdi,raviAdi,metin)) {
                    Toast.makeText(EkleHadis.this, "Kat No su "+ katNo + " "+kategoriAdi + " Kategorisine " + kitapAdi +" Kitabı " + raviAdi + " Ravili "+ metin +" Eklendi", Toast.LENGTH_SHORT).show();
                    fetchSpinnerValues();
                } else {
                    Toast.makeText(EkleHadis.this, "Eklenemedi", Toast.LENGTH_SHORT).show();
                    fetchSpinnerValues();
                }
            }
        });


    }

    private void fetchSpinnerValues() {

        db = new DatabaseHelper(getApplicationContext());

        kategori_list = db.spinnerlaKategorilerAl();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,kategori_list);

        spinner_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        kategoriSpinner.setAdapter(spinner_adapter);
    }
}
