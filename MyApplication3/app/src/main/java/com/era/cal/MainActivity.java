package com.era.cal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // 定义各个组件
    // 数字按键
    private Button button_zero;
    private Button button_one;
    private Button button_two;
    private Button button_three;
    private Button button_four;
    private Button button_five;
    private Button button_six;
    private Button button_seven;
    private Button button_eight;
    private Button button_nine;
    private Button button_point;

    // 功能按键
    private Button button_clear;
    private Button button_delete;
    private Button button_equal;
    private Button button_history;

    // 操作符按键
    private Button button_add;
    private Button button_sub;
    private Button button_mul;
    private Button button_div;
    private Button button_left_bracket;
    private Button button_right_bracket;


    // 屏幕部分
    private EditText screen;    // 表达式显示屏
    private TextView screen_result;     // 结果部分


    // 算术表达式存储
    private String expression;

    // 存储计算结果的可变长数组
    ArrayList<String> al = new ArrayList<>();


    // 清除历史记录按键
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 组件初始化
        button_zero = findViewById(R.id.button_zero);
        button_one = findViewById(R.id.button_one);
        button_two = findViewById(R.id.button_two);
        button_three = findViewById(R.id.button_three);
        button_four = findViewById((R.id.button_four));
        button_five = findViewById(R.id.button_five);
        button_six = findViewById(R.id.button_six);
        button_seven = findViewById(R.id.button_seven);
        button_eight = findViewById(R.id.button_eight);
        button_nine = findViewById(R.id.button_nine);
        button_point = findViewById(R.id.button_point);

        button_clear = findViewById(R.id.clear);
        button_delete = findViewById(R.id.delete);
        button_equal = findViewById(R.id.button_equal);
        button_history = findViewById(R.id.button_history);

        button_add = findViewById(R.id.button_add);
        button_sub = findViewById(R.id.button_sub);
        button_mul = findViewById(R.id.button_mul);
        button_div = findViewById(R.id.button_div);
        button_left_bracket = findViewById(R.id.button_left_bracket);
        button_right_bracket = findViewById(R.id.button_right_bracket);

        screen = findViewById(R.id.screen);
        screen_result = findViewById(R.id.screen_result);

        expression = null;
        btn = findViewById(R.id.empty);

        // 初始化部分完成

        // 监听各个按键
        btn.setOnClickListener(this);
        button_zero.setOnClickListener(this);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener(this);
        button_four.setOnClickListener(this);
        button_five.setOnClickListener(this);
        button_six.setOnClickListener(this);
        button_seven.setOnClickListener(this);
        button_eight.setOnClickListener(this);
        button_nine.setOnClickListener(this);
        button_point.setOnClickListener(this);

        button_clear.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_history.setOnClickListener(this);

        button_add.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_mul.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_equal.setOnClickListener(this);

        button_left_bracket.setOnClickListener(this);
        button_right_bracket.setOnClickListener(this);


        // 设置按键监听完成



    }



    @Override
    public void onClick(View v) {
        expression = screen.getText().toString();
        Animation alphaAnimation = new AlphaAnimation(0.1f, 0);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(5);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        v.startAnimation(alphaAnimation);

        if(v.getId() == R.id.empty){
            clearAL();
        }

        // 显示屏初始化为0，对初始化数据的处理
        if(expression.equals("0")){
            switch (v.getId()){
                case R.id.button_add:
                    setTextOnScreen(expression.concat("+"));
                    break;
                case R.id.button_sub:
                    setTextOnScreen(expression.concat("-"));
                    break;
                case R.id.button_div:
                    setTextOnScreen(expression.concat("÷"));
                    break;
                case R.id.button_mul:
                    setTextOnScreen(expression.concat("×"));
                    break;
                case R.id.button_point:
                    setTextOnScreen(expression.concat("."));
                    break;
                case R.id.button_one:
                case R.id.button_two:
                case R.id.button_three:
                case R.id.button_four:
                case R.id.button_five:
                case R.id.button_six:
                case R.id.button_seven:
                case R.id.button_eight:
                case R.id.button_nine:
                    setTextOnScreen(((Button) v).getText().toString());
                    break;
                case R.id.clear:
                    setTextOnScreen("0");
                    break;
                case R.id.button_left_bracket:
                    setTextOnScreen("(");
                    break;
                case R.id.button_history:
                    jumpToHistory();
                    break;
            }
        }

        // 等号按下后，对显示屏的处理
        else if(expression.charAt(expression.length()-1) == '='){

            // 常规数字处理
            switch (v.getId()){
                case R.id.button_one:
                case R.id.button_two:
                case R.id.button_three:
                case R.id.button_four:
                case R.id.button_five:
                case R.id.button_six:
                case R.id.button_seven:
                case R.id.button_eight:
                case R.id.button_nine:
                    setTextOnScreen(((Button) v).getText().toString());
                    screen_result.setText("0");
                    break;
                case R.id.button_zero:
                    setTextOnScreen("0.");
                    screen_result.setText("0");
                    break;
                case R.id.clear:
                case R.id.delete:
                case R.id.button_right_bracket:
                    setTextOnScreen("0");
                    screen_result.setText("0");
                    break;
                case R.id.button_left_bracket:
                    setTextOnScreen("(");
                    screen_result.setText("0");
                    break;
                case R.id.button_add:
                case R.id.button_sub:
                case R.id.button_div:
                case R.id.button_mul:
                    setTextOnScreen(screen_result.getText().toString().
                            concat(((Button) v).getText().toString()));
                    screen_result.setText("0");
                    break;
                case R.id.button_history:
                    jumpToHistory();
                    break;
            }
            // 结果显示栏清零


        }

        else{
            switch (v.getId()){
                case R.id.button_zero:
                    char input = expression.charAt(expression.length()-1);
                    // 避免除以0的情况发生
                    if(input == '÷'){
                        setTextOnScreen(expression.concat("0."));
                        break;
                    }
                    if(judgeZero(expression)){
                        setTextOnScreen(expression.concat("0"));
                    }
                    break;
                case R.id.button_one:
                case R.id.button_two:
                case R.id.button_three:
                case R.id.button_four:
                case R.id.button_five:
                case R.id.button_six:
                case R.id.button_seven:
                case R.id.button_eight:
                case R.id.button_nine:
                    if(judgeDigit(expression, v)){
                        setTextOnScreen(expression.concat(((Button) v).getText().toString()));
                    }
                    break;
                case R.id.clear:
                    setTextOnScreen("0");
                    break;
                case R.id.button_add:
                case R.id.button_sub:
                case R.id.button_div:
                case R.id.button_mul:
                    if(judgeOperator(expression)){
                        setTextOnScreen(expression.concat(((Button) v).getText().toString()));
                    }
                    break;
                case R.id.button_point:
                    if(judgePoint(expression, v)){
                        setTextOnScreen(expression.concat("."));
                    }
                    break;
                case R.id.button_left_bracket:
                    if(judgeLeftBracket(expression)){
                        setTextOnScreen(expression.concat("("));
                    }
                    break;
                case R.id.button_right_bracket:
                    if(judgeRightBracket(expression)){
                        setTextOnScreen(expression.concat(")"));
                    }
                    break;
                case R.id.button_equal:
                    if(judgeEqual(expression)){
                        setTextOnScreen(expression.concat("="));
                        double result = calculate(expression);
                        screen_result.setText(String.valueOf(result));
                        al.add(expression.concat("=").concat(String.valueOf(result)));
                    }
                    break;
                case R.id.button_history:
                    jumpToHistory();
                    break;
                case R.id.delete:
                    if(judgeDelete(expression)){

                        char lastInput = expression.charAt(expression.length()-1);
                        if(lastInput == '.' && expression.charAt(expression.length()-2) == '0' && expression.length() > 2){
                            setTextOnScreen(expression.substring(0, expression.length()-2));
                        }

                        else if(lastInput == '.' && expression.charAt(expression.length()-2) == '0' && expression.length() <= 2){
                            setTextOnScreen("0");
                        }

                        else{
                            setTextOnScreen(expression.substring(0, expression.length()-1));
                        }
                    }
                    else {
                        setTextOnScreen("0");
                    }

                    break;
                    }


        }


    }

    // 向Screen上写入文本的函数
    private void setTextOnScreen(String str){
        screen.setText(str);
        screen.setSelection(screen.getText().length());
    }




    /*
    控制输入表达式正确模块
     */

    // 是数字的判断
    private boolean isDigit(char c){
        switch (c){
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':   return true;
            default:    return  false;
        }
    }

    // 是加减乘除的判断
    private  boolean isOperater(char c){
        switch (c){
            case '+': case '-': case '×': case '÷': return true;
            default: return false;
        }
    }

    // 是小数点的判断
    private  boolean isPoint(char c){
       return c == '.';
    }



    // 输入为0的判断
    private boolean judgeZero(String oldExpress){
        char lastInput = oldExpress.charAt(oldExpress.length()-1);
        StringBuilder sb = new StringBuilder(oldExpress);
        // 先把字符添加上去，然后用这则表达式去测试一下是不是会不符合要求
        String temp = sb.append('0').toString();
        if(temp.contains("+00") || temp.contains("-00") || temp.contains("×00") ||
                temp.contains("÷00") || temp.contains("（00") || temp.equals("00")){
            return false;
        }

        // 如果是数字，和加减乘除，左括号，小数点后，都可添加0
        return (isDigit(lastInput) || isOperater(lastInput) || lastInput == '(' || isPoint(lastInput));
    }

    // 判断是否能向屏幕上添加1-9的数字
    public boolean judgeDigit(String oldExpress, View v){
        char lastInput = oldExpress.charAt(oldExpress.length()-1);
        StringBuilder sb = new StringBuilder(oldExpress);
        String enterString = ((Button) v).getText().toString();
        String temp = sb.append(enterString).toString();

        // 避免写出002这样的数字来，但又要保证1002这样的数字的存在
        if(temp.contains("+0".concat(enterString)) || temp.contains("-0".concat(enterString)) ||
            temp.contains("×0".concat(enterString)) || temp.contains("÷0".concat(enterString)) ||
            temp.contains("(0".concat(enterString))){
            return false;
        }
        // 如果前一个字符是数字，加减乘除，小数点，左括号可添加数字
        return (isDigit(lastInput) || isOperater(lastInput) || lastInput == '(' || isPoint(lastInput));
    }

    // 判断是否能向屏幕上添加加减乘除
    public boolean judgeOperator(String oldExpress){
        char lastInput = oldExpress.charAt(oldExpress.length()-1);
        return (isDigit(lastInput) ||  lastInput == ')');
    }

    // 判断是否能加小数点
    public boolean judgePoint(String oldExpress, View v){
        String enterString = ((Button) v).getText().toString();
        String temp = oldExpress.concat(enterString);
        // 先将小数点添加上取，后来通过运算符分隔开，
        // 如会出现分割开的子串中出现两个小数点的则不符合条件
        String regex = "\\+|\\-|×|÷";
        String[] arr = temp.split(regex);
        for(String str: arr){
            int count = 0;
            char[] charArr = str.toCharArray();
            for(char c: charArr){
                if(c == '.')
                    count ++;
            }
            if(count == 2)
                return false;
        }
        char lastInput = oldExpress.charAt(oldExpress.length()-1);
        return isDigit(lastInput);
    }

    // 判读能否删除
    public boolean judgeDelete(String oldExpress){
        return (oldExpress.length() > 1);
    }

    // 判断能够加入左括号
    public boolean judgeLeftBracket(String oldExpress){
        char lastInput = oldExpress.charAt(oldExpress.length()-1);
        return (isOperater(lastInput) || lastInput == '(');
    }

    // 判断能否加入右括号
    public boolean judgeRightBracket(String oldExpress){

        char lastInput = oldExpress.charAt(oldExpress.length()-1);

        //发现表达式中左括号和右括号一样多的时候,不可添加括号
        char[] charArray = oldExpress.toCharArray();
        int number = 0;
        for(char c : charArray){
            if(c == '('){
                number ++;
            }
            else if( c == ')'){
                number --;
            }
        }

        if (number == 0){
            return false;
        }

        //禁止在一个数字两端添加括号
        int index = oldExpress.length() - 1;
        while (charArray[index] != '('){
            if(isOperater(charArray[index])){
                return isDigit(lastInput);
            }
            index --;
        }
        if(charArray[index] == '('){
            return false;
        }


        return isDigit(lastInput);
    }

    // 判断是否能按下等号
    public boolean judgeEqual(String oldExpress){
        char lastInput = oldExpress.charAt(oldExpress.length()-1);
        char[] charArray = oldExpress.toCharArray();
        // 括号必须要匹配
        int number = 0;
        for(char c : charArray){
            if(c == '('){
                number ++;
            }
            else if( c == ')'){
                number --;
            }
        }

        if (number != 0){
            return false;
        }

        // 表达式中要有操作符
        if(!(oldExpress.contains("+") || oldExpress.contains("-")
        || oldExpress.contains("×") || oldExpress.contains("÷"))){
            return false;
        }

        // 结尾必须是右括号或者是数字
        return (isDigit(lastInput) || lastInput == ')');
    }

    /*
        计算逻辑模块
     */

    private double calculate(String express){
        String str = express.replaceAll("×", "*").replaceAll("÷", "/");
        return Calculator.conversion(str);
    }

    /*
        页面跳转模块
     */

    private void jumpToHistory(){
        Intent it = new Intent(MainActivity.this, history.class);
        String[] strArr = new String[al.size()];
        al.toArray(strArr);
        it.putExtra("data", strArr);
        startActivity(it);
    }

    public void clearAL(){
        al.clear();
        Toast toast = Toast.makeText(MainActivity.this, "历史记录已清空", Toast.LENGTH_SHORT);
        toast.show();
    }

}
