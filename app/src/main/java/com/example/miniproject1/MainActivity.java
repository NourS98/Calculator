package com.example.miniproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.miniproject1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    boolean resultShown = false;
    private static Stack<Character> operator = new Stack<>();

    private static ArrayList<String> expressionToken = new ArrayList<>();

    private static Stack<String> operand = new Stack<>();

    private String equation="";

    private Double result ;

  //  ActivityMainBinding binding;
    private TextView screen;
    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonFour;
    private Button buttonThree;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonSubtraction;
    private Button buttonDivision;
    private Button buttonAddition;
    private Button buttonMultiplication;
    private Button buttonEqual;
    private Button buttonPoint;
    private Button buttonClear;
    private Button buttonPercent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
          setContentView(R.layout.activity_main);
          screen=findViewById(R.id.screen);
          buttonZero=findViewById(R.id.button_zero);
          buttonOne=findViewById(R.id.button_one);
          buttonTwo=findViewById(R.id.button_two);
          buttonThree=findViewById(R.id.button_three);
          buttonFour=findViewById(R.id.button_four);
          buttonFive=findViewById(R.id.button_five);
          buttonSix=findViewById(R.id.button_six);
          buttonSeven=findViewById(R.id.button_seven);
          buttonEight=findViewById(R.id.button_eight);
          buttonNine=findViewById(R.id.button_nine);
          buttonAddition=findViewById(R.id.button_addition);
          buttonSubtraction=findViewById(R.id.button_subtraction);
          buttonDivision=findViewById(R.id.button_division);
          buttonMultiplication=findViewById(R.id.button_multiplication);
          buttonEqual=findViewById(R.id.button_equal);
          buttonClear=findViewById(R.id.button_clear);
          buttonPoint=findViewById(R.id.button_point);
          buttonPercent=findViewById(R.id.button_percent);





        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("0");
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("1");
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("2");
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("3");
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("4");
            }
        });

        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("5");
            }
        });


        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("6");
            }
        });

        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("7");
            }
        });

        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("8");
            }
        });

        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("9");
            }
        });

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    displayText("+");

            }


        });

        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("-");
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("/");

            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("x");

            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("%");
            }
        });
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText(".");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClear.setText("AC");
                result =evaluteExpression(equation);
                if (Double.isNaN(result)) {
                    screen.setText("Syntax Error");
                } else {
                    screen.setText(result.toString());

                }
                resultShown=true;

            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultShown){
                    equation="";
                    screen.setText("0");
                    
                }
                else{
                    if(equation.length()>0){
                    String backSpacedData = equation.substring(0, equation.length() - 1);
                    equation=backSpacedData;
                    if (backSpacedData.equals(""))
                        backSpacedData = "0";

                    screen.setText(backSpacedData);}
                }
            }
        });
    }

    private void displayText(String input){
        buttonClear.setText("CE");
        resultShown=false;
        if(screen.getText().toString().equals("0")){
            screen.setText("");
            equation="";
        }
        equation =screen.getText().toString().trim()+input;
        screen.setText(equation);

    }

    private static int isOperator(char c) {
        if (c == '+' || c == '-'|| c == 'x' ||c == '%' )
            return 1;
        else
            return -1;
    }

    private static double calculate(double x, double y, char operator) {
        if (operator == '+')
            return x + y;
        else if (operator == '-')
            return x - y;
        else if (operator == 'x')
            return x * y;
        else if (operator == '/')
            return x / y;
        else if (operator == '%')
            return x % y;

        return 0.0; // Should never reach
    }

    private double evaluteExpression(String expression) {

        expressionToken.clear();
        operand.clear();
        operator.clear();


        char[] expressionArr = expression.toCharArray();
        String oper = "";
        try{
        for (int i = 0; i < expressionArr.length; i++) {
            if (isOperator(expressionArr[i]) != -1 && i>0) {
                expressionToken.add(oper);
                oper = "";
                expressionToken.add(String.valueOf(expressionArr[i]));
            } else
                oper += expressionArr[i];
        }

        expressionToken.add(oper);

        for (String k : expressionToken) {
            if ( isOperator(k.charAt(0))!= -1 && k.length()==1 ) {
                operator.push(k.charAt(0));

            } else {
                operand.push(k);
            }
        }
        while (!operator.empty()) {
            double val1 = Double.valueOf(operand.pop());
            double val2 = Double.valueOf(operand.pop());
            double res = calculate(val2, val1, operator.pop());

            operand.push(String.valueOf(res));
        }
          result = Double.valueOf(operand.peek());
        return result;

        }
        catch (Exception e) {
            return Double.NaN;
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("resultShown",resultShown);
  //      outState.putCharSequenceArrayList("operator",operator);
        outState.putStringArrayList("expressionToken",expressionToken);
    //    outState.putBoolean("operand",operand);
        outState.putString("equation",equation);
        outState.putDouble("result",result);



    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resultShown=savedInstanceState.getBoolean("resultShown");
        expressionToken=savedInstanceState.getStringArrayList("expressionToken");
        equation= savedInstanceState.getString("equation");
        result=savedInstanceState.getDouble("result");
        if(resultShown){
            screen.setText(String.valueOf(result));}
        else{
            screen.setText(String.valueOf(equation));
        }


    }
}





