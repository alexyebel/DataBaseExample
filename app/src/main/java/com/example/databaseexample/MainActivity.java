package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        final ListView listView=findViewById(R.id.persons_list);

        final ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        final EditText firstEt = findViewById(R.id.first_input);
        final EditText lastEt = findViewById(R.id.last_input);

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=firstEt.getText().toString();
                String last=lastEt.getText().toString();

                ContentValues contentValues=new ContentValues();
                contentValues.put("first",first);
                contentValues.put("last",last);

                database.insert(TABLE_NAME,null,contentValues);
                firstEt.setText("");
                lastEt.setText("");
            }
        });


        Button allBtn = findViewById(R.id.all_btn);
        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();
                //Cursor is a pointer in table
                Cursor cursor=database.query(TABLE_NAME,null,null,null,null,null,null);
                int firstNameIndex=cursor.getColumnIndex("first");
                int lastNameIndex=cursor.getColumnIndex("last");

                while (cursor.moveToNext()){
                    String person=cursor.getString(firstNameIndex)+" "+cursor.getString(lastNameIndex)+"";

                    adapter.add(person);
                }
                adapter.notifyDataSetChanged();
            }
        });

        Button filterBtn = findViewById(R.id.filter_btn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();

                String filter=firstEt.getText().toString();

                Cursor cursor=database.query(TABLE_NAME,new  String[]{"id","first"},"first like '%"+filter+"%'",null,null,null,null);
                int firstNameIndex=cursor.getColumnIndex("first");

                while (cursor.moveToNext()){
                    //String person=cursor.getString(firstNameIndex);
                    //adapter.add(person);
                     adapter.add(cursor.getString(firstNameIndex));
                }
                adapter.notifyDataSetChanged();


            }
        });
        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldName=firstEt.getText().toString();
                String newName=lastEt.getText().toString();

                ContentValues contentValues= new ContentValues();
                contentValues.put("first",newName);

                int count=database.update(TABLE_NAME,contentValues,"first Like '"+oldName+"'",null);
                Toast.makeText(MainActivity.this, count+" records updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
