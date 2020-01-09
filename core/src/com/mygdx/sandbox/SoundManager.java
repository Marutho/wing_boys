package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    public static Music principalTheme = Gdx.audio.newMusic(Gdx.files.internal("SonidosMoviles/Mega.mp3"));
    public static Music menuTheme = Gdx.audio.newMusic(Gdx.files.internal("SonidosMoviles/MenuSong.mp3"));
    public static Sound Shoot = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/Shoot.wav"));
    public static Sound ShootEnemy = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/Disparo1.mp3"));
    public static Sound ShootEnemy2 = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/Disparo2.mp3"));
    public static Sound Hurt = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/Hurt.wav"));
    public static Sound HurtEnemy = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/HurtEnemy.wav"));
    public static Sound bulletvsbullet = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/bulletvsbullet.wav"));


    public static void reproduceSounds(int soundID){
        switch (soundID){
            case 1:
                Shoot.play();
                break;
            case 2:
                ShootEnemy.play();
                break;
            case 3:
                ShootEnemy2.play();
                break;
            case 4:
                Hurt.play();
                break;
            case 5:
                HurtEnemy.play();
                break;
            case 6:
                bulletvsbullet.play();
                break;
            default:
                break;

        }
    }

    public static void reproduceMusic(){
        menuTheme.stop();
        principalTheme.play();
        principalTheme.setLooping(true);
    }

    public static void reproduceMenuMusic(){
        menuTheme.play();
        menuTheme.setLooping(true);
    }
}
