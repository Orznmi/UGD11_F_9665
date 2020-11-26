package com.pbp.gd11_f_9665.ui.pdf;

public class Mahasiswa {
    private int nomor;
    private String nama;
    private String nim;

    public Mahasiswa(int nomor, String nama, String nim) {
        this.nomor = nomor;
        this.nama = nama;
        this.nim = nim;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
