package com.sflazarus.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sflazarus.trivia.controller.AppController;
import com.sflazarus.trivia.data.AnswerListAsyncResponse;
import com.sflazarus.trivia.data.QuestionBank;
import com.sflazarus.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Question> questionList=new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d("Inside Async", "processFinished: "+questionArrayList);
            }
        });
    }
}