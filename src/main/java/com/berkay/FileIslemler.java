package com.berkay;

import java.io.*;

public class FileIslemler {
    String dosyaPath = "src/main/resources/";

    public void menu() {
        System.out.println(" " +
                "    1- Dosya yarat\n" +
                "    2- Dosya Sil\n" +
                "    3- Dosya ya veri ekle\n" +
                "    4- Dosya dan veri oku\n" +
                "    5- Dosya daki bir harfi başka bir harf ile değiştir");
    }

    public void uygulama() {
        int secim = 0;
        do {
            menu();
            secim = Utility.intDegerAlma("lütfen bir işlem seciniz");
            switch (secim) {
                case 1:
                    dosyaOlustur();
                    break;
                case 2:
                    dosyaSil();
                    break;
                case 3:
                    dosyayaVeriYaz();
                    break;
                case 4:
                    dosyadanVeriOkuma();
                    break;
                case 5:
                    //   harfDegistir();
                    break;
                case 0:
                    break;
            }
        } while (secim != 0);


    }

    public void dosyaSil() {
        String path = Utility.stringDegerAlma("Lutfen silmek istediginiz dosya adi giriniz!");
        File dosya = new File(dosyaPath + path + ".txt");
        if (dosya.delete()) {
            System.out.println("DOSYA BASARILI SEKILDE SILINDI");
        } else {
            System.out.println("DOSYA BULUNAMADI");
        }

    }

    public void dosyaOlustur() {
        String path = Utility.stringDegerAlma("Lutfen bir dosya adi giriniz!");
        File dosya = new File(dosyaPath + path + ".txt");

        if (dosya.exists()) {
            System.out.println("Dosya daha onceden olusturulmustur!");
        } else {
            try {
                dosya.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Dosya basarili bir sekilde olusturuldu!");
        }

    }

    public void dosyayaVeriYaz() {

        String fileName = Utility.stringDegerAlma("Lütfen dosya  adini uzantisi ile birlikte giriniz: ");
        File dosya = new File(FileSabitler.path + fileName);
        BufferedWriter writer = null;
        try {
            if (dosya.exists()) {
                String metin = Utility.stringDegerAlma("Yazmak istediğiniz metni giriniz.");
                writer = new BufferedWriter(new FileWriter(dosya, true));
                writer.write(metin);
                writer.newLine();
            } else
                System.out.println("Aradığınız dosya bulunamamıştır.");
        } catch (Exception e) {
            System.out.println("Beklenmeyen bir hata oluştu.");
            ;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String dosyadanVeriOkuma() {
        String fileName = Utility.stringDegerAlma("Lütfen Dosya Adını dosya uzantısı ile beraber giriniz");
        File dosya = new File(FileSabitler.path + fileName);
        String tumMetin = "";

        BufferedReader bufferedReader = null;
        try {
            if (dosya.exists()) {
                System.out.println("Dosya Bulundu");
                bufferedReader = new BufferedReader(new FileReader(dosya));
                String metin = "";
                while ((metin = bufferedReader.readLine()) != null) {
                    tumMetin += metin + "\n";
                }
            } else {
                System.out.println("Dosya Bulunamadı");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tumMetin.substring(0, tumMetin.length() - 1);
    }
}

