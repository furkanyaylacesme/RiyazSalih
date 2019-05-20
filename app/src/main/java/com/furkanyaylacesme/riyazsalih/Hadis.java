package com.furkanyaylacesme.riyazsalih;

public class Hadis {
    private int kategoriId;
    private String kitapAdi;
    private String raviAdi;
    private String metin;


    public Hadis(int kategoriId, String kitapAdi, String raviAdi, String metin) {
        this.kategoriId = kategoriId;
        this.kitapAdi = kitapAdi;
        this.raviAdi = raviAdi;
        this.metin = metin;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getRaviAdi() {
        return raviAdi;
    }

    public void setRaviAdi(String raviAdi) {
        this.raviAdi = raviAdi;
    }

    public String getMetin() {
        return metin;
    }

    public void setMetin(String metin) {
        this.metin = metin;
    }
}
