package com.sflazarus.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sflazarus.trivia.controller.AppController;
import com.sflazarus.trivia.data.AnswerListAsyncResponse;
import com.sflazarus.trivia.data.QuestionBank;
import com.sflazarus.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView questionCounterTextView;
    private Button trueButton,falseButton;
    private ImageButton previousButton, nextButton;
    private int currentQuestionIndex=0;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton=findViewById(R.id.next_button);
        previousButton=findViewById(R.id.prev_button);
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        questionTextView=findViewById(R.id.question_textView);
        questionCounterTextView=findViewById(R.id.counter_textView);

        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);


        questionList=new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
//                questionList=questionArrayList;
                questionTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                questionCounterTextView.setText((currentQuestionIndex+1)+" out of "+questionList.size());
                Log.d("Inside Async", "processFinished: "+questionArrayList);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.next_button:
                currentQuestionIndex= (currentQuestionIndex+1)%questionList.size();
                updateQuestion();
                break;
            case R.id.prev_button:
                if (currentQuestionIndex!=0){
                    currentQuestionIndex=(currentQuestionIndex-1)%questionList.size();
                    updateQuestion();
                }else Toast.makeText(getApplicationContext(),"No more previous questions",Toast.LENGTH_SHORT).show();
                break;
            case R.id.true_button:
                checkAnswer(true);
                updateQuestion();
                break;
            case R.id.false_button:
                checkAnswer(false);
                updateQuestion();
                break;
            default:
                break;
        }
    }
    public void updateQuestion(){
        Log.d("Update", "updateQuestion: "+currentQuestionIndex);
        questionTextView.setText(questionList.get(currentQuestionIndex).getAnswer());

        questionCounterTextView.setText((currentQuestionIndex+1)+" out of "+questionList.size());
    }
    public void checkAnswer(Boolean userAnswer){
        Boolean actualAnswer = questionList.get(currentQuestionIndex).getAnswerTrue();

        if(actualAnswer==userAnswer){
            fadeView();
            Toast.makeText(getApplicationContext(),"Correct Response",Toast.LENGTH_SHORT).show();
        }
        else{
            shakeAnimation();
            Toast.makeText(getApplicationContext(),"Incorrect response",Toast.LENGTH_SHORT).show();
        }

    }
    private void shakeAnimation(){
        Animation shake= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake_animation);
        CardView cardView=findViewById(R.id.cardView);
        cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void fadeView(){
        CardView cardView=findViewById(R.id.cardView);
        AlphaAnimation alphaAnimation= new AlphaAnimation(1.0f,0.0f);

        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}