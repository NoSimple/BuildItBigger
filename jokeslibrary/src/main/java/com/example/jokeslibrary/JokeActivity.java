package com.example.jokeslibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public final class JokeActivity extends AppCompatActivity {

    private TextView jokeText;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeText = findViewById(R.id.text_joke);
        okButton = findViewById(R.id.btn_ok);

        if (getIntent() != null) {
            jokeText.setText(getIntent().getStringExtra("KeyJoke"));
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}