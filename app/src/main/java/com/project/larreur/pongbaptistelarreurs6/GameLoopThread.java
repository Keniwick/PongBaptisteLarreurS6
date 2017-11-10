package com.project.larreur.pongbaptistelarreurs6;

import android.graphics.Canvas;

/**
 * Created by larre on 30/10/2017.
 */

public class GameLoopThread extends Thread
{
    // on définit arbitrairement le nombre d'images par secondes à 50
    private final static int FRAMES_PER_SECOND = 50;

    // si on veut X images en 1 seconde, soit en 1000 ms,
    // on doit en afficher une toutes les (1000 / f) ms.
    private final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    private final GameView view;
    private boolean running = false;// état du thread, en cours ou non

    // constructeur de l'objet, on associe l'objet view passé en paramètre
    public GameLoopThread(GameView view) {
        this.view = view;
    }

    // défini l'état du thread : true ou false
    public void setRunning(boolean run) {
        running = run;
    }

    // démarrage du thread
    @Override
    public void run()
    {
        // déclaration temps de départ et de pause
        long startTime;
        long sleepTime;

        while (running)
        {
            startTime = System.currentTimeMillis();

            synchronized (view.getHolder()) {view.update();}

            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {view.doDraw(c);}
            }
            finally
            {
                if (c != null) {view.getHolder().unlockCanvasAndPost(c);}
            }

            sleepTime = SKIP_TICKS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime >= 0) {sleep(sleepTime);}
            }
            catch (Exception e) {}
        }
    }

}