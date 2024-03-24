package com.berkay.ogrenciSecmeApp;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        OgrenciFileIslemler ogrenciFileIslemler = new OgrenciFileIslemler();

        try {
            ogrenciFileIslemler.baslangicVerisiOlusturma();
            List<String> ogrenciListesi = ogrenciFileIslemler.dosyadanVeriOku();
            ogrenciFileIslemler.listeyiDosyayaYazdirma(ogrenciListesi);
            String secilenOgrenci = ogrenciFileIslemler.ogrenciSec(ogrenciListesi);
            ogrenciFileIslemler.dosyadanVeriSil();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}