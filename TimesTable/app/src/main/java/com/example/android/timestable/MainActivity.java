package com.example.android.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import static com.example.android.timestable.R.id.timestablelist;

public class MainActivity extends AppCompatActivity {

    ListView timestablelist;

    public void generatTimesTable(int timestable){
        ArrayList<String> content = new ArrayList<String>();

        for(int i = 1; i<=10; i++){

            content.add(Integer.toString(i * timestable));

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, content);
        timestablelist.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timestablebar = (SeekBar) findViewById(R.id.timestablebar);
        timestablelist = (ListView) findViewById(R.id.timestablelist);

        timestablebar.setMax(20); //but we can't set min here
        timestablebar.setProgress(10);

        timestablebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //set min to 1, or it will start with 0
                int min = 1;
                int timestable;

                if(i < 1){
                    timestable = min;
                    timestablebar.setProgress(min);
                }else{
                    timestable = i;
                }

                generatTimesTable(timestable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        generatTimesTable(10);


    }
}
