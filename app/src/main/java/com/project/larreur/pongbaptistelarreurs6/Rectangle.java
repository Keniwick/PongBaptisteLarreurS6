package com.project.larreur.pongbaptistelarreurs6;

import android.content.Context;
import android.graphics.Rect;

/**
 * Created by larre on 04/11/2017.
 */

public class Rectangle extends Mobile {
    public int w,h;

    public Rectangle(final Context c)
    {
        super(c,0,200,0,"Rectangle");
        rectangle=new Rect();
    }
    public Rectangle(final Context c, int _r, int _g, int _b,int _w,int _h)
    {
        super(c, _r, _g, _b,"Rectangle");
        rectangle=new Rect();
        w=_w;
        h=_h;
    }

    public Rectangle(final Context c, int _r, int _g, int _b, double _speedX,double _speedY,int _w,int _h)
    {
        super(c, _r, _g,_b,"Rectangle",_speedX,_speedY);
        rectangle=new Rect();
        w=_w;
        h=_h;
    }


    public void resize(int wScreen, int hScreen) {
        screenW=wScreen;
        screenH=hScreen;

        shapeW=wScreen/w;
        shapeH=wScreen/h;

    }
}
