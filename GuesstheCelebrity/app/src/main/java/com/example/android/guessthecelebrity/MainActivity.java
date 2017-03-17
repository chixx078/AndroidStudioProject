package com.example.android.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int chosenCeleb = 0;
    int locationOfCorrectAnswer = 0; //0-3
    String[] answer = new String[4];

    ImageView imageView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;


    public void celebChosen(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Wrong! It's " + celebNames.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }

        newQuestion();

    }



    //Download images
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    //Download web html content
    public class Download extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try{

                url = new URL(urls[0]);

                connection = (HttpURLConnection) url.openConnection();

                InputStream in = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                    connection.disconnect();

            }

            Log.i("activity","Done");
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("connection","done");

        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        Download task = new Download();
        String result ="";

        try {
            result = task.execute("http://www.posh24.se/kandisar").get();

            String[] splitResult = result.split("<div class=\"sidebarContainer\">");

            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]); //the part of code before <div class="sidebarContainer">

            while(m.find()){

                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while(m.find()){

                celebNames.add(m.group(1));
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        newQuestion();

    }

    public void newQuestion(){

        //display a random picture

        Random random = new Random();
        chosenCeleb = random.nextInt(celebURLs.size()); //nextInt gives 0 ~ parameter-1

        ImageDownloader imageDownloader = new ImageDownloader();

        Bitmap celebImage;

        try {
            celebImage = imageDownloader.execute(celebURLs.get(chosenCeleb)).get();

            imageView.setImageBitmap(celebImage);


            //display  names

            locationOfCorrectAnswer = random.nextInt(4);

            int incorrectAnswer;

            for(int i=0; i<4; i++){

                if(i==locationOfCorrectAnswer){
                    //display right name

                    answer[i] = celebNames.get(chosenCeleb);

                }else{
                    //display 3 random names

                    incorrectAnswer = random.nextInt(celebURLs.size());

                    while(incorrectAnswer == chosenCeleb){
                        incorrectAnswer = random.nextInt(celebURLs.size());
                    }

                    answer[i] = celebNames.get(incorrectAnswer);


                }

            }

            button0.setText(answer[0]);
            button1.setText(answer[1]);
            button2.setText(answer[2]);
            button3.setText(answer[3]);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
