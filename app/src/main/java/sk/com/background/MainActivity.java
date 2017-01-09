package sk.com.background;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    int userResult, result;
    int firstNum, ramdom_variable = 1;
    int secondNum;
    EditText etxt;
    TextView number1, number2, tvoperator;
    Random random = new Random();
    Timer timerObj = new Timer();
    int maxtime = 10000;
    String[] operators = {"+", "-", "*", "/"};
    int opt;

    void schedule() {
        timerObj.cancel();

        timerObj = new Timer("", true);
        final MyTimer timerTaskObj = new MyTimer();
        timerObj.schedule(timerTaskObj, maxtime);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        schedule();


        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);


        etxt = (EditText) findViewById(R.id.edit_txt);
        number1 = (TextView) findViewById(R.id.number1);
        number2 = (TextView) findViewById(R.id.number2);
        tvoperator = (TextView) findViewById(R.id.operatortv);

        ColorDrawable[] BackGroundColor = {
                new ColorDrawable(Color.parseColor("#fa0707")),
                new ColorDrawable(Color.parseColor("#3507fa")),
                new ColorDrawable(Color.parseColor("#07faec")),
                new ColorDrawable(Color.parseColor("#faec07"))

        };

        final TransitionDrawable transitionDrawable = new TransitionDrawable(BackGroundColor);

        firstNum = random.nextInt(5) + 1;
        secondNum = random.nextInt(5) + 1;
        number1.setText(String.valueOf(firstNum));
        number2.setText(String.valueOf(secondNum));
        tvoperator.setText("+");
        result = firstNum + secondNum;

        linearLayout.setBackground(transitionDrawable);
        transitionDrawable.reverseTransition(maxtime);


        etxt.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                //Log.e("value", String.valueOf(firstNum));
                Log.e("afterTextChanged", "afterTextChanged");
                String dump = etxt.getText().toString();
                if (dump.isEmpty()) {
                    Log.e("NullBlock", "NullBock");
                    // Toast.makeText(MainActivity.this,"Enter value",Toast.LENGTH_LONG).show();
                } else {


                    Log.e("NotNull/block", "NotNull");


                    userResult = Integer.parseInt(dump);

                    Log.e("userResult", String.valueOf(userResult) + "  " + result);

                    if (userResult != result) {
                        Log.e("Not equal block", "Values are not equal block");
                        //Toast.makeText(MainActivity.this, "Your Answer is wrong", Toast.LENGTH_LONG).show();

                        //etxt.setText("");
                    } else {
                        schedule();
                        Log.e("Else block", String.valueOf(userResult) + result);
                        Toast.makeText(MainActivity.this, "Your Answer is crt", Toast.LENGTH_SHORT).show();


                        linearLayout.setBackground(transitionDrawable);
                        transitionDrawable.startTransition(10000);


                        firstNum = random.nextInt(15 * ramdom_variable) + 1;
                        secondNum = random.nextInt(15 * ramdom_variable) + 1;
                        if (ramdom_variable < 50) {
                            ramdom_variable++;
                        }
                        //New operators  and geeting userResult
                        opt = random.nextInt(operators.length - 2);
                        Log.e("Random Operator", String.valueOf(opt));
                        switch (operators[opt]) {
                            case "+":
                                result = firstNum + secondNum;
                                break;
                            case "-":
                                result = firstNum - secondNum;
                                result = Math.abs(result);
                                break;
                            case "*":
                                result = firstNum * secondNum;
                                break;
                            case "/":
                                result = firstNum / secondNum;
                                break;

                        }

                        number1.setText(String.valueOf(firstNum));
                        number2.setText(String.valueOf(secondNum));
                        tvoperator.setText(operators[opt]);
                        etxt.setText("");
                    }
                }

            }
        });


    }

    class MyTimer extends TimerTask {
        @Override
        public void run() {

            Intent inta = new Intent(getApplicationContext(), FirstPage.class);

            startActivity(inta);


        }
    }
}






