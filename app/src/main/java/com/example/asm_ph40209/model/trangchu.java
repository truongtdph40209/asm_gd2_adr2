package com.example.asm_ph40209.model;

public class trangchu {
    private int masp;
    private String tensp, giaban, soluong;

    public trangchu() {
    }

    public trangchu(int masp, String tensp, String giaban, String soluong) {
        this.masp = masp;
        this.tensp = tensp;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public trangchu(String tensp, String giaban, String soluong) {
        this.tensp = tensp;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiaban() {
        return giaban;
    }

    public void setGiaban(String giaban) {
        this.giaban = giaban;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
