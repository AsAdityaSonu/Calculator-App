package com.aswiz.adityapandey102217092;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Result1 , input1;

    MaterialButton buttonC,buttonOpenLeft,buttonOpenRight,buttonDivide;
    MaterialButton button7,button8,button9,buttonMultiply;
    MaterialButton button4,button5,button6,buttonPlus;
    MaterialButton button1,button2,button3,buttonMinus;
    MaterialButton buttonAC,button0,buttonDot,buttonEquals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result1 = findViewById(R.id.result);
        input1 = findViewById(R.id.input);

        assignment(buttonC,R.id.button_c);
        assignment(button0,R.id.button_0);
        assignment(button1,R.id.button_1);
        assignment(button2,R.id.button_2);
        assignment(button3,R.id.button_3);
        assignment(button4,R.id.button_4);
        assignment(button5,R.id.button_5);
        assignment(button6,R.id.button_6);
        assignment(button7,R.id.button_7);
        assignment(button8,R.id.button_8);
        assignment(button9,R.id.button_9);
        assignment(buttonAC,R.id.button_ac);
        assignment(buttonDivide,R.id.button_divide);
        assignment(buttonMultiply,R.id.button_multiply);
        assignment(buttonPlus,R.id.button_plus);
        assignment(buttonMinus,R.id.button_minus);
        assignment(buttonDot,R.id.button_dot);
        assignment(buttonEquals,R.id.button_equals);
        assignment(buttonOpenLeft,R.id.button_open_left);
        assignment(buttonOpenRight,R.id.button_open_right);
    }



    void assignment(MaterialButton btn,int id){
        btn =findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = input1.getText().toString();


        if(buttonText.equals("AC")){
            input1.setText("");
            Result1.setText("");
            return;
        }
        if(buttonText.equals("=")){
            input1.setText(Result1.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        input1.setText((dataToCalculate));
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            Result1.setText((finalResult));
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;

        } catch (Exception e){
            return "Error";
        }
    }
}
