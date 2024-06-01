package com.example.quiz_app;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalQuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button btn_Submit;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex =0;
    String selectedAnswer="";

    @Override

    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    totalQuestionTextView = findViewById(R.id.total_question);
    questionTextView = findViewById(R.id.question);
    ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        btn_Submit = findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_Submit.setOnClickListener(this);

        totalQuestionTextView.setText("Total question: "+totalQuestion);

        loadNewQuestion();
    }

    private void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer="";

    }

    private void finishQuiz(){
        String passStatus;
        if(score >= totalQuestion*0.6){
            passStatus = "Passed";
        }
        else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score id "+score+"Out of "+totalQuestion)
                .setPositiveButton("Restart",((dailog,i) -> restartQuiz() ))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz(){
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){

        ansA.setBackgroundColor(android.R.color.white);
        ansB.setBackgroundColor(android.R.color.white);
        ansC.setBackgroundColor(android.R.color.white);
        ansD.setBackgroundColor(android.R.color.white);

        Button clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.btn_submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }
                else{
                    clickedButton.setBackgroundColor(android.R.color.black);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
        }
        else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(android.R.color.black);
        }
    }
}