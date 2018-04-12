package com.example.juanlopez.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class noteDetailsActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1)
        {
            editText.setText(MainActivity.noteList.get(noteId));
        }
        else
        {
            MainActivity.noteList.add("");
            noteId =  MainActivity.noteList.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.noteList.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().
                        getSharedPreferences("com.example.juanlopez.notes", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet(MainActivity.noteList);

                sharedPreferences.edit().putStringSet("notes", set).apply();




            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
