package com.ruifan_wang.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private EditText editText_display;
    private TextView textView_error;
    private boolean error_state;
    private final StringBuilder input_str = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_display = (EditText) findViewById(R.id.editView_display);
        textView_error = (TextView) findViewById(R.id.textView_error);
    }

    public void num(View view) {
        Button button = (Button) view;
        editText_display.append(button.getText());
        input_str.append(button.getText());
    }

    public void clear(View view) {
        editText_display.setText(null);
        textView_error.setText(null);
        input_str.delete(0, input_str.length());
    }

    public void delete(View view) {
        if (editText_display.length() != 0 && input_str.length() != 0) {
            String now_display = editText_display.getText().toString();
            editText_display.setText(now_display.substring(0, now_display.length() - 1));
            input_str.deleteCharAt(input_str.length() - 1);
        }
        textView_error.setText(null);
    }

    public void sqrt(View view) {
        editText_display.append("√(");
        input_str.append("k(");
    }

    public void ln(View view) {
        editText_display.append("ln(");
        input_str.append("n(");
    }

    public void lg(View view) {
        editText_display.append("lg(");
        input_str.append("g(");
    }

    public void log(View view) {
        editText_display.append("log(");
        input_str.append("l(");
    }

    public void pi(View view) {
        editText_display.append("π");
        input_str.append("p");
    }

    public void tan(View view) {
        editText_display.append("tan(");
        input_str.append("t(");
    }

    public void cos(View view) {
        editText_display.append("cos(");
        input_str.append("c(");
    }

    public void sin(View view) {
        editText_display.append("sin(");
        input_str.append("s(");
    }

    public void e(View view) {
        editText_display.append("e");
        input_str.append("e");
    }

    public void ex(View view) {
        editText_display.append("e^(");
        input_str.append("e^(");
    }

    public void x2(View view) {
        editText_display.append("^2");
        input_str.append("^2");
    }

    public void xy(View view) {
        editText_display.append("^(");
        input_str.append("^(");
    }

    public void left_bracket(View view) {
        editText_display.append("(");
        input_str.append("(");
    }

    public void right_bracket(View view) {
        editText_display.append(")");
        input_str.append(")");
    }

    public void divide(View view) {
        editText_display.append("÷");
        input_str.append("/");
    }

    public void multiply(View view) {
        editText_display.append("×");
        input_str.append("*");
    }

    public void subtract(View view) {
        editText_display.append("-");
        input_str.append("-");
    }

    public void add(View view) {
        editText_display.append("+");
        input_str.append("+");
    }

    public void percentage(View view) {
        editText_display.append("%");
        input_str.append("*0.01");
    }

    public void dot(View view) {
        editText_display.append(".");
        input_str.append(".");
    }

    public void equal(View view) {
        error_state = false;
        judge();
        if (!error_state) {
            textView_error.setText("输入有效");
        }
    }

    public void judge() {
        if (input_str.length() == 0) {
            textView_error.setText("输入不能为空！");
            error_state = true;
        }
        if (input_str.length() == 1 && ("0123456789ep".indexOf(input_str.charAt(0)) == -1)) {
            textView_error.setText("没有输入数字或常数！");
            error_state = true;
        }
        if(input_str.charAt(0)=='0'&&input_str.charAt(1)=='0'){
            textView_error.setText("不能只输入0！");
            error_state=true;
        }
        if (input_str.length() > 1) {
            if ("kngltcs(123456789ep".indexOf(input_str.charAt(0)) == -1) {
                textView_error.setText("首个字符无效！");
                error_state = true;
            }
            for (int i = 0; i < input_str.length() - 1; i++) {
                if ("+-*/".indexOf(input_str.charAt(i)) >= 0 && "kngltcs(0123456789ep".indexOf(input_str.charAt(i + 1)) == -1) {
                    textView_error.setText("运算符号不能重复出现！");
                    error_state = true;
                }
                if (".".indexOf(input_str.charAt(i)) >= 0 && "0123456789".indexOf(input_str.charAt(i + 1)) == -1) {
                    textView_error.setText("小数点后只能为数字！");
                    error_state = true;
                }
                if ("kngltcs".indexOf(input_str.charAt(i)) >= 0 && "0123456789ep".indexOf(input_str.charAt(i + 2)) == -1) {
                    textView_error.setText("运算符号不能连续出现！");
                    error_state = true;
                }
                if ("123456789".indexOf(input_str.charAt(i)) >= 0 && "0123456789ep+-*/.)^".indexOf(input_str.charAt(i + 1)) == -1) {
                    textView_error.setText("数字后不能直接与运算符号相连接！");
                    error_state = true;
                }
                if ("(".indexOf(input_str.charAt(i)) >= 0 && "kngltcs()0123456789ep".indexOf(input_str.charAt(i + 1)) == -1) {
                    textView_error.setText("括号内的符号无效！");
                    error_state = true;
                }
                if (")".indexOf(input_str.charAt(i)) >= 0 && "+-*/^)".indexOf(input_str.charAt(i + 1)) == -1) {
                    textView_error.setText("括号后的符号无效！");
                    error_state = true;
                }
                if ("ep".indexOf(input_str.charAt(i)) >= 0 && "+-*/^)".indexOf(input_str.charAt(i + 1)) == -1) {
                    textView_error.setText("小数点后的符号无效！");
                    error_state = true;
                }
                if (i >= 1 && input_str.charAt(i) == '0') {
                    int zero_position = i;
                    int j = i - 1;
                    boolean state_dot = false;
                    if ("0123456789.".indexOf(input_str.charAt(zero_position - 1)) == -1 && "+-*/.^".indexOf(input_str.charAt(zero_position + 1)) == -1) {
                        textView_error.setText("0无效");
                        error_state = true;
                    }
                    if (input_str.charAt(zero_position-1)=='.'&& "0123456789+-*/^".indexOf(input_str.charAt(zero_position + 1)) == -1) {
                        textView_error.setText("0无效");
                        error_state = true;
                    }
                    while (j > 0) {
                        if ("(+-*/^kngltsc".indexOf(input_str.charAt(j)) >= 0) {
                            break;
                        }
                        if (".".indexOf(input_str.charAt(j))==0) {
                            state_dot = true;
                        }
                        if (".".indexOf(input_str.charAt(j))==0 && state_dot) {
                            textView_error.setText("输入多个小数点！");
                            error_state = true;
                        }
                        j--;
                    }
                    if ((!state_dot&&input_str.charAt(j)=='0')||"0123456789+-*/.!^)".indexOf(input_str.charAt(zero_position+1))==-1){
                        textView_error.setText("0无效！");
                        error_state=true;
                    }
                    if(state_dot&&"0123456789+-*/.^)".indexOf(input_str.charAt(zero_position+1))==-1){
                        textView_error.setText("0无效！");
                        error_state=true;
                    }
                }
            }
        }
    }
}