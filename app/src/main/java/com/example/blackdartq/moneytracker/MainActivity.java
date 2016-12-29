package com.example.blackdartq.moneytracker;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    Button minusButton;
    Button plusButton;
    EditText inputBar;
    TextView moneyBar;
    Scanner scanner;

    File file = new File("money.txt");
    String fileName = "money.txt";

    public void writeToFile(String fileName, String text) {

        try {
            FileOutputStream file = openFileOutput(fileName,MODE_PRIVATE);
            file.write(text.getBytes());
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String readFromFile(String fileName) {

        String text = null;
        try {
            FileInputStream file = openFileInput(fileName);
            InputStreamReader inputStream = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuffer stringBuffer = new StringBuffer();

            while ((text = bufferedReader.readLine()) != null) {

                stringBuffer.append(text + "\n");

            }
            text = stringBuffer.toString();
            return text;


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;

    }

    public void hideKeyboard(){

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!file.exists()){
            //writeToFile(fileName,"$0.00");

        }

        //writeToFile(fileName,"$0.00");

        minusButton = (Button) findViewById(R.id.minusButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        inputBar = (EditText) findViewById(R.id.inputBar);
        moneyBar = (TextView) findViewById(R.id.moneyBar);

        moneyBar.setText(readFromFile(fileName));

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(inputBar.getText().toString().equals("")){




                }else {
                    String inputNumber = inputBar.getText().toString();
                    float convertedInputNumber = Float.parseFloat(inputNumber);

                    String currentMoney = moneyBar.getText().toString();
                    currentMoney = currentMoney.replace("$", "");
                    float convertedCurrentMoney = Float.parseFloat(currentMoney);

                    float total = convertedCurrentMoney - convertedInputNumber;
                    String completeTotal = "$" + Float.toString(total);
                    writeToFile(fileName, completeTotal);
                    moneyBar.setText(readFromFile(fileName));


                }
            }
        });


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputBar.getText().toString().equals("")){




                }else {
                    String inputNumber = inputBar.getText().toString();
                    float convertedInputNumber = Float.parseFloat(inputNumber);

                    String currentMoney = moneyBar.getText().toString();
                    currentMoney = currentMoney.replace("$", "");
                    float convertedCurrentMoney = Float.parseFloat(currentMoney);

                    float total = convertedCurrentMoney + convertedInputNumber;
                    String completeTotal = "$" + Float.toString(total);
                    writeToFile(fileName, completeTotal);
                    moneyBar.setText(readFromFile(fileName));


                }
            }
        });

        inputBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputBar.setText("");
            }
        });

        inputBar.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if (actionId == EditorInfo.IME_ACTION_DONE){

                    hideKeyboard();

                    return true;
                }return false;
            }
        });

    }


}
