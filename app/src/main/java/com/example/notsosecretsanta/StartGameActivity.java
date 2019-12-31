package com.example.notsosecretsanta;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartGameActivity extends AppCompatActivity {

    TextView p1View;
    TextView p2View;
    TextView arrow;
    Button nextButton;
    TextView startTitle;
    LinearLayout historyContainer;
    int clickCount=0;

    Random rand1 = new Random();
    Random rand2 = new Random();

    List<Integer> collector1 =new ArrayList<Integer>();
    List<Integer> collector2 =new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        p1View = findViewById(R.id.player1);
        p2View = findViewById(R.id.player2);
        arrow = findViewById(R.id.arrow);
        startTitle = findViewById(R.id.startTitle);
        nextButton = findViewById(R.id.nextBtn);
        historyContainer = findViewById(R.id.textHistoryContainer);

        final List<String> p1 = AppSettings.getInstance().getItemsFromPreferences(getApplicationContext());
        final List<String> p2 = new ArrayList<String>();


        for (String string: p1) {
            p2.add(string);
        }
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTitle.setText("HINT: Tap on Next To See Next Mathed Pair");
                startTitle.setTextColor(Color.GREEN);
                startTitle.setTextSize(18);
                clickCount++;
                if (p1.isEmpty()){
                    Toast.makeText(StartGameActivity.this,"There are no players",Toast.LENGTH_SHORT).show();
                    nextButton.setVisibility(View.GONE);
                    return;
                }

                if(clickCount == 1){
                    int firstRandInt = rand1.nextInt(p1.size());
                    collector1.add(firstRandInt);
                    int secondRandInt = rand2.nextInt(p2.size());
                    collector2.add(secondRandInt);

                    while(firstRandInt == secondRandInt){
                        collector2.remove(secondRandInt);
                        secondRandInt = rand2.nextInt(p2.size());
                        collector2.add(secondRandInt);
                    }

                    String firstText = p1.get(firstRandInt);
                    p1View.setText(firstText);

                    String secondText = p2.get(secondRandInt);
                    p2View.setText(secondText);


                    // After every tap the strings in first and second array moving to bottom container
                    LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                    View listItemLayout= layoutInflater.inflate(R.layout.view_double_text, historyContainer, false);

                    TextView txt1 = listItemLayout.findViewById(R.id.txt1);
                    TextView txt2 = listItemLayout.findViewById(R.id.txt2);

                    txt1.setText(firstText);
                    txt2.setText(secondText);

                    historyContainer.addView(listItemLayout);

                    nextButton.setText("Next");

                }
                else if(clickCount!= p1.size()){

                    int firstRandInt = rand1.nextInt(p1.size());
                    int secondRandInt = rand2.nextInt(p2.size());

                    while(firstRandInt == secondRandInt){
                        secondRandInt = rand2.nextInt(p2.size());
                        while(collector2.contains(secondRandInt)){
                            secondRandInt = rand2.nextInt(p2.size());
                        }
                    }

                    while(collector1.contains(firstRandInt)){
                        firstRandInt = rand1.nextInt(p1.size());
                    }
                    collector1.add(firstRandInt);

                    while(collector2.contains(secondRandInt)){
                        secondRandInt = rand2.nextInt(p2.size());
                    }
                    collector2.add(secondRandInt);


                    String firstText = p1.get(firstRandInt);
                    p1View.setText(firstText);

                    String secondText = p2.get(secondRandInt);
                    p2View.setText(secondText);


                    // After every tap the strings in first and second array moving to bottom container
                    LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                    View listItemLayout= layoutInflater.inflate(R.layout.view_double_text, historyContainer, false);

                    TextView txt1 = listItemLayout.findViewById(R.id.txt1);
                    TextView txt2 = listItemLayout.findViewById(R.id.txt2);

                    txt1.setText(firstText);
                    txt2.setText(secondText);

                    historyContainer.addView(listItemLayout);

                    nextButton.setText("Next");
                }
                else {

                    int firstRandInt = rand1.nextInt(p1.size());
                    int secondRandInt = rand2.nextInt(p2.size());

                    while(collector1.contains(firstRandInt)){
                        firstRandInt = rand1.nextInt(p1.size());
                    }
                    collector1.add(firstRandInt);

                    while(collector2.contains(secondRandInt)){
                        secondRandInt = rand2.nextInt(p2.size());
                    }
                    collector2.add(secondRandInt);


                    String firstText = p1.get(firstRandInt);
                    p1View.setText(firstText);

                    String secondText = p2.get(secondRandInt);
                    p2View.setText(secondText);


                    // After every tap the strings in first and second array moving to bottom container
                    LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                    View listItemLayout= layoutInflater.inflate(R.layout.view_double_text, historyContainer, false);

                    TextView txt1 = listItemLayout.findViewById(R.id.txt1);
                    TextView txt2 = listItemLayout.findViewById(R.id.txt2);

                    txt1.setText(firstText);
                    txt2.setText(secondText);

                    historyContainer.addView(listItemLayout);

                    nextButton.setVisibility(View.GONE);
                    p1View.setVisibility(View.GONE);
                    p2View.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    startTitle.setText("Congartulations, Game has been finished!");
                    startTitle.setTextColor(Color.RED);
                    startTitle.setTextSize(21);
                }



               // p1.remove(firstRandInt);
               // p2.remove(secondRandInt);

            }
        });


    }


}
