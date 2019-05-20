package com.furkanyaylacesme.riyazsalih;

public class Kategori {
    private int kategoriID;
    private String kategoriAdi;

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }

    public Kategori(int kategoriID, String kategoriAdi) {
        this.kategoriID = kategoriID;
        this.kategoriAdi = kategoriAdi;
    }
}
