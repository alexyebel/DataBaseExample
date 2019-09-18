package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String TABLE_NAME = "people_table";
    final String CREATE_TABLE_CMD="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, first TEXT, last TEXT);";

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=openOrCreateDatabase("database.sql",MODE_PRIVATE,null);
        database.execSQL(CREATE_TABLE_CMD);


        ListView listView=findViewById(R.id.persons_list);

        final EditText firstEt = findViewById(R.id.first_input);
        final EditText lastEt = findViewById(R.id.last_input);

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Button allBtn = findViewById(R.id.all_btn);
        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button filterBtn = findViewById(R.id.filter_btn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
