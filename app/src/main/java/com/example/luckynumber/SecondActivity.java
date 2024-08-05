package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTxt = findViewById(R.id.welcomeTxt);
        luckyNumberTxt = findViewById(R.id.luckyNumberTxt);
        share_btn = findViewById(R.id.share_btn);

        // receive data from main activity

        Intent i = getIntent(); // getting all the data
        String userName = i.getStringExtra("name");//specify key and store in username variable


        int  random_num = generateRandomNumber();
        luckyNumberTxt.setText(""+random_num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random_num);
            }
        });
    }
        //Generating numbers

        public int generateRandomNumber(){
            Random random = new Random();         // standard library to store random numbers/values
            int upper_limit = 1000;

            int randomNumberGenerated = random.nextInt(upper_limit); // store the no generated
            return randomNumberGenerated;
        }

        public void shareData(String username, int randomNum){

        //Implicit intent
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");

            i.putExtra(Intent.EXTRA_SUBJECT,username+ " got lucky today");
            i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: "+randomNum);

            startActivity(Intent.createChooser(i, "Choose a Platform"));
        }

}