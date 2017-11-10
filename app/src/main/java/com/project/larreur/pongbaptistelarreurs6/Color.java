package com.project.larreur.pongbaptistelarreurs6;

import android.graphics.Paint;
import java.util.Random;

/**
 * Created by larre on 02/11/2017.
 */

public class Color {
    public Paint paint = new Paint();
    private int r, g, b;
    public int valeurMin=0;
    public int valeurMax=256;

    public void setR(int r) {
        this.r = r;
    }
    public void setG(int g) {
        this.g = g;
    }
    public void setB(int b) {
        this.b = b;
    }
    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    protected void setColor(int _r,int _g,int _b){
        r=_r;
        g=_g;
        b=_b;
        paint.setARGB(253, _r, _g, _b);
    }

    public void changeColor(){
        Random r = new Random();
        int valeur1 = valeurMin + r.nextInt(valeurMax - valeurMin);
        int valeur2 = valeurMin + r.nextInt(valeurMax - valeurMin);
        int valeur3 = valeurMin + r.nextInt(valeurMax - valeurMin);
        setColor(valeur1,valeur2,valeur3);
    }
}