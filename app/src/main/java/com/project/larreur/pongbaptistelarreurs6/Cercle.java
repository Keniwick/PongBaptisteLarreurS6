package com.project.larreur.pongbaptistelarreurs6;

/**
 * Created by larre on 30/10/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Cercle extends Mobile {

    private int imageN = R.mipmap.ic_launcher;

    public Cercle(final Context c,int n,double _speedX , double _speedY ) {
        super( c, 0, 0, 0, "Cercle",_speedX ,_speedY );
        selectImgN(n);

    }

    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h) {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    public void resize(int wScreen, int hScreen) {
        screenW = wScreen;
        screenH = hScreen;

        // on définit la taille de la balle à 1/5ème de la largeur de l'écran
        shapeW = wScreen / 5;
        shapeH = wScreen / 5;
        cercle = setImage(mContext, imageN, shapeW, shapeH);
    }

    public void selectImgN(int n){
        if (n==1){imageN = R.mipmap.cercle1;}
        else if (n==2){imageN = R.mipmap.cercle2;}
        else if (n==0){imageN = R.mipmap.cercle3;}
    }
}