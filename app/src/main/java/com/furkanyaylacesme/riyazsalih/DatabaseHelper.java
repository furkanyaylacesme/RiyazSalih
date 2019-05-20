package com.furkanyaylacesme.riyazsalih;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Kategori.db";
    private static final String DB_TABLE = "Kategoriler";
    private static final String DB_TABLE2 = "Hadisler";


    //columns
    private static final String ID = "katID";
    private static final String kat_Adi = "katAdi";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +"("+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            kat_Adi+ " TEXT NOT NULL " + ")";

    private static final String CREATE_TABLE2= "CREATE TABLE " + DB_TABLE2 +
            "(hadisNo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "kitapAdi TEXT," +
            "raviAdi TEXT," +
            "metin TEXT," +
            "kategID INTEGER );";
//"FOREIGN KEY(kategID) REFERENCES Kategoriler(katID)



    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
        onCreate(db);
    }


    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("katAdi",name);

        long result = db.insert(DB_TABLE,null,contentValues);

        if(result==-1){
            Log.e("YYYY","eklenmedi.");
        }



        return result != -1; //Data eklenemediyse -1 gönder

    }



    public boolean metinEkle(int katNo, String kAdi, String rAdi, String gelenMetin)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("kategID",katNo);
        contentValues.put("kitapAdi",kAdi);
        contentValues.put("raviAdi",rAdi);
        contentValues.put("metin",gelenMetin);
        long result = db.insert(DB_TABLE2,null,contentValues);
        if(result==-1){
            Log.e("YYYY","eklenmedi.");
        }
        return result != -1;

    }

    public Cursor kategorileriGoster(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = db.rawQuery(query,null);


        return cursor;
    }

    public ArrayList<Kategori> kategorileriAl()
    {
        Cursor cursor = kategorileriGoster();
        ArrayList<Kategori> kategoriler = new ArrayList<>();
        Kategori kategori = null;


        while (cursor.moveToNext())
        {
            int IDIndex = cursor.getColumnIndex("katID");
            int katAdiIndex = cursor.getColumnIndex("katAdi");

            int katId = cursor.getInt(IDIndex);
            String katAdi = cursor.getString(katAdiIndex);


            kategori = new Kategori(katId,katAdi);

            kategoriler.add(kategori);
        }
        Log.e("YYYY","Kategori size:"+kategoriler.size());

        return  kategoriler;
    }

    public List<Hadis> hadisleriAl(){


        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu = "SELECT * FROM " + DB_TABLE2;
        Cursor cursor = db.rawQuery(sorgu,null);
        List<Hadis> hadisler = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int katIndex = cursor.getColumnIndex("kategID");
            int kitapAdiIndex = cursor.getColumnIndex("kitapAdi");
            int raviAdiIndex = cursor.getColumnIndex("raviAdi");
            int metinIndex = cursor.getColumnIndex("metin");


            int kategoriId = cursor.getInt(katIndex);
            String kitapAdı = cursor.getString(kitapAdiIndex);
            String raviAdi = cursor.getString(raviAdiIndex);
            String metinAdi = cursor.getString(metinIndex);

            Hadis hadis = new Hadis(kategoriId,kitapAdı,raviAdi,metinAdi);
            hadisler.add(hadis);

        }
        return hadisler;
    }

    public List<String> spinnerlaKategorilerAl(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu = "SELECT * FROM "+ DB_TABLE;
        Cursor cursor = db.rawQuery(sorgu,null);
        List<String> kategoriler = new ArrayList<String>();

        if (cursor.moveToFirst()) {

            do {
                kategoriler.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return kategoriler;
    }

    public void kategoriSil(String name)
    {
        this.getWritableDatabase().delete("Kategoriler","katAdi='"+name+"'", null);

    }

    public void  kategoriGuncelle(String eskiName, String yeniName){

        this.getWritableDatabase().execSQL("UPDATE Kategoriler SET katAdi = '"+yeniName+"' WHERE katAdi = '"+eskiName+"'");
    }


    /*public ArrayList<String> spinnerlaGoster()
    {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String query = "SELECT katAdi FROM "+DB_TABLE;
            //[] columns={kat_Adi};
           // Cursor cursor = db.query(DB_TABLE,columns,null,null,null,null,null);
          *//*      if(cursor!=null){
                    Log.e("YYYY","CURSO BOŞ");
                }
                *//*
            Cursor cursor = db.rawQuery(query,null);

            if(cursor.getCount()>0)
            {
                while (cursor.moveToNext())
                {
                    String kategoriAdi = cursor.getString(cursor.getColumnIndex("kat_Adi"));
                    list.add(kategoriAdi);
                }
            }else{
                Log.e("YYYY","CURSO BOŞ");
            }
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }*/
}
