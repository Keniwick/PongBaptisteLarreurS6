package com.project.larreur.pongbaptistelarreurs6;

/**
 * Created by larre on 29/10/2017.
 */

public class Forme {
    private long delay;
    protected int x, y; // coordonnées x,y de la forme en pixel
    protected int oldX,oldY;
    long lastRefreshTime;
    protected boolean move = true; // true si la forme doit se déplacer automatiquement, false sinon
    public double speedX, speedY;
    protected int shapeW, shapeH; // largeur et hauteur de la balle en pixels

    public Forme(int _x, int _y,double _speedX,double _speedY) {
        x = _x;
        y = _y;
        speedX = _speedX;
        speedY = _speedY;
    }

    public boolean isMoving() {
        return move;
    }

    public void setOld(int oldX,int oldY) {
        this.oldX = oldX;
        this.oldY = oldY;
        lastRefreshTime=System.currentTimeMillis();
        delay=0;
    }

    public void changeSpeed(){

        speedX=(int) (5*(x-oldX)/(1+delay));
        speedY=(int) (5*(y-oldY)/(1+delay));
        if (speedX<-20) speedX=-20;
        if (speedY<-20) speedY=-20;
        if (speedX> 20) speedX= 20;
        if (speedY> 20) speedY= 20;
    }


    public void setMove(boolean move) {
        this.move = move;
    }

    public long getDelay() {
        setDelay();
        return delay;
    }

    public void setDelay() {
        delay=System.currentTimeMillis()-lastRefreshTime;
    }
}
