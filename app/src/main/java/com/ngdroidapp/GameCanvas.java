package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.Random;
import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {
    //karakter etiketleri
    int KDURUM_NORMAL = 0, KDURUM_ATES = 1, KDURUM_HASAR = 2, KDURUM_OLUM = 3;
    //Düşman Etiketleri
    int DUSMAN_TUR = 0, DUSMAN_FRAME = 1, DUSMAN_X = 2, DUSMAN_Y = 3, DUSMAN_HIZX = 4,
    DUSMAN_HIZY = 5, DUSMAN_MERMI = 6, DUSMAN_RELOAD = 7;
    //Oyuncu ateş etiketleri
    int OATES_FRAME = 0, OATES_X = 1, OATES_Y = 2, OATES_W = 3, OATES_H = 4, OATES_HIZ = 5;
    //Düşman ateş etiketleri
    int DATES_TUR = 0, DATES_FRAME = 1, DATES_X = 2, DATES_Y = 3, DATES_W = 4, DATES_H = 5,
            DATES_HIZ = 6;
    //Düşman türleri
    int DUSMAN_RED = 0, DUSMAN_YELLOW = 1, DUSMAN_GREEN = 2;
    //patlama etiketkleri
    int PATLAMA_FRAME = 0, PATLAMA_X = 1, PATLAMA_Y = 2;
    //bonus etiketleri
    int BONUS_CAN = 0, BONUS_ENERJI = 1, BONUS_GUC = 2;
    int BONUS_TUR = 0, BONUS_FRAME = 1, BONUS_X = 2, BONUS_Y = 3, BONUS_W =4,
            BONUS_H = 5, BONUS_HIZ = 6;
    //boss hasar etiketleri
    int BOSSHASAR_FRAME = 0, BOSSHASAR_X = 1, BOSSHASAR_Y = 2;
    //boss ates etiketleri
    int BOSSATES_TUR = 0, BOSSATES_FRAME = 1, BOSSATES_X = 2, BOSSATES_Y = 3, BOSSATES_W = 4,
    BOSSATES_H = 5, BOSSATES_HIZX = 6, BOSSATES_HIZY = 7;


    //Çarpışma Kontrolleri
    Rect carpisan1, carpisan2;

    //Arkaplan parametreleri
    Canvas canvas;
    Bitmap arkaplan;
    Rect arkaplankaynak, arkaplanhedef;
    int kaydirmahizi, kaydirmamiktari;

    //oyuncu parametreleri
    Bitmap oyuncuresmi[][];
    int karakterdurumsayisi, karakterdurum, karakterframe;
    int karaktermaxframe[];
    int oyuncux, oyuncuy, oyuncuhiz;
    int karaktermaxcan, karaktersuankican;
    int karaktersuankienerji, karaktermaxenerji;

    //Oyuncu ateş
    Bitmap oyuncuatesresmi[];
    int oyatesframesayisi;
    Vector<Integer> oyuncuates;
    Vector<Vector<Integer>> oyateslistesi;

    //Düşman ateş
    Bitmap dusmanatesresmi[][];
    int mermicesitleri, mermimaxframe[];
    Vector<Integer> dusmanatesi;
    Vector<Vector<Integer>> dusmanateslistesi;
    //Reload süreleri
    Vector<Long> dusmanreloadsureleri;

    //ateş butonu
    Bitmap atesbutonu;
    int atesbutonux, atesbutonuy;

    //asagı ok
    Bitmap asagibuton;
    int asagibutonx, asagibutony;
    boolean asagigidecek;

    //yukari ok
    Bitmap yukaributon;
    int yukaributonx, yukaributony;
    boolean yukarigidecek;

    //Puan çerçevesi
    Bitmap puancerceve;
    int puancercevex, puancercevey;
    //Puan yazısı
    int puanx, puany, puan;
    Paint puanrengi;
    int dusmanpuanlari[];

    //Düşman parametreleri
    Vector<Vector<Integer>> dusmanlistesi;
    Vector<Integer> yenidusman;
    Bitmap dusmanresmi[][];
    int dusmancesitleri, dusmanmaxframe[];
    int dusmanmermituru[];

    //Genel parametreler
    Random rasgelesayi;
    double baslangiczamani, suankizaman;
    boolean oyundevamediyor, oyunbitti;

    //pause menusu
    Bitmap ayarbutonresmi;
    int ayarbutonx, ayarbutony;
    Bitmap pausemenu;
    int pausemenux, pausemenuy;
    int devamx, devamy, devamw, devamh;
    int yenidenx, yenideny, yenidenw, yenidenh;
    int menudonx, menudony, menudonw, menudonh;

    //oyun sonu ekranı
    Bitmap oyunsonuekrani[];
    int yildizsayisi, oyunsonuekranix, oyunsonuekraniy;
    int yildizseviyesi;
    int osyenidenx, osyenideny, osyenidenw, osyenidenh;
    int osilerix, osileriy, osileriw, osilerih;
    Paint oyunsonuyazisi;
    int oyunsonuyazisix, oyunsonuyazisiy;

    //patlama efekti
    Bitmap patlamaresimleri[];
    int patlamaframesayisi, patlamax, patlamay;
    Vector<Integer> yenipatlama;
    Vector<Vector<Integer>> patlamalistesi;

    //can ve enerji
    Bitmap gosterge, cancubugu, enerjicubugu;
    int gostergex, gostergey, cancubugux, cancubuguy, enerjicubugux, enerjicubuguy;
    Rect cankaynak, canhedef;
    int cancubugumax, canazaltmamiktari;
    int enerjicubugumax, enerjiazaltmamiktari;
    int gosterge2x, gosterge2y;
    Rect enerjikaynak, enerjihedef;
    boolean karakteratesetsin;

    //bonus eşyaları
    Bitmap bonusesyaresmi[][];
    int bonuscesidi, bonusmaxframe[];
    Vector<Integer> yenibonus;
    Vector<Vector<Integer>> bonuslistesi;
    long bonusbaslangic, bonussuan;
    boolean gucbonusuaktif;
    int gucbonususayaci;

    //boss parametreleri
    int bossx, bossy, bossmaxframe, bosssuankiframe;
    Bitmap boss1[];
    int bosshizx, bosshizy;
    int hareketpatenti;
    Bitmap bosscani, bosscancubugu;
    int bossmaxcani, bosssuankicani, bosscanx, bosscany, bosscancubugux, bosscancubuguy, bosscanazaltmamiktari;
    Rect bosscankaynak, bosscanhedef;
    int bosscanileft, bosscanitop, bosscaniright, bosscanidown;
    Rect bosscancubugukaynak, bosscancubuguhedef;
    int bosscancubuguleft, bosscancubuguright, bosscancubugutop, bosscancubugudown;
    Paint bossyazisi;
    int bossyazisix, bossyazisiy;
    long bossolumbaslangic, bossolumsuanki;
    boolean bossoldu, bossgeldi, bosscizsin;
    Vector<Integer> bossmermisi;
    Vector<Vector<Integer>> bossmermilistesi;
    int bossatespatenti;
    long bossreloadbaslangic, bossreloadsuan;
    int mermipatenti, patentsayaci;
    int mermisayisi;


    //hasar efekti
    Vector<Integer> yenihasar;
    Vector<Vector<Integer>> hasarlistesi;
    int hasarsayisi, bosskaybedilencan, kachasaruretildi, hasarkonumx[], hasarkonumy[];

    int baktigiderece, atesderecesi, suankiderece;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    private void bossMermisiUret(int mermipatenti){
        switch (mermipatenti) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    bossmermisi = new Vector<Integer>();
                    bossmermisi.add(0); //tur
                    bossmermisi.add(0); //frame
                    bossmermisi.add(bossx + 120); //x
                    bossmermisi.add(bossy + ((i - 1) * 50) + 420); //y
                    bossmermisi.add(dusmanatesresmi[0][0].getWidth()); //w
                    bossmermisi.add(dusmanatesresmi[0][0].getHeight()); //h
                    bossmermisi.add(-20); //hızx
                    bossmermisi.add(0); //hızy
                    bossmermilistesi.add(bossmermisi);
                    root.sesOynat(root.SE_ATES);
                    bossreloadbaslangic = System.currentTimeMillis();
                }
                break;

            case 1:
                mermisayisi += 1;
                bossmermisi = new Vector<Integer>();
                bossmermisi.add(0); //tur
                bossmermisi.add(0); //frame
                bossmermisi.add(bossx + 120); //x
                bossmermisi.add(bossy + ((mermisayisi / 10) * 50) + 420); //y
                bossmermisi.add(dusmanatesresmi[0][0].getWidth()); //w
                bossmermisi.add(dusmanatesresmi[0][0].getHeight()); //h
                bossmermisi.add(-20); //hızx
                bossmermisi.add((-mermisayisi + 10) * 2); //hızy
                bossmermilistesi.add(bossmermisi);
                root.sesOynat(root.SE_ATES);
                if (mermisayisi >= 20){
                    bossreloadbaslangic = System.currentTimeMillis();
                }
                break;
        }
    }

    private void hasarEfekti(){
        yenihasar = new Vector<Integer>();
        yenihasar.add(0); //frame
        yenihasar.add(bossx + hasarkonumx[kachasaruretildi]); //x
        yenihasar.add(bossy + hasarkonumy[kachasaruretildi]); //y
        hasarlistesi.add(yenihasar);
        if (kachasaruretildi < 4 - 1) kachasaruretildi += 1;
    }

    public void setup() {
        mermisayisi = 20;
        baktigiderece = -45;
        atesderecesi = 0;
        suankiderece = baktigiderece;
        gucbonususayaci = 0;
        gucbonusuaktif = false;
        LOGI("setup");
        arkaplanYukle();
        oyuncuYukle();
        atesbutonuYukle();
        asagibutonYukle();
        yukaributonYukle();
        puanYukle();
        dusmanYukle();
        bossYukle();
        oyAtesYukle();
        dusmanAtesYukle();
        genelAyarlar();
        ayarbutonYukle();
        pausemenuYukle();
        oyunsonuEkraniYukle();
        root.oyunmuzik.setLooping(true);    //müzik bitince tekrar çal
        root.gamemuzikOynat(true);
        patlamaYukle();
        canveenerjiYukle();
        bonusYukle();
    }

    private void bossYukle() {
        patentsayaci = 0;
        mermipatenti = 0;
        bossmermilistesi = new Vector<Vector<Integer>>();
        bossreloadbaslangic = System.currentTimeMillis();
        bossreloadsuan = System.currentTimeMillis();
        bosscizsin = false;
        hasarkonumx = new int[4];
        hasarkonumy = new int[4];

        hasarkonumx[0] = 320;
        hasarkonumx[1] = 420;
        hasarkonumx[2] = 50;
        hasarkonumx[3] = 170;

        hasarkonumy[0] = 285;
        hasarkonumy[1] = 370;
        hasarkonumy[2] = 220;
        hasarkonumy[3] = 350;

        kachasaruretildi = 0;
        bossgeldi = false;
        hasarsayisi = 0;
        hasarlistesi = new Vector<Vector<Integer>>();
        bossolumbaslangic = System.currentTimeMillis();
        bossoldu = false;
        hareketpatenti = 0;
        bossmaxframe = 3;
        bosssuankiframe = 0;
        bosshizx = 20;
        bosshizy = 5;
        boss1 = new Bitmap[bossmaxframe];
        for (int i = 0; i < 3; i++) {
            boss1[i] = Utils.loadImage(root, "resim/boss_1_0"+ (i) +".png");
        }
        bossx = 1920;
        bossy = 1080 / 2 - boss1[bosssuankiframe].getHeight() / 2;
        bosscaniYukle();
        bossyazisi = new Paint();
        bossyazisi.setARGB(255, 0, 175, 0);
        bossyazisi.setTextSize(60);
        bossyazisi.setTypeface(Typeface.DEFAULT_BOLD);
        bossyazisi.setTextAlign(Paint.Align.CENTER);
        bossyazisix = bosscancubuguleft + (bosscancubuguright - bosscancubuguleft) / 2;
        bossyazisiy = bosscancubugutop + (bosscancubugudown - bosscancubugutop) / 2 + 15;
    }

    private void bosscaniYukle(){
        bosskaybedilencan = 0;
        bosscani = Utils.loadImage(root, "can_kaplama.png");
        bosscancubugu = Utils.loadImage(root, "life_fill.png");
        bosscanileft = 700;
        bosscanitop = 50;
        bosscaniright = 1400;
        bosscanidown = 150;
        bosscankaynak = new Rect();
        bosscanhedef = new Rect();
        bosscankaynak.set(0, 0, bosscani.getWidth(), bosscani.getHeight());
        bosscanhedef.set(bosscanileft, bosscanitop, bosscaniright, bosscanidown);
        bosscancubuguleft = bosscanileft + 30;
        bosscancubugutop = bosscanitop + 15;
        bosscancubuguright = bosscaniright - 20;
        bosscancubugudown = bosscanidown - 15;
        bosscancubugukaynak = new Rect();
        bosscancubuguhedef = new Rect();
        bosscancubugukaynak.set(0, 0, bosscancubugu.getWidth(), bosscancubugu.getHeight());
        bosscancubuguhedef.set(bosscancubuguleft, bosscancubugutop, bosscancubuguright, bosscancubugudown);
        bossmaxcani = bosscancubuguright - bosscancubuguleft;
        bosssuankicani = 65;
        bosscanazaltmamiktari = bossmaxcani / bosssuankicani;
    }

    private  void bosscaniAzalt() {
        bosssuankicani -= 1;
        bosskaybedilencan += 1;
        if (bosskaybedilencan == 13) {
            bosskaybedilencan = 0;
            hasarEfekti();
        }
        bosscancubuguhedef.right -= bosscanazaltmamiktari;
        if (bosssuankicani == 0) {
            //bos öldü
            bossoldu = true;
            root.bosspatlama.prepare();
            root.bossbolumsonu.prepare();
            if (root.oyunmuzik.isPlaying()) {
                root.gamemuzikOynat(false);
            }
            oyundevamediyor = false;
            bossolumbaslangic = System.currentTimeMillis();
        }
    }

    private void bonusUret(int dusmanx, int dusmany){
        yenibonus = new Vector<Integer>();
        int tur = rasgelesayi.nextInt(bonuscesidi);
        yenibonus.add(tur); //tur
        yenibonus.add(0); //frame
        yenibonus.add(dusmanx); //x
        yenibonus.add(dusmany); //y
        yenibonus.add(bonusesyaresmi[tur][0].getWidth()); //w
        yenibonus.add(bonusesyaresmi[tur][0].getHeight()); //h
        yenibonus.add(-30); //hız
        bonuslistesi.add(yenibonus);
    }

    private void bonusYukle() {
        bonuslistesi = new Vector<Vector<Integer>>();
        bonuscesidi = 3;
        bonusmaxframe = new int[bonuscesidi];
        bonusmaxframe[0] = 1; //can
        bonusmaxframe[1] = 1; //enerji
        bonusmaxframe[2] = 4; //güç

        bonusesyaresmi = new Bitmap[bonuscesidi][4];
        bonusesyaresmi[0][0] = Utils.loadImage(root, "bonus/life_icon.png");
        bonusesyaresmi[1][0] = Utils.loadImage(root, "bonus/energy_icon.png");
        bonusesyaresmi[2][0] = Utils.loadImage(root, "bonus/power_1.png");
        bonusesyaresmi[2][1] = Utils.loadImage(root, "bonus/power_2.png");
        bonusesyaresmi[2][2] = Utils.loadImage(root, "bonus/power_3.png");
        bonusesyaresmi[2][3] = Utils.loadImage(root, "bonus/power_4.png");

    }

    private void canveenerjiYukle() {
        gosterge = Utils.loadImage(root, "can_kaplama.png");
        gostergex = 200;
        gostergey = 20;

        cancubugu = Utils.loadImage(root, "life_fill.png");
        cancubugux = gostergex;
        cancubuguy = gostergey;
        cankaynak = new Rect();
        canhedef = new Rect();
        cankaynak.set(0, 0, cancubugu.getWidth(), cancubugu.getHeight());
        canhedef.set(gostergex + 15, gostergey + 10,
                gostergex + 295, gostergey + 50);
        cancubugumax = canhedef.right - canhedef.left; //w
        canazaltmamiktari = cancubugumax / karaktermaxcan;

        enerjicubugu = Utils.loadImage(root, "energy_fill.png");
        gosterge2x = gostergex;
        gosterge2y = gostergey + gosterge.getHeight();
        enerjikaynak = new Rect();
        enerjihedef = new Rect();
        enerjikaynak.set(0, 0, enerjicubugu.getWidth(), enerjicubugu.getHeight());
        enerjihedef.set(gosterge2x + 15, gosterge2y + 10,
                gosterge2x + 295, gosterge2y + 50);
        enerjicubugumax = enerjihedef.right - enerjihedef.left;
        enerjiazaltmamiktari = enerjicubugumax / karaktermaxenerji;

    }

    private void canAzaltmaIslemi(){
        if (Math.abs(bonusbaslangic - bonussuan) < 10000)return;
        karaktersuankican -= 1;
        canhedef.right -= canazaltmamiktari;
        if (karaktersuankican <= 0){
            oyunuBitir();
        }
    }

    private void enerjiArtirmaIslemi(){
        int artacakenerji;
        artacakenerji = karaktermaxenerji * 40 / 140;
        //max enerji aşımı kontrolü
        Log.d("Artacak Enerji: ", "" + artacakenerji);
        Log.d("Şuanki Enerji: ", "" + karaktersuankienerji);
        Log.d("Enerji çubugu max: ", "" + enerjicubugumax);
        Log.d("Enerji hedef.right: ", "" + enerjihedef.right);
        if (karaktersuankienerji + artacakenerji >= karaktermaxenerji){
            karaktersuankienerji = karaktermaxenerji;
            enerjihedef.right = gosterge2x + 295;
            Log.d("Uğradı1", "1:   " + enerjihedef.right);
        }else{
            Log.d("Uğradı2", "2:   " + enerjihedef.right);
            karaktersuankienerji += artacakenerji;
            enerjihedef.right += enerjiazaltmamiktari * artacakenerji;
        }
    }

    private void canArtirmaIslemi() {
        int artacakcan;
        artacakcan = karaktermaxcan * 2 / 10;
        //max can aşımı kontrolü
        if(karaktersuankican + artacakcan > karaktermaxcan) {
            karaktersuankican = karaktermaxcan;
            canhedef.right = gostergex + 295;
        } else {
            karaktersuankican += artacakcan;
            canhedef.right += canazaltmamiktari * artacakcan;
        }
    }

    private void patlamaYukle() {
        patlamaframesayisi = 4;
        patlamaresimleri = new Bitmap[patlamaframesayisi];
        for (int i = 0; i < patlamaframesayisi; i++) {
            patlamaresimleri[i] = Utils.loadImage(root,
                    "Explode" + (i+1) + ".png");
        }
        patlamalistesi = new Vector<Vector<Integer>>();
    }

    private void patlamaUret(int patlamax, int patlamay){
        yenipatlama = new Vector<Integer>();
        yenipatlama.add(0); //frame
        yenipatlama.add(patlamax); //x
        yenipatlama.add(patlamay); //y
        patlamalistesi.add(yenipatlama);
        root.sesOynat(root.SE_PATLAMA);
    }

    private void oyunsonuEkraniYukle() {
        yildizsayisi = 4;
        yildizseviyesi = 0;
        oyunsonuekrani = new Bitmap[yildizsayisi];
        for (int i = 0; i < yildizsayisi; i++) {
            oyunsonuekrani[i] = Utils.loadImage(root, "oyunsonu" + (i) + ".png");
        }
        oyunsonuekranix = 1920 / 2 - oyunsonuekrani[0].getWidth() / 2;
        oyunsonuekraniy = 1080 / 2 - oyunsonuekrani[0].getHeight() / 2;

        osyenidenx = oyunsonuekranix + 150;
        osyenideny = oyunsonuekraniy + 480;
        osyenidenw = oyunsonuekranix + 220;
        osyenidenh = oyunsonuekraniy + 545;

        osilerix = oyunsonuekranix + 270;
        osileriy = oyunsonuekraniy + 480;
        osileriw = oyunsonuekranix + 340;
        osilerih = oyunsonuekraniy + 545;

        oyunsonuyazisi = new Paint();
        oyunsonuyazisi.setTextSize(40);
        oyunsonuyazisi.setTypeface(Typeface.DEFAULT_BOLD);
        oyunsonuyazisi.setTextAlign(Paint.Align.LEFT);
        oyunsonuyazisi.setARGB(255, 0, 200, 0);
        oyunsonuyazisix = oyunsonuekranix + 50;
        oyunsonuyazisiy = oyunsonuekraniy + 300;
    }

    private void pausemenuYukle() {
        pausemenu = Utils.loadImage(root, "pausemenu.png");
        pausemenux = 1920 / 2 - pausemenu.getWidth() / 2;
        pausemenuy = 1080 / 2 - pausemenu.getHeight() / 2;

        devamx = pausemenux + 60;
        devamy = pausemenuy + 72;
        devamw = pausemenux + 125;
        devamh = pausemenuy + 140;

        yenidenx = pausemenux + 160;
        yenideny = pausemenuy + 72;
        yenidenw = pausemenux + 230;
        yenidenh = pausemenuy + 140;

        menudonx = pausemenux + 265;
        menudony = pausemenuy + 72;
        menudonw = pausemenux + 335;
        menudonh = pausemenuy + 140;
    }

    private void ayarbutonYukle() {
        ayarbutonresmi = Utils.loadImage(root, "butonlar/ayar.png");
        ayarbutonx = 0 + 20;
        ayarbutony = 0 + 20;
    }

    private void genelAyarlar() {
        carpisan1 = new Rect();
        carpisan2 = new Rect();
        //Rasgele
        rasgelesayi = new Random();
        baslangiczamani = System.currentTimeMillis();//1000 = 1 saniye
        oyundevamediyor = true;
        oyunbitti = false;
    }



    public void update() {
        suankizaman = System.currentTimeMillis();
        bonussuan = System.currentTimeMillis();
        bossolumsuanki = System.currentTimeMillis();
        if (oyundevamediyor == false){
            bossOlduMu();
            return;
        }
        LOGI("update");
        Log.d("long değeri => ", "" + suankizaman);
        Log.d("int değeri => ", "" + (int) suankizaman);
        dusmanUret();
        oyuncuHareketi();
        dusmanHareketi();
        bossDurumu();
        oyuncuAtesHareketi();
        dusmanatesUretsinmi();
        bossatesUretsinmi();
        bossatesIlerlet();
        dusmanatesiIlerlet();
        arkaplankaydirmaMekanizmasi();
        carpismaMetodlari();
        bonusHareketi();
    }

    private void bossatesIlerlet() {
        for (int i = 0; i < bossmermilistesi.size(); i++) {
            if (bossmermilistesi.get(i).get(BOSSATES_X) > 0){

                bossmermilistesi.get(i).set(BOSSATES_X, bossmermilistesi.get(i).get(BOSSATES_X) +
                        bossmermilistesi.get(i).get(BOSSATES_HIZX));
                bossmermilistesi.get(i).set(BOSSATES_Y, bossmermilistesi.get(i).get(BOSSATES_Y) +
                        bossmermilistesi.get(i).get(BOSSATES_HIZY));

            }else{
                bossmermilistesi.remove(i);
            }
        }
    }

    private void bossatesUretsinmi() {
        if (bossgeldi == false) return;
        bossreloadsuan = System.currentTimeMillis();
        if (Math.abs(bossreloadsuan - bossreloadbaslangic) > 2000) {
            if (mermisayisi >= 20) {
                mermipatenti = 0;
                patentsayaci += 1;
                if (patentsayaci > 3) {
                    patentsayaci = 0;
                    mermipatenti = 1;
                    mermisayisi = 0;
                }
            }
            bossMermisiUret(mermipatenti);
        }
    }

    private void bossDurumu() {
        if (puan < 100){
            return;
        }else {
            bossgeldi = true;
            bosscizsin = true;
            switch (bosssuankicani) {
                case 50:
                    bossyazisi.setARGB(255, 150, 150, 0);
                    break;
                case 25:
                    bossyazisi.setARGB(255, 150, 0, 0);
                    break;
            }
            bossHareketi();
        }
    }


    private void bossOlduMu() {
        //sayac ++;
        //if(sayac < 300){}
        //if(sayac > 300 && sayac < 600){}
        //if(sayac > 600){}
        if (bossoldu == false){
            return;
        }
        if (Math.abs(bossolumsuanki - bossolumbaslangic) < 5000 && bossoldu == true){
            if (root.bosspatlama.isPlaying() == false){
                root.bosspatlama.start();
            }
        }

        if (Math.abs(bossolumsuanki - bossolumbaslangic) > 5000
                && Math.abs(bossolumsuanki - bossolumbaslangic) < 10000
                && bossoldu == true){
            if (root.bossbolumsonu.isPlaying() == false){
                if (root.bosspatlama.isPlaying() == true){
                    root.bosspatlama.stop();
                }
                bosscizsin = false;
                root.bossbolumsonu.start();
            }
        }

        if (Math.abs(bossolumsuanki - bossolumbaslangic) > 10000 && bossoldu == true){
            oyunbitti = true;
            root.maxpuanTut(puan);
        }
    }


    private void bossHareketi() {
        switch (hareketpatenti){
            case 0:
                if (bossx + boss1[bosssuankiframe].getWidth() - 300 >= 1920){
                    bossx -= bosshizx;
                }else{
                    hareketpatenti = 1;
                }
                break;
            case 1:
                bossy += bosshizy;
                hasarHareketi(bosshizy);
                if (bossy <= 0 || bossy + boss1[bosssuankiframe].getHeight() >= 1080){
                    bosshizy *= (-1);
                }
                break;
        }
    }

    private void hasarHareketi(int bosshizy) {
        for (int i = 0; i < hasarlistesi.size(); i++) {
            hasarlistesi.get(i).set(BOSSHASAR_Y,
                    hasarlistesi.get(i).get(BOSSHASAR_Y) + bosshizy);
        }
    }

    private void bonusHareketi() {
        for (int bh_i = 0; bh_i < bonuslistesi.size(); bh_i++) {
            if (bonuslistesi.get(bh_i).get(BONUS_X) > 0){
                bonuslistesi.get(bh_i).set(BONUS_X,
                        bonuslistesi.get(bh_i).get(BONUS_X) + bonuslistesi.get(bh_i).get(BONUS_HIZ));
            }else {
                bonuslistesi.remove(bh_i);
            }
        }
    }

    private void carpismaMetodlari() {
        oyuncuMermisiDusmanMermisineCarptiMi();
        oyuncuDusmanaCarptiMi();
        oyuncuMermisiDusmanaCarptiMi();
        dusmanMermisiOyuncuyaCarptiMi();
        bonusEsyaKontrolu();
        oyuncuMermisiBossaCarptiMi();
        bossmermisiOyuncuyaCarptiMi();
        bossmermisiOyuncuMermisineCarptiMi();
    }

    private void bossmermisiOyuncuMermisineCarptiMi() {
        for (int o_mermisi = 0; o_mermisi < oyateslistesi.size(); o_mermisi++) {
            carpisan1.set(oyateslistesi.get(o_mermisi).get(OATES_X),
                    oyateslistesi.get(o_mermisi).get(OATES_Y),
                    oyateslistesi.get(o_mermisi).get(OATES_X) + oyateslistesi.get(o_mermisi).get(OATES_W),
                    oyateslistesi.get(o_mermisi).get(OATES_Y) + oyateslistesi.get(o_mermisi).get(OATES_H));
            for (int b_mermisi = 0; b_mermisi < bossmermilistesi.size(); b_mermisi++) {
                carpisan2.set(bossmermilistesi.get(b_mermisi).get(BOSSATES_X),
                        bossmermilistesi.get(b_mermisi).get(BOSSATES_Y),
                        bossmermilistesi.get(b_mermisi).get(BOSSATES_X) + bossmermilistesi.get(b_mermisi).get(BOSSATES_W),
                        bossmermilistesi.get(b_mermisi).get(BOSSATES_Y) + bossmermilistesi.get(b_mermisi).get(BOSSATES_H));
                if (carpisan1.intersect(carpisan2)){
                    patlamax = bossmermilistesi.get(b_mermisi).get(BOSSATES_X);
                    patlamay = bossmermilistesi.get(b_mermisi).get(BOSSATES_Y);
                    patlamaUret(patlamax, patlamay);
                    root.sesOynat(root.SE_PATLAMA);
                    bossmermilistesi.remove(b_mermisi);
                    patlamax = oyateslistesi.get(o_mermisi).get(OATES_X);
                    patlamay = oyateslistesi.get(o_mermisi).get(OATES_Y);
                    patlamaUret(patlamax, patlamay);
                    root.sesOynat(root.SE_PATLAMA);
                    oyateslistesi.remove(o_mermisi);
                }
            }
        }
    }

    private void bossmermisiOyuncuyaCarptiMi() {
        carpisan1.set(oyuncux, oyuncuy, oyuncux + oyuncuresmi[karakterdurum][karakterframe].getWidth(),
                oyuncuy + oyuncuresmi[karakterdurum][karakterframe].getHeight());
        for (int i = 0; i < bossmermilistesi.size(); i++) {
            carpisan2.set(bossmermilistesi.get(i).get(BOSSATES_X), bossmermilistesi.get(i).get(BOSSATES_Y),
                    bossmermilistesi.get(i).get(BOSSATES_X) + bossmermilistesi.get(i).get(BOSSATES_W),
                    bossmermilistesi.get(i).get(BOSSATES_Y) + bossmermilistesi.get(i).get(BOSSATES_H));
            if (carpisan1.intersect(carpisan2)){
                patlamax = bossmermilistesi.get(i).get(BOSSATES_X);
                patlamay = bossmermilistesi.get(i).get(BOSSATES_Y);
                patlamaUret(patlamax, patlamay);
                root.sesOynat(root.SE_PATLAMA);
                bossmermilistesi.remove(i);
                canAzaltmaIslemi();
            }
        }
    }

    private void oyuncuMermisiBossaCarptiMi() {
        if (bossgeldi == false) return;
        //boss
        carpisan1.set(bossx, bossy, bossx + boss1[bosssuankiframe].getWidth(),
                bossy + boss1[bosssuankiframe].getHeight());
        for (int i = 0; i < oyateslistesi.size(); i++){
            carpisan2.set(oyateslistesi.get(i).get(OATES_X),
                    oyateslistesi.get(i).get(OATES_Y),
                    oyateslistesi.get(i).get(OATES_X)
                            + oyateslistesi.get(i).get(OATES_W),
                    oyateslistesi.get(i).get(OATES_Y)
                            + oyateslistesi.get(i).get(OATES_H));
            if (carpisan2.intersect(carpisan1)){
                patlamax = oyateslistesi.get(i).get(OATES_X);
                patlamay = oyateslistesi.get(i).get(OATES_Y);
                patlamaUret(patlamax, patlamay);
                root.sesOynat(root.SE_PATLAMA);
                bosscaniAzalt();
                oyateslistesi.remove(i);
                puan += 5;
            }
        }
    }

    private void bonusEsyaKontrolu() {
        for (int bek_i = 0; bek_i < bonuslistesi.size(); bek_i++) {
            //karakterin alanı
            carpisan1.set(oyuncux, oyuncuy,
                    oyuncux + oyuncuresmi[karakterdurum][karakterframe].getWidth(),
                    oyuncuy + oyuncuresmi[karakterdurum][karakterframe].getHeight());
            //bonus eşya alanı
            carpisan2.set(bonuslistesi.get(bek_i).get(BONUS_X),
                          bonuslistesi.get(bek_i).get(BONUS_Y),
                          bonuslistesi.get(bek_i).get(BONUS_X)
                        + bonuslistesi.get(bek_i).get(BONUS_W),
                          bonuslistesi.get(bek_i).get(BONUS_Y)
                        + bonuslistesi.get(bek_i).get(BONUS_H));
            //Kontrol
            if(carpisan1.intersect(carpisan2) == true) {
                bonusEtkileri(bonuslistesi.get(bek_i).get(BONUS_TUR));
                bonuslistesi.remove(bek_i);
            }
        }
    }

    private void dusmanMermisiOyuncuyaCarptiMi() {
        //oyuncu alanı
        carpisan1.set(oyuncux, oyuncuy,
                oyuncux + oyuncuresmi[karakterdurum][karakterframe].getWidth(),
                oyuncuy + oyuncuresmi[karakterdurum][karakterframe].getHeight());

        //düşman mermi alanı
        for (int dmoc_dusman = 0; dmoc_dusman < dusmanateslistesi.size(); dmoc_dusman++) {
            //merminin alanı
            carpisan2.set(dusmanateslistesi.get(dmoc_dusman).get(DATES_X), //sol köşe
                    dusmanateslistesi.get(dmoc_dusman).get(DATES_Y), //üst köşe
                    dusmanateslistesi.get(dmoc_dusman).get(DATES_X)
            + dusmanateslistesi.get(dmoc_dusman).get(DATES_W),
                    dusmanateslistesi.get(dmoc_dusman).get(DATES_Y)
            + dusmanateslistesi.get(dmoc_dusman).get(DATES_H));

            //iki nesneyi karsılastıracagız
            if (carpisan1.intersect(carpisan2) == true){
                //oyuncunun canı azalacak veya oyun bitecek
                patlamax = dusmanateslistesi.get(dmoc_dusman).get(DATES_X);
                patlamay = dusmanateslistesi.get(dmoc_dusman).get(DATES_Y);
                patlamaUret(patlamax, patlamay);
                dusmanateslistesi.remove(dmoc_dusman);
                canAzaltmaIslemi();

                break;
            }
        }
    }

    private void oyunuBitir() {
        oyunbitti = true;
        root.maxpuanTut(puan);
        oyundevamediyor = false;

        if (puan / 100 > 3) {
            yildizseviyesi = 3;
        } else {
            yildizseviyesi = puan / 100;
        }
    }

    private void oyuncuMermisiDusmanaCarptiMi() {
        for (int omdc_oyuncumermisi = 0; omdc_oyuncumermisi < oyateslistesi.size(); omdc_oyuncumermisi++) {
            carpisan1.set(oyateslistesi.get(omdc_oyuncumermisi).get(OATES_X),
                    oyateslistesi.get(omdc_oyuncumermisi).get(OATES_Y),
                    oyateslistesi.get(omdc_oyuncumermisi).get(OATES_X)
            + oyateslistesi.get(omdc_oyuncumermisi).get(OATES_W),
                    oyateslistesi.get(omdc_oyuncumermisi).get(OATES_Y)
            + oyateslistesi.get(omdc_oyuncumermisi).get(OATES_H));
            for (int omdc_dusman = 0; omdc_dusman < dusmanlistesi.size(); omdc_dusman++) {
                carpisan2.set(dusmanlistesi.get(omdc_dusman).get(DUSMAN_X),
                        dusmanlistesi.get(omdc_dusman).get(DUSMAN_Y),
                        dusmanlistesi.get(omdc_dusman).get(DUSMAN_X)
                + dusmanresmi[dusmanlistesi.get(omdc_dusman).get(DUSMAN_TUR)]
                                [dusmanlistesi.get(omdc_dusman).get(DUSMAN_FRAME)].getWidth(),
                        dusmanlistesi.get(omdc_dusman).get(DUSMAN_Y)
                + dusmanresmi[dusmanlistesi.get(omdc_dusman).get(DUSMAN_TUR)]
                                [dusmanlistesi.get(omdc_dusman).get(DUSMAN_FRAME)].getHeight());
                //iki nesne carpıstımı
                if (carpisan1.intersect(carpisan2) == true){
                    oyateslistesi.remove(omdc_oyuncumermisi);
                    puan += dusmanpuanlari[dusmanlistesi.get(omdc_dusman).get(DUSMAN_TUR)];
                    patlamax = dusmanlistesi.get(omdc_dusman).get(DUSMAN_X);
                    patlamay = dusmanlistesi.get(omdc_dusman).get(DUSMAN_Y);
                    patlamaUret(patlamax, patlamay);
                    if (rasgelesayi.nextInt(100) <= 20){
                        bonusUret(patlamax, patlamay);
                    }
                    dusmanlistesi.remove(omdc_dusman);
                    break;
                }
            }
        }
    }

    private void bonusEtkileri(int bonustipi) {
        switch(bonustipi) {
            case 0:
                //Can bonusu
                canArtirmaIslemi();
                break;
            case 1:
                //Enerji bonusu
                enerjiArtirmaIslemi();
                break;
            case 2:
                //Güç bonusu
                gucBonusuIslemi();
                break;
            default:
                return;
        }
    }

    private void gucBonusuIslemi() {
        bonusbaslangic = System.currentTimeMillis();
        gucbonusuaktif = true;
    }





    private void oyuncuDusmanaCarptiMi() {
        //karakterin alanı
        carpisan1.set(oyuncux, oyuncuy,
                oyuncux + oyuncuresmi[karakterdurum][karakterframe].getWidth(),
                oyuncuy + oyuncuresmi[karakterdurum][karakterframe].getHeight());

        //dusmanın alanı
        for (int odcm_dusman = 0; odcm_dusman < dusmanlistesi.size(); odcm_dusman++) {
            carpisan2.set(dusmanlistesi.get(odcm_dusman).get(DUSMAN_X),
                          dusmanlistesi.get(odcm_dusman).get(DUSMAN_Y),
                     dusmanlistesi.get(odcm_dusman).get(DUSMAN_X)
                          + dusmanresmi[dusmanlistesi.get(odcm_dusman).get(DUSMAN_TUR)]
                          [dusmanlistesi.get(odcm_dusman).get(DUSMAN_FRAME)].getWidth(),
                    dusmanlistesi.get(odcm_dusman).get(DUSMAN_Y)
                            + dusmanresmi[dusmanlistesi.get(odcm_dusman).get(DUSMAN_TUR)]
                            [dusmanlistesi.get(odcm_dusman).get(DUSMAN_FRAME)].getHeight());
            if (carpisan1.intersect(carpisan2) == true){
                puan += dusmanpuanlari[dusmanlistesi.get(odcm_dusman).get(DUSMAN_TUR)];
                patlamax = dusmanlistesi.get(odcm_dusman).get(DUSMAN_X);
                patlamay = dusmanlistesi.get(odcm_dusman).get(DUSMAN_Y);
                patlamaUret(patlamax, patlamay);
                if (rasgelesayi.nextInt(100) <= 20){
                    bonusUret(patlamax, patlamay);
                }
                dusmanlistesi.remove(odcm_dusman);
                canAzaltmaIslemi();
                break;
            }
        }
    }

    private void oyuncuMermisiDusmanMermisineCarptiMi() {
        //oyateslistesi.size() => kac mermi var ise o miktar kadar aynı işi yap
        for (int omdc_oyuncu = 0; omdc_oyuncu < oyateslistesi.size() ; omdc_oyuncu++) {
            for (int omdc_dusman = 0; omdc_dusman < dusmanateslistesi.size(); omdc_dusman++) {
                if (oyateslistesi.get(omdc_oyuncu).get(OATES_X) //x1
                        < dusmanateslistesi.get(omdc_dusman).get(DATES_X) //a2
                        + dusmanateslistesi.get(omdc_dusman).get(DATES_W) //a2
                && oyateslistesi.get(omdc_oyuncu).get(OATES_X) //x2
                + oyateslistesi.get(omdc_oyuncu).get(OATES_W) //x2
                > dusmanateslistesi.get(omdc_dusman).get(DATES_X) //a1
                && oyateslistesi.get(omdc_oyuncu).get(OATES_Y) //y1
                < dusmanateslistesi.get(omdc_dusman).get(DATES_Y) //b2
                + dusmanateslistesi.get(omdc_dusman).get(DATES_H) //b2
                && oyateslistesi.get(omdc_oyuncu).get(OATES_Y) //y2
                + oyateslistesi.get(omdc_oyuncu).get(OATES_H) //y2
                > dusmanateslistesi.get(omdc_dusman).get(DATES_Y)){ //b1
                    //carpısan mermiler silindi
                    patlamax = oyateslistesi.get(omdc_oyuncu).get(OATES_X);
                    patlamay = oyateslistesi.get(omdc_oyuncu).get(OATES_Y);
                    patlamaUret(patlamax, patlamay);
                    oyateslistesi.remove(omdc_oyuncu);
                    patlamax = dusmanateslistesi.get(omdc_dusman).get(DATES_X);
                    patlamay = dusmanateslistesi.get(omdc_dusman).get(DATES_Y);
                    patlamaUret(patlamax, patlamay);
                    dusmanateslistesi.remove(omdc_dusman);
                    break;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        LOGI("draw");
        this.canvas = canvas;
        canvas.scale(getWidth() / 1920.0f, getHeight() / 1080.0f);
        arkaplanCiz();
        bonusCiz();
        ayarbutonCiz();
        puanCiz();
        dusmanCiz();
        bossCiz();
        bossmermisiCiz();
        oyuncuCiz();
        oyuncuAtesCiz();
        dusmanatesCiz();
        bosscaniCiz();
        patlamaCiz();
        arayuzButonlari();
        //nesneparametreleriCiz();
        pausemenuCiz();
        oyunsonuEkraniCiz();
    }

    private void bossmermisiCiz() {
        for (int i = 0; i < bossmermilistesi.size(); i++) {
            canvas.drawBitmap(dusmanatesresmi[bossmermilistesi.get(i).get(BOSSATES_TUR)][bossmermilistesi.get(i).get(BOSSATES_FRAME)],
                    bossmermilistesi.get(i).get(BOSSATES_X), bossmermilistesi.get(i).get(BOSSATES_Y), null);
        }
    }

    private void bosscaniCiz() {
        if (bosscizsin == false) return;
        canvas.drawBitmap(bosscani, bosscankaynak, bosscanhedef, null);
        canvas.drawBitmap(bosscancubugu, bosscancubugukaynak, bosscancubuguhedef, null);
        canvas.drawText("Kara Şimşek", bossyazisix, bossyazisiy, bossyazisi);
    }

    private void bossCiz() {
        if (bosscizsin == false) return;
        canvas.drawBitmap(boss1[bosssuankiframe], bossx, bossy, null);
        //frame+1 işlemi
        if (oyundevamediyor == true && oyunbitti == false) {
            if (bosssuankiframe < bossmaxframe - 1) {
                bosssuankiframe += 1;
            } else {
                bosssuankiframe = 0;
            }
        }
        hasarCiz();
    }

    private void hasarCiz(){
        //hasar efekti
        for (int i = 0; i < hasarlistesi.size(); i++) {
            canvas.drawBitmap(patlamaresimleri[hasarlistesi.get(i).get(BOSSHASAR_FRAME)],
                    hasarlistesi.get(i).get(BOSSHASAR_X), hasarlistesi.get(i).get(BOSSHASAR_Y),
                    null);
            //frame + 1 işlemi
            if (oyundevamediyor == true || oyunbitti == true) {
                if (hasarlistesi.get(i).get(BOSSHASAR_FRAME) < patlamaframesayisi - 1) {
                    hasarlistesi.get(i).set(BOSSHASAR_FRAME, hasarlistesi.get(i).get(BOSSHASAR_FRAME) + 1);
                } else {
                    hasarlistesi.get(i).set(BOSSHASAR_FRAME, 0);
                }
            }
        }
    }

    private void bonusCiz() {
        for (int i = 0; i < bonuslistesi.size(); i++) {
            canvas.drawBitmap(bonusesyaresmi[bonuslistesi.get(i).get(BONUS_TUR)][bonuslistesi.get(i).get(BONUS_FRAME)],
                    bonuslistesi.get(i).get(BONUS_X), bonuslistesi.get(i).get(BONUS_Y),null);
            //frame + 1 işlemi
            if (bonuslistesi.get(i).get(BONUS_FRAME) < bonusmaxframe[bonuslistesi.get(i).get(BONUS_TUR)] - 1){
                bonuslistesi.get(i).set(BONUS_FRAME,
                        bonuslistesi.get(i).get(BONUS_FRAME) + 1);
            }else{
                bonuslistesi.get(i).set(BONUS_FRAME, 0);
            }
        }
    }

    private void canveenerjiCiz() {
        canvas.drawBitmap(gosterge, gostergex, gostergey, null);
        canvas.drawBitmap(cancubugu, cankaynak, canhedef, null);
        canvas.drawBitmap(gosterge, gosterge2x, gosterge2y,null);
        canvas.drawBitmap(enerjicubugu, enerjikaynak, enerjihedef, null);

    }

    private void enerjiAzaltmaIslemi(){
        karaktersuankienerji -= 1;
        enerjihedef.right -= enerjiazaltmamiktari;
        if (karaktersuankienerji > 0){
            karakteratesetsin = true;
        }else{
            karakteratesetsin = false;
        }
    }

    private void patlamaCiz() {
        for (int i = 0; i < patlamalistesi.size(); i++) {
            canvas.drawBitmap(patlamaresimleri[patlamalistesi.get(i).get(PATLAMA_FRAME)],
                    patlamalistesi.get(i).get(PATLAMA_X), patlamalistesi.get(i).get(PATLAMA_Y),
                    null);
            //frame +1 işlemi
            if (patlamalistesi.get(i).get(PATLAMA_FRAME) < patlamaframesayisi - 1) {
                patlamalistesi.get(i).set(PATLAMA_FRAME,
                        patlamalistesi.get(i).get(PATLAMA_FRAME) + 1);
            }else{
                patlamalistesi.remove(i);
            }
        }
    }

    private void oyunsonuEkraniCiz() {
        if (oyundevamediyor == false && oyunbitti == true){
            canvas.drawBitmap(oyunsonuekrani[yildizseviyesi], oyunsonuekranix, oyunsonuekraniy, null);
            canvas.drawText("--------------------------", oyunsonuyazisix, oyunsonuyazisiy - 50, oyunsonuyazisi);
            canvas.drawText("Toplam Puan: " + puan, oyunsonuyazisix, oyunsonuyazisiy, oyunsonuyazisi);
            canvas.drawText("--------------------------", oyunsonuyazisix, oyunsonuyazisiy + 50, oyunsonuyazisi);
            canvas.drawText("Max Puan: " + root.maxpuan, oyunsonuyazisix, oyunsonuyazisiy + 100, oyunsonuyazisi);
        }
    }

    private void pausemenuCiz() {
        if (oyundevamediyor == false && oyunbitti == false){
            canvas.drawBitmap(pausemenu, pausemenux, pausemenuy, null);
        }
    }

    private void ayarbutonCiz() {
        canvas.drawBitmap(ayarbutonresmi, ayarbutonx, ayarbutony, null);
    }



    //-----Düşman Bölümü-----
    private void dusmanAtesUret(int secilendusman) {
        dusmanatesi = new Vector<Integer>();
        dusmanatesi.add(dusmanlistesi.get(secilendusman).get(DUSMAN_MERMI)); //tur
        dusmanatesi.add(0); //frame
        dusmanatesi.add(dusmanlistesi.get(secilendusman).get(DUSMAN_X)); //x
        dusmanatesi.add(dusmanlistesi.get(secilendusman).get(DUSMAN_Y) + 55); //y
        dusmanatesi.add(dusmanatesresmi[dusmanlistesi.get(secilendusman).get(DUSMAN_MERMI)][0].getWidth()); //w
        dusmanatesi.add(dusmanatesresmi[dusmanlistesi.get(secilendusman).get(DUSMAN_MERMI)][0].getHeight()); //h
        dusmanatesi.add(-30); //hiz
        dusmanateslistesi.add(dusmanatesi);
        dusmanreloadsureleri.set(secilendusman, System.currentTimeMillis());
        root.sesOynat(root.SE_ATES);
    }


    private void dusmanAtesYukle() {
        dusmanateslistesi = new Vector<Vector<Integer>>();
        mermicesitleri = 2;
        mermimaxframe = new int[mermicesitleri];
        mermimaxframe[0] = 1;
        mermimaxframe[1] = 1;
        dusmanatesresmi = new Bitmap[mermicesitleri][1];
        dusmanatesresmi[0][0] = Utils.loadImage(root, "ufo_alien_shot_1.png");
        dusmanatesresmi[1][0] = Utils.loadImage(root, "suit_alien_shot_1.png");
    }

    private void dusmanatesUretsinmi() {
        for (int dau_i = 0; dau_i < dusmanlistesi.size(); dau_i++) {
            if (Math.abs(suankizaman - dusmanreloadsureleri.get(dau_i)) > 1000) {
                dusmanAtesUret(dau_i);
            }
        }
    }




    private void dusmanHareketi() {
        for (int dh_i = 0; dh_i < dusmanlistesi.size(); dh_i++) {
            if (dusmanlistesi.get(dh_i).get(DUSMAN_X)
                    > -dusmanresmi[dusmanlistesi.get(dh_i).get(DUSMAN_TUR)]
                    [dusmanlistesi.get(dh_i).get(DUSMAN_FRAME)].getWidth()) {
                // X = HIZ DEĞİL!!!  X = X + HIZ
                dusmanlistesi.get(dh_i).set(DUSMAN_X,
                        dusmanlistesi.get(dh_i).get(DUSMAN_X)
                                + dusmanlistesi.get(dh_i).get(DUSMAN_HIZX));
            }else {
                dusmanlistesi.remove(dh_i);
                dusmanreloadsureleri.remove(dh_i);
            }
            //y ekseni hareketi
            dusmanlistesi.get(dh_i).set(DUSMAN_Y,
                    dusmanlistesi.get(dh_i).get(DUSMAN_Y)
                            +dusmanlistesi.get(dh_i).get(DUSMAN_HIZY));
            //üst köşeye çarptı
            if (dusmanlistesi.get(dh_i).get(DUSMAN_Y) <= 0 || dusmanlistesi.get(dh_i).get(DUSMAN_Y) +
                    dusmanresmi[dusmanlistesi.get(dh_i).get(DUSMAN_TUR)][0].getHeight() >= 1080){

               dusmanlistesi.get(dh_i).set(DUSMAN_HIZY,
                       dusmanlistesi.get(dh_i).get(DUSMAN_HIZY) * (-1));
               suankiderece *= (-1);

            }
        }
    }

    private void dusmanatesCiz() {
        for (int dac_i = 0; dac_i < dusmanateslistesi.size(); dac_i++) {
            canvas.drawBitmap(dusmanatesresmi[dusmanateslistesi.get(dac_i).get(DATES_TUR)]
                            [dusmanateslistesi.get(dac_i).get(DATES_FRAME)],
                    dusmanateslistesi.get(dac_i).get(DATES_X),
                    dusmanateslistesi.get(dac_i).get(DATES_Y),
                    null);
        }
    }

    private void dusmanCiz() {
        for (int dc_i = 0; dc_i < dusmanlistesi.size(); dc_i++) {
            canvas.drawBitmap(dusmanresmi[dusmanlistesi.get(dc_i).get(DUSMAN_TUR)]
                            [dusmanlistesi.get(dc_i).get(DUSMAN_FRAME)],
                    dusmanlistesi.get(dc_i).get(DUSMAN_X),
                    dusmanlistesi.get(dc_i).get(DUSMAN_Y),
                    null);

            // Frame + 1 işlemi
            if (oyundevamediyor == true && oyunbitti == false) {
                if (dusmanlistesi.get(dc_i).get(DUSMAN_FRAME)
                        < dusmanmaxframe[dusmanlistesi.get(dc_i).get(DUSMAN_TUR)] - 1) {
                    dusmanlistesi.get(dc_i).set(DUSMAN_FRAME,
                            dusmanlistesi.get(dc_i).get(DUSMAN_FRAME) + 1);
                } else {
                    dusmanlistesi.get(dc_i).set(DUSMAN_FRAME, 0);
                }
            }
        }
    }

    private void dusmanYukle() {
        dusmancesitleri = 3;
        //dusmanların puanları
        dusmanpuanlari = new int[dusmancesitleri];
        dusmanpuanlari[0] = 5;
        dusmanpuanlari[1] = 10;
        dusmanpuanlari[2] = 20;
        dusmanlistesi = new Vector<Vector<Integer>>();
        dusmanreloadsureleri = new Vector<Long>();
        dusmanmaxframe = new int[dusmancesitleri];
        dusmanmaxframe[0] = 2;
        dusmanmaxframe[1] = 2;
        dusmanmaxframe[2] = 2;

        //kullanacagı mermi türü
        dusmanmermituru = new int[dusmancesitleri];
        dusmanmermituru[DUSMAN_RED] = 0;
        dusmanmermituru[DUSMAN_YELLOW] = 1;
        dusmanmermituru[DUSMAN_GREEN] = 0;


        dusmanresmi = new Bitmap[dusmancesitleri][2];
        for (int ufo = 0; ufo < dusmancesitleri; ufo++) {
            for (int frame = 0; frame < dusmanmaxframe[ufo]; frame++) {
                dusmanresmi[ufo][frame] =
                        Utils.loadImage(root,
                                "dusmanlar/" + (ufo) + "/" + (frame) + "0.png");
            }
        }
    }

    private void dusmanatesiIlerlet() {
        for (int dai_i = 0; dai_i < dusmanateslistesi.size(); dai_i++) {
            if (dusmanateslistesi.get(dai_i).get(DATES_X) > 0) {
                dusmanateslistesi.get(dai_i).set(DATES_X,
                        dusmanateslistesi.get(dai_i).get(DATES_X)
                                + dusmanateslistesi.get(dai_i).get(DATES_HIZ));
            } else {
                dusmanateslistesi.remove(dai_i);
            }
        }
    }

    private void dusmanUret() {
        if (Math.abs(baslangiczamani - suankizaman) < 2000 || bossgeldi == true) return;
        int tur;
        tur = rasgelesayi.nextInt(dusmancesitleri);
        yenidusman = new Vector<Integer>();
        yenidusman.add(tur);//Resim(tür)  index:0 = DUSMAN_TUR
        yenidusman.add(0);//Frame       index:1 = DUSMAN_FRAME
        yenidusman.add(1920);//x        index:2 = DUSMAN_X
        yenidusman.add(rasgelesayi.nextInt(1080 - dusmanresmi[tur][0].getHeight())); //y        index:3 = DUSMAN_Y
        yenidusman.add(-10); //hız      index:4 = DUSMAN_HIZX
        yenidusman.add(10);  //hızy     index:5 = DUSMAN_HIZY
        yenidusman.add(dusmanmermituru[tur]); //mermi türü index:6 = DUSMAN_MERMI
        dusmanreloadsureleri.add(System.currentTimeMillis());

        dusmanlistesi.add(yenidusman); // Düşman listeye eklendi.
        baslangiczamani = System.currentTimeMillis();

    }
    //-----Düşman Bölümü Bitişi-----





    //-----Karakter Bölümü-----
    private void oyAtesYukle() {
        oyatesframesayisi = 3;
        oyateslistesi = new Vector<Vector<Integer>>();
        oyuncuatesresmi = new Bitmap[oyatesframesayisi];
        for (int oay_frame = 0; oay_frame < oyatesframesayisi; oay_frame++) {
            oyuncuatesresmi[oay_frame] = Utils.loadImage(root,
                    "karakter/1/fire" + (oay_frame + 1) + ".png");
        }

    }

    private void oyuncuAtesUret(int oyx, int oyy) {
        oyuncuates = new Vector<Integer>();
        oyuncuates.add(0); //Frame
        oyuncuates.add(oyx + oyuncuresmi[karakterdurum][karakterframe].getWidth()); // x
        oyuncuates.add(oyy + 40); // y
        oyuncuates.add(oyuncuatesresmi[0].getWidth()); // w
        oyuncuates.add(oyuncuatesresmi[0].getHeight()); // h
        oyuncuates.add(30); // hız
        oyateslistesi.add(oyuncuates); // listeye eklendi.
        //Karakter ateş etme durumunda
        karakterdurum = KDURUM_ATES;
        karakterframe = 0;
        root.sesOynat(root.SE_ATES);
    }

    private void oyuncuYukle() {
        karakteratesetsin = true;
        karakterdurum = 0;
        karaktermaxcan = 10;
        karaktersuankican = 10;
        karaktermaxenerji = 140;
        karaktersuankienerji = 140;
        karakterdurumsayisi = 4;
        karaktermaxframe = new int[karakterdurumsayisi];
        karaktermaxframe[KDURUM_NORMAL] = 2; //normal durum
        karaktermaxframe[KDURUM_ATES] = 2; //ateş
        karaktermaxframe[KDURUM_HASAR] = 1; //hasar durumu
        karaktermaxframe[KDURUM_OLUM] = 3; //öldüğü durum
        oyuncuresmi = new Bitmap[karakterdurumsayisi][3];

        for (int durum = 0; durum < karakterdurumsayisi; durum++) {
            for (int frame = 0; frame < karaktermaxframe[durum]; frame++) {
                oyuncuresmi[durum][frame] =
                        Utils.loadImage(root,
                                "karakter/" + (durum) + "/" + (frame + 1) + ".png");
            }

        }
        asagigidecek = false;
        yukarigidecek = false;
        oyuncux = 0;
        oyuncuy = 1080 / 2 - oyuncuresmi[karakterdurum][karakterframe].getHeight() / 2;
        oyuncuhiz = 20;
    }

    private void oyuncuAtesHareketi() {
        for (int oah_i = 0; oah_i < oyateslistesi.size(); oah_i++) {
            if (oyateslistesi.get(oah_i).get(OATES_X) < 1920) {
                //x = x + hız;
                oyateslistesi.get(oah_i).set(OATES_X, // OATES_X(230) = 200 + 30;
                        oyateslistesi.get(oah_i).get(OATES_X)
                                + oyateslistesi.get(oah_i).get(OATES_HIZ));
            } else {
                oyateslistesi.remove(oah_i);
            }
        }
    }

    private void oyuncuAtesCiz() {
        for (int oac_i = 0; oac_i < oyateslistesi.size(); oac_i++) {
            //çizdirme işlemi
            canvas.drawBitmap(oyuncuatesresmi[oyateslistesi.get(oac_i).get(OATES_FRAME)],
                    oyateslistesi.get(oac_i).get(OATES_X),
                    oyateslistesi.get(oac_i).get(OATES_Y), null);
        }
    }

    private void oyuncuHareketi() {
        //yukarı hareket
        if (yukarigidecek == true
                && oyuncuy > 0) {
            oyuncuy -= oyuncuhiz;
        }

        //asagı hareket
        if (asagigidecek == true
                && oyuncuy + oyuncuresmi[karakterdurum][karakterframe].getHeight() < 1080) {
            oyuncuy += oyuncuhiz;
        }

    }

    private void oyuncuCiz() {
        if (gucbonusuaktif == false){
            canvas.drawBitmap(oyuncuresmi[karakterdurum][karakterframe], oyuncux, oyuncuy, null);
        }else{
            if (gucbonususayaci % 2 == 0){
                canvas.drawBitmap(oyuncuresmi[karakterdurum][karakterframe], oyuncux, oyuncuy, null);
            }
            gucbonususayaci += 1;
            if (Math.abs(bonussuan - bonusbaslangic) > 10000){
                gucbonusuaktif = false;
                gucbonususayaci = 0;
            }
        }
        //Sonraki Frame hesapla
        if (oyundevamediyor == true && oyunbitti == false) {
            if (karakterframe < karaktermaxframe[karakterdurum] - 1) {
                karakterframe += 1;
            } else {
                karakterframe = 0;
                if (karakterdurum != KDURUM_NORMAL) karakterdurum = KDURUM_NORMAL;
            }
        }
    }
    //-----Karaket Bölümü Bitişi-----





    //-----Arayüz Bölümü-----
    private void puanYukle() {
        puanrengi = new Paint();
        puanrengi.setTextSize(30);//Punto
        puanrengi.setTextAlign(Paint.Align.LEFT);//Sola hizala(Hiza)
        puanrengi.setTypeface(Typeface.DEFAULT_BOLD); //Kalın yazı
        puanrengi.setARGB(255, 225, 255, 50); //A:saydamlık(255 kapalı), R:KIRMIZI, G:YEŞİL, B:MAVİ
        //Puan çerçevesi yükle
        puancerceve = Utils.loadImage(root, "puanpanel.png");
        puancercevex = 1920 - puancerceve.getWidth() - 30;
        puancercevey = 30;
        //puan başlangıç değerleri
        puan = 0;
        puanx = puancercevex + 80;
        puany = puancercevey + 50;
    }

    private void asagibutonYukle() {
        asagibuton = Utils.loadImage(root,
                "butonlar/asagi_ok.png");
        asagibutonx = 30;
        asagibutony = 1080 - asagibuton.getHeight() - 30;
    }

    private void yukaributonYukle() {
        yukaributon = Utils.loadImage(root,
                "butonlar/yukari_ok.png");
        yukaributonx = 30;
        yukaributony = 1080 - asagibuton.getHeight() - yukaributon.getHeight() - 50;
    }

    private void atesbutonuYukle() {
        atesbutonu = Utils.loadImage(root,
                "butonlar/ates_etme.png");
        atesbutonux = 1920 - atesbutonu.getHeight() - 30;
        atesbutonuy = 1080 - atesbutonu.getHeight() - 30;
    }

    private void nesneparametreleriCiz() {
        for (int secilennesne = 0; secilennesne < dusmanlistesi.size(); secilennesne++) {
            canvas.drawText("Düşman Sayısı: " + dusmanlistesi.size(),
                    100, 100, puanrengi);
            canvas.drawText("Seçilen Düşmanın İndexi: " + (secilennesne),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y), puanrengi);
            //Düşmanın parametreleri
            canvas.drawText("Düşmanın Türü: " + (dusmanlistesi.get(secilennesne).get(DUSMAN_TUR)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 30, puanrengi);
            canvas.drawText("Düşmanın Frame: " + (dusmanlistesi.get(secilennesne).get(DUSMAN_FRAME)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 60, puanrengi);
            canvas.drawText("Düşmanın X: " + (dusmanlistesi.get(secilennesne).get(DUSMAN_X)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 90, puanrengi);
            canvas.drawText("Düşmanın Y: " + (dusmanlistesi.get(secilennesne).get(DUSMAN_Y)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 120, puanrengi);
            canvas.drawText("Düşmanın Hız: " + (dusmanlistesi.get(secilennesne).get(DUSMAN_HIZX)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 150, puanrengi);
            canvas.drawText("Düşmanın Mermi Türü: " + (dusmanlistesi.get(secilennesne).get(DUSMAN_MERMI)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 180, puanrengi);
            canvas.drawText("Düşmanın Reload Süresi: " + (dusmanreloadsureleri.get(secilennesne)),
                    dusmanlistesi.get(secilennesne).get(DUSMAN_X) + 200,
                    dusmanlistesi.get(secilennesne).get(DUSMAN_Y) + 210, puanrengi);
        }
    }

    private void puanCiz() {
        //Çerçeveyi çiz
        canvas.drawBitmap(puancerceve, puancercevex, puancercevey, null);
        //Puanı çiz
        canvas.drawText("" + puan, puanx, puany, puanrengi);
    }

    private void arkaplankaydirmaMekanizmasi() {
        if (kaydirmamiktari < 1920 - kaydirmahizi) {
            kaydirmamiktari += kaydirmahizi;
        } else {
            kaydirmamiktari = 0;
        }
    }

    private void arayuzButonlari() {
        atesButonuCiz();
        asagibutonCiz();
        yukaributonCiz();
        canveenerjiCiz();
    }

    private void asagibutonCiz() {
        canvas.drawBitmap(asagibuton, asagibutonx, asagibutony, null);
    }

    private void yukaributonCiz() {
        canvas.drawBitmap(yukaributon, yukaributonx, yukaributony, null);
    }

    private void arkaplanYukle() {
        arkaplan = Utils.loadImage(root, "arkaplan_oyun.png");
        arkaplankaynak = new Rect();
        arkaplanhedef = new Rect();
        kaydirmahizi = 40;
        kaydirmamiktari = 0;
    }

    private void atesButonuCiz() {
        canvas.drawBitmap(atesbutonu, atesbutonux, atesbutonuy, null);
    }

    private void arkaplanCiz() {
        //Arkaplanı kaydıran bölüm
        arkaplankaynak.set(kaydirmamiktari, 0, arkaplan.getWidth(), arkaplan.getHeight());
        arkaplanhedef.set(0, 0, 1920 - kaydirmamiktari, 1080);
        canvas.drawBitmap(arkaplan, arkaplankaynak, arkaplanhedef, null);

        //Eksik bölümü çizen parça
        arkaplankaynak.set(0, 0, kaydirmamiktari, 1080);
        arkaplanhedef.set(1920 - kaydirmamiktari, 0, 1920, 1080);
        canvas.drawBitmap(arkaplan, arkaplankaynak, arkaplanhedef, null);

    }

    private void atesbutonTiklandi(int x, int y) {
        if (x > root.xOranla(atesbutonux)
                && y > root.yOranla(atesbutonuy)
                && x < root.xOranla(atesbutonux + atesbutonu.getWidth())
                && y < root.yOranla(atesbutonuy + atesbutonu.getHeight())
                && oyundevamediyor == true
                && oyunbitti == false) {
            if (karakteratesetsin == true){
                enerjiAzaltmaIslemi();
                oyuncuAtesUret(oyuncux, oyuncuy);
            }

        }
    }

    private void yukaributonTiklandi(int x, int y) {
        if (oyuncuy > 0
                && x > root.xOranla(yukaributonx)
                && y > root.yOranla(yukaributony)
                && x < root.xOranla(yukaributonx + yukaributon.getWidth())
                && y < root.yOranla(yukaributony + yukaributon.getHeight())
                && oyundevamediyor == true
                && oyunbitti == false) {
            yukarigidecek = true;
        }

    }

    private void asagibutonTiklandi(int x, int y) {
        Log.d("log1 " , "" + root.yOranla(asagibutony));
        Log.d("log2 " , "" + root.yOranla(asagibutony + asagibuton.getHeight()));

        if (oyuncuresmi[karakterdurum][karakterframe].getHeight() + oyuncuy < 1080
                && x > root.xOranla(asagibutonx)
                && y > root.yOranla(asagibutony)
                && x < root.xOranla(asagibutonx + asagibuton.getWidth())
                && y < root.yOranla(asagibutony + asagibuton.getHeight())
                && oyundevamediyor == true
                && oyunbitti == false) {
            asagigidecek = true;
        }

    }
    //-----Arayüz Bölümü Bitişi-----






    public void keyPressed(int key) {
    }
    public void keyReleased(int key) {
    }
    public boolean backPressed() {
        return true;
    }
    public void surfaceChanged(int width, int height) {
    }
    public void surfaceCreated() {
    }
    public void surfaceDestroyed() {
    }

    public void touchDown(int x, int y, int id) {
        asagibutonTiklandi(x, y);
        yukaributonTiklandi(x, y);
        atesbutonTiklandi(x, y);
    }

    public void touchMove(int x, int y, int id) {
        if (!(x > root.xOranla(yukaributonx)
                && y > root.yOranla(yukaributony)
                && x < root.xOranla(yukaributonx + yukaributon.getWidth())
                && y < root.yOranla(yukaributony + yukaributon.getHeight()))) {
            yukarigidecek = false;
        }

        if (!(x > root.xOranla(asagibutonx)
                && y > root.yOranla(asagibutony)
                && x < root.xOranla(asagibutonx + asagibuton.getWidth())
                && y < root.yOranla(asagibutony + asagibuton.getHeight()))) {
            asagigidecek = false;
        }
    }

    public void touchUp(int x, int y, int id) {
        karakterYonHareketi(x, y);
        pauseMenuTiklandi(x, y);
        oyunsonuTiklandi(x, y);
    }

    private void oyunsonuTiklandi(int x, int y) {
       ostekrarTiklandi(x, y);
       osileriTiklandi(x, y);
    }

    private void osileriTiklandi(int x, int y) {
        if (x >= root.xOranla(osilerix)
                &&  x < root.xOranla(osileriw)
                &&  y >= root.yOranla(osileriy)
                &&  y < root.yOranla(osilerih)
                &&  oyundevamediyor == false
                &&  oyunbitti == true){
            if (root.oyunmuzik.isPlaying()){
                root.gamemuzikOynat(false);
            }
            root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
        }
    }

    private void ostekrarTiklandi(int x, int y) {
        if (x >= root.xOranla(osyenidenx)
                &&  x < root.xOranla(osyenidenw)
                &&  y >= root.yOranla(osyenideny)
                &&  y < root.yOranla(osyenidenh)
                &&  oyundevamediyor == false
                &&  oyunbitti == true){
            if (root.oyunmuzik.isPlaying()){
                root.gamemuzikOynat(false);
            }
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        }
    }

    private void pauseMenuTiklandi(int x, int y) {
        if (x >= root.xOranla(ayarbutonx)
        &&  x < root.xOranla(ayarbutonx + ayarbutonresmi.getWidth())
        &&  y >= root.yOranla(ayarbutony)
        &&  y < root.yOranla(ayarbutony + ayarbutonresmi.getHeight())
        &&  oyunbitti == false){
            oyundevamediyor = !oyundevamediyor;
        }

        devamButonuTiklandi(x, y);
        tekrarButonuTiklandi(x, y);
        menuyeDonButonuTiklandi(x, y);
    }

    private void menuyeDonButonuTiklandi(int x, int y) {
        if (x >= root.xOranla(menudonx)
                &&  x < root.xOranla(menudonw)
                &&  y >= root.yOranla(menudony)
                &&  y < root.yOranla(menudonh)
                &&  oyundevamediyor == false
                &&  oyunbitti == false){
            if (root.oyunmuzik.isPlaying()){
                root.gamemuzikOynat(false);
            }
            if (root.bosspatlama.isPlaying()){
                root.bosspatlama.stop();
            }
            root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
        }
    }

    private void tekrarButonuTiklandi(int x, int y) {
        if (x >= root.xOranla(yenidenx)
                &&  x < root.xOranla(yenidenw)
                &&  y >= root.yOranla(yenideny)
                &&  y < root.yOranla(yenidenh)
                &&  oyundevamediyor == false
                &&  oyunbitti == false){
            if (root.oyunmuzik.isPlaying()){
                root.gamemuzikOynat(false);
            }
            if (root.bosspatlama.isPlaying()){
                root.bosspatlama.stop();
            }
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        }
    }

    private void devamButonuTiklandi(int x, int y) {
        if (x >= root.xOranla(devamx)
        &&  x < root.xOranla(devamw)
        &&  y >= root.yOranla(devamy)
        &&  y < root.yOranla(devamh)
        &&  oyundevamediyor == false
        &&  oyunbitti == false){
            oyundevamediyor = true;
        }
    }

    private void karakterYonHareketi(int x, int y) {
        if (yukarigidecek == true
                && x > root.xOranla(yukaributonx)
                && y > root.yOranla(yukaributony)
                && x < root.xOranla(yukaributonx + yukaributon.getWidth())
                && y < root.yOranla(yukaributony + yukaributon.getHeight())
                && oyundevamediyor == true
                && oyunbitti == false) {
            yukarigidecek = false;
        }
        if (asagigidecek == true
                && x > root.xOranla(asagibutonx)
                && y > root.yOranla(asagibutony)
                && x < root.xOranla(asagibutonx + asagibuton.getWidth())
                && y < root.yOranla(asagibutony + asagibuton.getHeight())
                && oyundevamediyor == true
                && oyunbitti == false) {
            asagigidecek = false;
        }
    }

    public void pause() {
    }
    public void resume() {
    }
    public void reloadTextures() {
    }
    public void showNotify() {
    }
    public void hideNotify() {
    }
}
