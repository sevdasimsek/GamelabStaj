package com.ngdroidapp;

import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseActivity;
import istanbul.gamelab.ngdroid.core.AppManager;
import istanbul.gamelab.ngdroid.base.BaseApp;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.core.SoundManager;
import istanbul.gamelab.ngdroid.util.Log;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */

public class NgApp extends BaseApp {

    //ses efekti etiket
    int SE_BUTON = 0, SE_ATES = 1, SE_PATLAMA = 2, SE_BOSSOLU1 = 3, SE_BOSSOLU2 = 4, SE_BOSSATES = 5
            , SE_BOSSATES2 = 6, SE_BOLUMBITTI = 7;

    NgMediaPlayer oyunmuzik;
    NgMediaPlayer menumuzik;
    NgMediaPlayer bosspatlama;
    NgMediaPlayer bossbolumsonu;

    boolean muzikbutonacik;
    boolean sesbutonacik;

    SoundManager sescalar;
    int sesefektleri[];

    int maxpuan;


    public NgApp(BaseActivity nitraBaseActivity, AppManager appManager) {
        super(nitraBaseActivity, appManager);
    }

    public void sesOynat(int secilen){
        if (sesbutonacik == true){
            sescalar.play(sesefektleri[secilen]);
        }
    }

    public void menumuzikOynat(boolean oynat){
        if (muzikbutonacik == true && oynat == true){
            menumuzik.prepare();
            menumuzik.start();
        }
        if (muzikbutonacik == false || oynat == false){
            menumuzik.stop();
        }
    }

    public void gamemuzikOynat(boolean oynat){
        if (muzikbutonacik == true && oynat == true){
            oyunmuzik.prepare();
            oyunmuzik.start();
        }
        if (muzikbutonacik == false || oynat == false){
            oyunmuzik.stop();
        }
    }

    public void maxpuanTut(int puan){
        if (puan > maxpuan){
            maxpuan = puan;
        }
    }

    public void setup() {
        maxpuan = 0;
        muzikbutonacik = true;
        sesbutonacik = true;

        appManager.setUnitResolution(AppManager.RESOLUTION_FULLHD);
        appManager.setFrameRate(20);

        oyunmuzik = new NgMediaPlayer(this);
        oyunmuzik.load("sounds/oyunmuzigi.mp3");

        menumuzik = new NgMediaPlayer(this);
        menumuzik.load("sounds/menumuzigi.mp3");

        bosspatlama = new NgMediaPlayer(this);
        bosspatlama.load("sounds/bossexplode.wav");

        bossbolumsonu = new NgMediaPlayer(this);
        bossbolumsonu.load("sounds/horn.wav");

        sescalar = new SoundManager(this);
        sesefektleri = new int[6];
        try {
            sesefektleri[SE_BUTON] = sescalar.load("sounds/buttonclick2.wav");
            sesefektleri[SE_ATES] = sescalar.load("sounds/kediates.ogg");
            sesefektleri[SE_PATLAMA] = sescalar.load("sounds/se2.wav");
            sesefektleri[SE_BOSSOLU1] = sescalar.load("sounds/bossexplode.wav");
            sesefektleri[SE_BOSSOLU2] = sescalar.load("sounds/bossexplode.wav");
            sesefektleri[SE_BOSSATES] = sescalar.load("sounds/shotgunshoot.wav");
            sesefektleri[SE_BOSSATES2] = sescalar.load("sounds/dusmanates.wav");
            sesefektleri[SE_BOLUMBITTI] = sescalar.load("sounds/horn.wav");
        }catch (Exception E){

        }
        MenuCanvas mc = new MenuCanvas(this);
        canvasManager.setCurrentCanvas(mc);
    }

    public float xOranla(int x){
        return x * (getWidth() / 1920.0f);
    }
    public float yOranla(int y){
        return y * (getHeight() / 1080.0f);
    }

    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void touchDown(int x, int y, int id) {

    }

    public void touchMove(int x, int y, int id) {

    }

    public void touchUp(int x, int y, int id) {

    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void pause() {
        Log.i("NGAPP", "pause");
    }

    public void resume() {
        Log.i("NGAPP", "resume");
    }

    public void reloadTextures() {
        Log.i("NGAPP", "reloadTextures");
    }
}
