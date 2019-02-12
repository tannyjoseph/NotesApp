package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class Main2Activity extends AppCompatActivity {

    EditText note;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         note = findViewById(R.id.editText);

        Intent intent = getIntent();
          noteId = intent.getIntExtra("NoteId", -1);

        if (noteId != -1){
            note.setText(MainActivity.notes.get(noteId));
        }

        else {

            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

        }

        note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.notes.set(noteId,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet(MainActivity.notes);

                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}
