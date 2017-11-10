package com.project.larreur.pongbaptistelarreurs6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by larre on 30/10/2017.
 */

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        setContentView(gameView);
    }
}

