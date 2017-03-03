package com.example.android.gridlayoutdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void buttonTapped(View view){

        int id = view.getId(); //this is the id of android studio, a very large number
        String ourId = "";

        ourId = view.getResources().getResourceEntryName(id); //this is our id

        //then link the mp3 resource file to the button
        //our id must be exactly the same with file name, no space or capital allowed
        int resourceId = getResources().getIdentifier(ourId, "raw", "com.example.android.gridlayoutdemo");

        MediaPlayer mplayer = MediaPlayer.create(this, resourceId);
        mplayer.start();

        Log.i("tapped", ourId);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
