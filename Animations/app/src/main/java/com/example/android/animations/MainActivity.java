package com.example.android.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view){

        ImageView ada1 = (ImageView) findViewById(R.id.ada1);

        ada1.animate().translationXBy(1000f).setDuration(2000);

        //ImageView ada2 = (ImageView) findViewById(R.id.ada2);

        //ada2.animate().alpha(1f).setDuration(2000);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ImageView ada1 = (ImageView) findViewById(R.id.ada1);

        ada1.setTranslationX(-1000f);*/
    }
}
