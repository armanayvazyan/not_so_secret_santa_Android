package com.example.notsosecretsanta;

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

    private List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        final TextView p1View = findViewById(R.id.player1);
        final TextView p2View = findViewById(R.id.player2);
        final Button nextButton = findViewById(R.id.nextBtn);
        final LinearLayout historyContainer = findViewById(R.id.textHistoryContainer);
        final LinearLayout itemHistoryContainer = findViewById(R.id.itemHistoryContainer);

        final List<String> p1 = AppSettings.getInstance().getItemsFromPreferences(getApplicationContext());


        final List<String> p2 = new ArrayList<String>();
        for (String string: p1) {
            p2.add(string);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Random firstView = new Random();
                Random secondView = new Random();
                if (p1.isEmpty()){
                    Toast.makeText(StartGameActivity.this,"There are no players anymore",Toast.LENGTH_SHORT).show();
                    return;
                }
                int firstRandInt = firstView.nextInt(p1.size());
                int secondRandInt = secondView.nextInt(p2.size());

                while (secondRandInt == firstRandInt && !(secondRandInt == 0 && firstRandInt == 0)) {
                    secondRandInt = secondView.nextInt(p2.size());
                }

                String firstText = p1.get(firstRandInt);
                p1View.setText(firstText);

                String secondText = p2.get(secondRandInt);
                p2View.setText(secondText);

                historyList.add(firstText);
                historyList.add(secondText);

                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());

                View listItemLayout= layoutInflater.inflate(R.layout.view_double_text, itemHistoryContainer, false);
                TextView txt1 = listItemLayout.findViewById(R.id.txt1);
                TextView txt2 = listItemLayout.findViewById(R.id.txt2);
                txt1.setText(firstText);
                txt2.setText(secondText);
                historyContainer.addView(listItemLayout);

                nextButton.setText("Next");

                p1.remove(firstRandInt);
                p2.remove(secondRandInt);

            }
        });


    }


}
