package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Utils;

/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {

    Canvas canvas;

    //Arkaplan
    Bitmap arkaplan;
    //Oyna buton parametreleri
    Bitmap oynabuton[];
    int oynabutonx, oynabutony;
    int oynabutondurumu;
    //Geçici
    Paint yazirengi;
    int yazix, yaziy;
    int sayac;
    int sonuc2;

    //ayar butonu
    Bitmap ayarbutonu;
    int ayarbutonux, ayarbutonuy;
    boolean ayarbutonacik;

    //ses butonu
    Bitmap sesbutonu;
    int sesbutonux, sesbutonuy;

    //muzik butonu
    Bitmap muzikbutonu;
    int muzikbutonux, muzikbutonuy;

    //ban
    Bitmap banresmi;
    int bansesx, bansesy, banmuzikx, banmuziky;

    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        sayac = 0;
        sonuc2 = 0;
        yazirengi = new Paint();
        yazirengi.setTextSize(40);//Punto
        yazirengi.setTypeface(Typeface.DEFAULT_BOLD);//Kalın yazı
        yazirengi.setTextAlign(Paint.Align.LEFT);//Sola yasla
        yazirengi.setARGB(255, 255, 0, 0);//Renk A:saydam, R:RED, G:Green, B:Blue
        yazix = 100;
        yaziy = 100;
        arkaplanYukle();
        oynabutonYukle();
        ayarbutonuYukle();
        sesbutonuYukle();
        muzikbutonuYukle();
        banYukle();
        root.menumuzikOynat(true);
    }

    private void banYukle() {
        banresmi = Utils.loadImage(root, "butonlar/ban.png");
    }

    private void muzikbutonuYukle() {
        muzikbutonu = Utils.loadImage(root, "butonlar/müzik.png");
        muzikbutonux = ayarbutonux;
        muzikbutonuy = ayarbutonuy + ayarbutonu.getHeight() + sesbutonu.getHeight();
        banmuzikx = muzikbutonux;
        banmuziky = muzikbutonuy;
    }

    private void sesbutonuYukle(){
        sesbutonu = Utils.loadImage(root, "butonlar/ses.png");
        sesbutonux = ayarbutonux;
        sesbutonuy = ayarbutonuy + ayarbutonu.getHeight();
        bansesx = sesbutonux;
        bansesy = sesbutonuy;
    }

    private void ayarbutonuYukle(){
        ayarbutonu = Utils.loadImage(root, "butonlar/ayar.png");
        ayarbutonux = 20;
        ayarbutonuy = 20;
        ayarbutonacik = false;
    }

    private  void ayarbutonuCiz(){
        canvas.drawBitmap(ayarbutonu, ayarbutonux, ayarbutonuy, null);
        if (ayarbutonacik == true){
            sesbutonuCiz();
            muzikbutonuCiz();
        }
    }

    private void sesbutonuCiz() {
        canvas.drawBitmap(sesbutonu, sesbutonux, sesbutonuy, null);
        if (root.sesbutonacik == false){
            canvas.drawBitmap(banresmi, bansesx, bansesy, null);
        }
    }

    private void muzikbutonuCiz() {
        canvas.drawBitmap(muzikbutonu, muzikbutonux, muzikbutonuy, null);
        if (root.muzikbutonacik == false){
            canvas.drawBitmap(banresmi, banmuzikx, banmuziky, null);
        }
    }

    private void oynabutonYukle() {
        //oynabutonYukle
        oynabutondurumu = 0;
        oynabuton = new Bitmap[2];
        oynabuton[0] = Utils.loadImage(root, "butonlar/Play_button.png");
        oynabuton[1] = Utils.loadImage(root, "butonlar/play.png");
        oynabutonx = 1920 / 2 - oynabuton[oynabutondurumu].getWidth() / 2;
        oynabutony = 1080 / 2 - oynabuton[oynabutondurumu].getHeight() / 2;

    }

    private void arkaplanYukle() {
        //arkaplanYukle
        arkaplan = Utils.loadImage(root, "arkaplan_menu.png");
    }

    public void update() {

    }

    /**
     * Tüm çizdirilme işlemleri burada yapılır.
     * @param canvas Telefonun ekranını temsil eder.
     * cv: canvas => Telefonun ekranını temsil eder.
     */
    public void draw(Canvas canvas) {
        this.canvas = canvas;
        canvas.scale(getWidth() / 1920.0f, getHeight() / 1080.0f);
        arkaplanCiz();
        oynabutonCiz();
        ayarbutonuCiz();
    }

    private void oynabutonCiz() {
        canvas.drawBitmap(oynabuton[oynabutondurumu], oynabutonx, oynabutony, null);
    }

    private void arkaplanCiz() {
        canvas.drawBitmap(arkaplan, 0, 0, null);
    }

    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {
        oynaButonTiklandi(x, y);
        ayarbutonTiklandi(x, y);
        sesbutonTiklandi(x, y);
        muzikbutonTiklandi(x, y);
    }

    private void muzikbutonTiklandi(int x, int y) {
        if (ayarbutonacik == false) return;
        if (x >= root.xOranla(muzikbutonux)
                && x < root.xOranla(muzikbutonux) + muzikbutonu.getWidth()
                && y >= root.yOranla(muzikbutonuy)
                && y < root.yOranla(muzikbutonuy) + muzikbutonu.getHeight()){
            root.muzikbutonacik = !root.muzikbutonacik;
            root.menumuzikOynat(root.muzikbutonacik);
        }
    }

    private void sesbutonTiklandi(int x, int y) {
        if (ayarbutonacik == false) return;
        if (x >= root.xOranla(sesbutonux)
        && x < root.xOranla(sesbutonux) + sesbutonu.getWidth()
        && y >= root.yOranla(sesbutonuy)
        && y < root.yOranla(sesbutonuy) + sesbutonu.getHeight()) {
            root.sesbutonacik = !root.sesbutonacik;
        }
    }

    private void ayarbutonTiklandi(int x, int y) {
        if (x >= root.xOranla(ayarbutonux)
        && x < root.xOranla(ayarbutonuy) + ayarbutonu.getWidth()
        && y >= root.yOranla(ayarbutonux)
        && y < root.yOranla(ayarbutonuy) + ayarbutonu.getHeight()){
            ayarbutonacik = !ayarbutonacik;
        }
    }


    private void oynaButonTiklandi(int x, int y) {
        if(x > root.xOranla(oynabutonx)
        && y > root.yOranla(oynabutony)
        && x < root.xOranla(oynabutonx) + oynabuton[oynabutondurumu].getWidth()
        && y < root.yOranla(oynabutony) + oynabuton[oynabutondurumu].getHeight()) {
            oynabutondurumu = 1;
            oynaButonKonumunuDuzelt();
            root.sescalar.play(root.sesefektleri[root.SE_BUTON]);
        }
    }

    private void oynaButonKonumunuDuzelt() {
        oynabutonx = 1920 / 2 - oynabuton[oynabutondurumu].getWidth() / 2;
        oynabutony = 1080 / 2 - oynabuton[oynabutondurumu].getHeight() / 2;
    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        if(x > root.xOranla(oynabutonx)
        && y > root.yOranla(oynabutony)
        && x < root.xOranla(oynabutonx) + oynabuton[0].getWidth()
        && y < root.yOranla(oynabutony) + oynabuton[0].getHeight()) {
            root.menumuzik.stop();
            root.sescalar.play(root.sesefektleri[root.SE_BUTON]);
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        }
        if(oynabutondurumu == 1) {
            oynabutondurumu = 0;
            oynaButonKonumunuDuzelt();
            }
    }

    public void surfaceChanged(int width, int height) {
    }

    public void surfaceCreated() {
    }

    public void surfaceDestroyed() {
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
