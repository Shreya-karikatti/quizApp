package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totalQysetionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQysetionTextView = findViewById(R.id.total_Questions);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQysetionTextView.setText("Total question : "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(getColor(android.R.color.white));
        ansB.setBackgroundColor(getColor(android.R.color.white));
        ansC.setBackgroundColor(getColor(android.R.color.white));
        ansD.setBackgroundColor(getColor(android.R.color.white));


        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){

            if(selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(getColor(android.R.color.holo_blue_dark));
        }


    }

    void loadNewQuestion(){
        
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus ="Failed";
        }


        new AlertDialog.Builder( this)
                .setTitle(passStatus)
                .setMessage("Score is" + score + "out of" + totalQuestion)
                .setPositiveButton("Restat",(dialogInterface , i)->restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0 ;
        currentQuestionIndex = 0;
        loadNewQuestion();

    }


}