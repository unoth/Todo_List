package com.unoth.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.unoth.todolost.R;

public class AddNoteActivity extends AppCompatActivity {

    private EditText edInput;
    private Button btnSave;
    private RadioButton radioLow;
    private RadioButton radioMedium;
    private RadioButton radioHigh;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewNote();
            }
        });
    }

    private void initViews() {
        edInput = findViewById(R.id.edInput);
        btnSave = findViewById(R.id.btnSave);
        radioLow = findViewById(R.id.radioLow);
        radioMedium = findViewById(R.id.radioMedium);
        radioHigh = findViewById(R.id.radioHigh);
    }

    private void saveNewNote() {
        String text = edInput.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(
                    AddNoteActivity.this,
                    getString(R.string.error_empty),
                    Toast.LENGTH_SHORT
            ).show();
        }
        int priority = getPriority();
        int id = database.getNotes().size();
        Note note = new Note(id,text,priority);
        database.add(note);

        finish();
    }

    private int getPriority() {
        int priority;
        if (radioLow.isChecked()) {
            priority = 0;
        } else if (radioMedium.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }

}