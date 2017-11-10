package com.project.larreur.pongbaptistelarreurs6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by larre on 30/10/2017.
 */


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopThread gameLoopThread;
    private ArrayList<Mobile> listObjs;
    private Mobile obj = null;

    // création de la surface de dessin
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopThread(this);
        listObjs = new ArrayList<>();

        //Ajout d'objets
        listObjs.add(new Cercle(this.getContext(),1,25,25));
        listObjs.add(new Cercle(this.getContext(),2,20,16));
        listObjs.add(new Rectangle(this.getContext(),0,150,200,5,5,11,11));
        listObjs.add(new Rectangle(this.getContext(),0,150,200,0,0,5,9));
    }

    // Fonction qui "dessine" un écran de jeu
    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        // on efface l'écran, en noir
        canvas.drawColor(Color.BLACK);

        for (int k=0; k<listObjs.size();k++){
            ((Mobile) listObjs.get(k)).draw(canvas);
        }

    }

    // On gère le déplacement des objets
    public void update() {
        for (int k=0; k<listObjs.size();k++){
            ((Mobile) listObjs.get(k)).moveWithCollisionDetection(listObjs);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread=new GameLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int)event.getX();
        int currentY = (int)event.getY();

        switch (event.getAction()) {

            // code exécuté lorsque le doigt touche l'écran.
            case MotionEvent.ACTION_DOWN:
                // si le doigt touche la balle :
                for (int k=0; k<listObjs.size();k++){
                    obj=((Mobile) listObjs.get(k));

                    if(currentX >= obj.getX() &&
                            currentX <= obj.getX()+obj.getW() &&
                            currentY >= obj.getY() && currentY <= obj.getY()+obj.getH() )
                    {
                        // on arrête de déplacer la balle
                        obj.setMove(false);
                        obj.setOld(currentX,currentY);
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                //balle sous le doigt
                for (int k=0; k<listObjs.size();k++){
                    obj=((Mobile) listObjs.get(k));
                    if(!obj.isMoving()) {
                        obj.setX(currentX - obj.shapeW / 2);
                        obj.setY(currentY - obj.shapeH / 2);
                        if(obj.getDelay()>500) obj.setOld(currentX,currentY);
                    }
                }
                break;

            // lorsque le doigt quitte l'écran
            case MotionEvent.ACTION_UP:
                // La balle repart
                for (int k=0; k<listObjs.size();k++){
                    obj=((Mobile) listObjs.get(k));
                    if (!obj.isMoving()) obj.changeSpeed(); obj._c.changeColor();
                    obj.setMove(true);
                }

        }

        return true;  // Evenement effectué
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        for (int k=0; k<listObjs.size();k++){
            if (((Mobile) listObjs.get(k)).mobile=="Cercle"){
                ((Cercle) listObjs.get(k)).resize(w,h); //taille des cercles selon la taille de l'écran
            }
            if (((Mobile) listObjs.get(k)).mobile=="Rectangle"){
                ((Rectangle) listObjs.get(k)).resize(w,h); //taille des carrés selon la taille de l'écran
            }
        }

    }
}