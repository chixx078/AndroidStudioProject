package com.example.android.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //first String: web address
    //second void: usually a function for displaying, no fun here so void
    //third String: things to return. Here we just return the web address so String
    public class Download extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;

            HttpURLConnection urlConnection = null; //consider as a browser

            try{

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != 1){

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            }catch (Exception e){

                e.printStackTrace();

                return "Failed";


            }finally {
                urlConnection.disconnect();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Download task = new Download();
        String result = null;
        try {
            result = task.execute("https://www.ecowebhosting.co.uk").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        Log.i("Content of URL", result);

    }
}
