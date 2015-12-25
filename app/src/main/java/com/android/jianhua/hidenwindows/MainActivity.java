package com.android.jianhua.hidenwindows;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.jianhua.hidenwindows.Adapter.ListBodyDtatAdapter;
import com.android.jianhua.hidenwindows.DataForm.BodyData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView, imageView1, imageView2, imageView3;
    Button btn, btn1;
    ArrayList<BodyData> bodyData = new ArrayList<BodyData>();
    ListBodyDtatAdapter myArrayAdapter;
    Double length, sport, weigth;
    int old;
    boolean gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = MainActivity.this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        HomePage();
    }

    private void HomePage() {
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView2 = (ImageView) findViewById(R.id.imageView3);
        imageView3 = (ImageView) findViewById(R.id.imageView4);
        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                Toast.makeText(MainActivity.this, "正常模式", Toast.LENGTH_SHORT).show();
                FoodDetail_Normal();
                break;
            case R.id.imageView2:
                Toast.makeText(MainActivity.this, "減重模式", Toast.LENGTH_SHORT).show();
                FoodDetail_Reduce();
                break;
            case R.id.imageView3:
                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
                SettingProfile();
                break;
            case R.id.imageView4:
                MainActivity.this.finish();
                break;
            case R.id.button2:
                HomePage();
                break;
            case R.id.button3:
                AddBodyData();
                break;
        }
    }

    private void FoodDetail_Reduce() {
        setContentView(R.layout.food_memory);
    }

    private void FoodDetail_Normal() {
        setContentView(R.layout.food_memory);
    }

    private void AddBodyData() {
        BodyData data = new BodyData();
        EditText length = (EditText) findViewById(R.id.editText2);
        EditText weigth = (EditText) findViewById(R.id.editText2);
        EditText old = (EditText) findViewById(R.id.editText2);
        this.length = Double.valueOf(String.valueOf(length.getText()));
        this.weigth = Double.valueOf(String.valueOf(weigth.getText()));
        this.old = Integer.valueOf(String.valueOf(old.getText()));
        data.length = this.length;
        data.weigth = this.weigth;
        data.old = this.old;
        data.gender = gender;
        data.sport = sport;
        bodyData.add(data);
        myArrayAdapter.notifyDataSetChanged();
    }

    private void SettingProfile() {
        setContentView(R.layout.normal_mode);
        btn = (Button) findViewById(R.id.button2);
        btn1 = (Button) findViewById(R.id.button3);
        ListDataBase();
        ListSport();
        SetRadioBox();
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

    private void SetRadioBox() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        gender = true;
                        break;
                    case R.id.radioButton2:
                        gender = false;
                        break;
                }
            }
        });
    }

    private void ListSport() {
        Spinner sp = (Spinner) findViewById(R.id.spinner2);
        String[] mode_Text = {"懶散", "正常", "積極"};
        final Double[] mode_Content = {1.1, 1.2, 1.3};
        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, mode_Text);
        sp.setAdapter(SpinnerAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sport = mode_Content[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ListDataBase() {
        bodyData.clear();
        ListView ls = (ListView) findViewById(R.id.listView);
        for(int i = 1 ; i < 10 ; i++){
            BodyData data = new BodyData();
            data.length = 123.00;
            data.weigth = 234.00;
            data.gender = false;
            data.old = 34;
            data.sport = 1.3;
            bodyData.add(data);
        }
        myArrayAdapter = new ListBodyDtatAdapter(MainActivity.this, bodyData);
        ls.setAdapter(myArrayAdapter);
        myArrayAdapter.notifyDataSetChanged();
    }
}