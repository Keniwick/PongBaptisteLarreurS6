package com.project.larreur.pongbaptistelarreurs6;

/**
 * Created by larre on 30/10/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Rect;
import java.util.ArrayList;


public class Mobile extends Forme {
    protected Rect rectangle = null;
    protected BitmapDrawable cercle = null;
    protected int shapeW, shapeH; // largeur et hauteur de la balle en pixels
    protected int screenW, screenH; // largeur et hauteur de l'écran en pixels
    public int ral=1;
    public String mobile;
    protected final Context mContext;
    Color _c = new Color();


    public Mobile(final Context c, int _r, int _g, int _b, String _mobile, double _speedX, double _speedY) {
        super(5,5,_speedX, _speedY);
        _c.setR(_r);
        _c.setG(_g);
        _c.setB(_b);
        mobile = _mobile;
        _c.paint.setARGB(253, _c.getR(), _c.getG(), _c.getB());
        mContext = c; // sauvegarde du contexte
    }

    public Mobile( final Context c, int _r, int _g, int _b, String _mobile) {
        super(20,20, 5, 5);
        _c.setR(_r);
        _c.setG(_g);
        _c.setB(_b);
        mobile = _mobile;
        mContext = c; // sauvegarde du contexte
    }

    // on dessine le mobile, en x et y
    public void draw(Canvas canvas) {

        if (mobile == "Cercle") {
            if (cercle == null) {
                return;
            }
            canvas.drawBitmap(cercle.getBitmap(), getX(), getY(), null);
        }
        if (mobile == "Rectangle") {
            if (rectangle == null) {
                return;
            }
            _c.setColor(_c.getR(),_c.getG(),_c.getB());
            rectangle =new Rect(getX(), getY(), getX() + shapeW, getY() + shapeH);
            canvas.drawRect(rectangle, _c.paint);
        }
    }


    public int getW() {
        return shapeW;
    }

    public int getH() {
        return shapeH;
    }

    public int getX() {return x; }

    public int getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
        collX();
    }

    public void setY(int y) {
        this.y = y;
        collY();

    }

    public void collX() {
        if (x >= screenW - shapeW) {
            this.x = screenW - shapeW;
        }
        if (x < 0) {
            this.x = 0;
        }
    }

    public void collY() {
        if (y >= screenH - shapeH) {
            this.y = screenH - shapeH;
        }
        if (y < 0) {
            this.y = 0;
        }
    }

    public void moveWithCollisionDetection(ArrayList<Mobile> listObjs) {
        // on ne doit pas déplacer la balle (lorsqu'elle est sous le doigt du joueur)
        // on quitte
        if (!move) {
            return;
        }


        // on incrémente X et Y
        x += speedX;
        y += speedY;

        // si x dépasse la largeur de l'écran, on inverse le déplacement
        if (x + getShapeW() > getScreenW()) {
            if (speedX > 0) speedX = -speedX+ral;
            x=getScreenW()-getShapeW();
        }

        // si y dépasse la hauteur l'écran, on inverse le déplacement
        if (y + getShapeH() > getScreenH()) {
            if (speedY > 0) speedY = -speedY+ral;
            y=getScreenH()-getShapeH();
        }

        // si x passe à gauche de l'écran, on inverse le déplacement
        if (x < 0) {
            if (speedX < 0) speedX = -speedX-ral;
            x=0;
        }

        // si y passe au dessus de l'écran, on inverse le déplacement
        if (y < 0) {
            if (speedY < 0) speedY = -speedY-ral;
            y=0;
        }
        for (int k=0; k<listObjs.size();k++){
            Mobile obj=(Mobile) listObjs.get(k);
            if(obj!=this){
                int xs=x+shapeW;int ys=y+shapeH;
                int x2=obj.getX();int y2=obj.getY();
                int x2s=x2+obj.getShapeW();int y2s=y2+obj.getShapeH();

                //collision horizontale
                if(((y2<ys)&&(ys<y2s))|| ((y2<y)&&(y<y2s))){
                    //cas d'arrivee par la gauche
                    if((x+shapeW/2<x2)&&(x2<xs)){
                        if (speedX>0) speedX=-speedX;
                        if (obj.speedX<0) obj.speedX=-obj.speedX;
                    }
                    //arrivée par la droite
                    if((x<x2s)&&(x2s<x+shapeW/2)){
                        if (speedX<0) speedX=-speedX;
                        if (obj.speedX>0) obj.speedX=-obj.speedX;
                    }
                }
                //collision verticale
                if(((x2<xs)&&(xs<x2s))|| ((x2<x)&&(x<x2s))){
                    //cas d'arrivee par le haut
                    if((y+shapeH/2<y2)&&(y2<ys)){
                        if (speedY>0) speedY=-speedY;
                        if (obj.speedY<0) obj.speedY=-obj.speedY;
                    }
                    //arrivée par le bas
                    if((y<y2s)&&(y2s<y+shapeH/2)){
                        if (speedY<0) speedY=-speedY;
                        if (obj.speedY>0) obj.speedY=-obj.speedY;
                    }
                }
            }
        }
    }

    public int getScreenH() {
        return screenH;
    }

    public int getShapeH() {
        return shapeH;
    }

    public int getScreenW() {
        return screenW;
    }

    public int getShapeW() {
        return shapeW;
    }
}

