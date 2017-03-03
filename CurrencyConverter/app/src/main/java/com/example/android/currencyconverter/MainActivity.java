package com.example.android.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){
        EditText dollarEditText = (EditText) findViewById(R.id.dollarEditText);

        Double dollarAmountDouble = Double.parseDouble(dollarEditText.getText().toString());

        Double poundAmount = dollarAmountDouble * 0.75;

        Toast.makeText(this, String.format("%.2f", poundAmount), Toast.LENGTH_SHORT).show();

        Log.i("amount", dollarEditText.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
