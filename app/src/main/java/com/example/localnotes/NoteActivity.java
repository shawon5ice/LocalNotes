package com.example.localnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashMap;
import java.util.HashSet;

public class NoteActivity extends AppCompatActivity {

    int noteID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
         noteID = intent.getIntExtra("noteID", -1);
         if(noteID!=-1) {
            editText.setText(MainActivity.notes.get(noteID));
        }else {
             MainActivity.notes.add("");
             noteID = MainActivity.notes.size()-1;
         }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteID, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.localnotes", Context.MODE_PRIVATE);
                HashSet<String> noteset = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",noteset).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}