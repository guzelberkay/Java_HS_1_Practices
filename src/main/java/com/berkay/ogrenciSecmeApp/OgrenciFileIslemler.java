package com.berkay.ogrenciSecmeApp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OgrenciFileIslemler {
    /*
        ogrenci.txt dosyasını okuyup
        secilecekler.txt dosyasına aktaracğım
        daha sonra bir ogrenci secip bu ogrenci verisini secilenler.txt dosyasına aktarıp (secilen ogrenci diye cıktıyı vermiş olacagız )
        secilecekler.txt dosyasından silmem gerekecek
            dosyadan verioku==> ogrencitxt.dosyasını okuyacak ve bir listede verileri toplayacak
            listeyiDosyayaYazdirma==> listedeki verileri  secilecekler.txt dosyasına yazdıracagız
            ogrenciSec=> listemizden rastgele bir ogrenciyi secip geri donecek
            secilenOgrenciyiDosyayYazdir==> secilen ogrenci ismini secilenler.txt dosyasına ekleyelim
        //2.kısım
        /*
         baslangicVerisiOlusturma==>   dosyadan veri okuma işlemi ve secilecekler.txt dosyasını olusturma işlemini bu metot ustlenecek
         listedenOgrenciSecme==>   bir ogrenci secilecek(ogrenci sec metodu )
         gelen ogrenci dosyaya yazılacak(secilenOgrenciyiDosyayYazdir)
         secilen ogrenci ismini yazdırıp
         daha sonra bu ogrenci silinecek ve
         seilecekler.txt guncellenecek
         */

    public static final String OGRENCI_FILE = "src/main/resources/ogrenci.txt";
    public static final String SECILECEKLER_FILE = "src/main/resources/secilecekler.txt";
    public static final String SECILENLER_FILE = "src/main/resources/secilenler.txt";

    public void baslangicVerisiOlusturma() throws IOException {
        Path secileceklerFolder = Paths.get(SECILECEKLER_FILE).getParent();
        if (!Files.exists(secileceklerFolder)) {
            Files.createDirectories(secileceklerFolder);
        }
        if (!Files.exists(Paths.get(SECILECEKLER_FILE))) {
            Files.createFile(Paths.get(SECILECEKLER_FILE));
        }
        if (!Files.exists(Paths.get(SECILENLER_FILE))) {
            Files.createFile(Paths.get(SECILENLER_FILE));
        }
    }

    public List<String> dosyadanVeriOku() {
        List<String> ogrenciListesi = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(OGRENCI_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                ogrenciListesi.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ogrenciListesi;
    }

    public void listeyiDosyayaYazdirma(List<String> ogrenciListesi) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SECILECEKLER_FILE))) {
            for (String ogrenci : ogrenciListesi) {
                bw.write(ogrenci);
                bw.newLine();
            }
        }
    }

    public void secilenOgrenciyiDosyayYazdir(String secilenOgrenci) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SECILENLER_FILE, true))) {
            bw.write(secilenOgrenci);
            bw.newLine();
        }
    }

    public String ogrenciSec(List<String> ogrenciListesi) {
        int randomIndex = new Random().nextInt(ogrenciListesi.size() - 1);
        String secilenOgrenci = ogrenciListesi.get(randomIndex);

        try {
            secilenOgrenciyiDosyayYazdir(secilenOgrenci);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Seçilen öğrenci: " + secilenOgrenci);

        return secilenOgrenci;
    }


    public void dosyadanVeriSil() throws IOException {
        List<String> secilecekOgrenciListesi = Files.readAllLines(Paths.get(SECILECEKLER_FILE));
        List<String> ogrenciListesi = Files.readAllLines(Paths.get(OGRENCI_FILE));

        for (String ogrenci : secilecekOgrenciListesi) {
            ogrenciListesi.remove(ogrenci);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OGRENCI_FILE))) {
            for (String ogrenci : ogrenciListesi) {
                bw.write(ogrenci);
                bw.newLine();
            }
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(SECILECEKLER_FILE, false))) {
            printWriter.print("");
        }
    }
}