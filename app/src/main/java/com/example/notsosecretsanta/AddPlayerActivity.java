package com.example.notsosecretsanta;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerActivity extends AppCompatActivity {

    EditText addPlayerText;
    ImageView addPlayerBtn;
    ListView listView;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        listView = findViewById(R.id.listView);


        List<String> savedItems = AppSettings.getInstance().getItemsFromPreferences(getApplicationContext());
        if (!savedItems.isEmpty()){
            list.addAll(savedItems);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }

        addPlayerText = findViewById(R.id.addNewPlayerTextView);
        addPlayerBtn = findViewById(R.id.addBtn);
        addPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textField= addPlayerText.getText().toString();
                if(!textField.isEmpty() && !textField.trim().isEmpty()){
                    list.add(textField);
                    AppSettings.getInstance().saveListToPreferences(list, getApplicationContext());
                    addPlayerText.setText(null);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(AddPlayerActivity.this,"Please fill the field",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void removeList(View view){
        list.clear();
        AppSettings.getInstance().removeSavedItems(getApplicationContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

}
