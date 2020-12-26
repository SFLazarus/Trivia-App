package com.sflazarus.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sflazarus.trivia.controller.AppController;
import com.sflazarus.trivia.data.QuestionBank;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new QuestionBank().getQuestions();
    }
}