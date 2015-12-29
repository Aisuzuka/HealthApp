package com.android.jianhua.hidenwindows;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jianhua.hidenwindows.Adapter.ListBodyDtatAdapter;
import com.android.jianhua.hidenwindows.Adapter.ListFoodAdapter;
import com.android.jianhua.hidenwindows.Adapter.ListFoodListAdapter;
import com.android.jianhua.hidenwindows.DataForm.BodyData;
import com.android.jianhua.hidenwindows.DataForm.FoodData;
import com.android.jianhua.hidenwindows.Tools.SQLite;
import com.android.jianhua.hidenwindows.Tools.SQLiteAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView, imageView1, imageView2, imageView3;
    Button btn, btn1;
    ArrayList<BodyData> bodyData = new ArrayList<BodyData>();
    ArrayList<FoodData> foodData = new ArrayList<FoodData>();
    ListBodyDtatAdapter myArrayAdapter;
    ListFoodListAdapter myArrayAdapter2;
    Double length, sport, weigth;
    FrameLayout main;
    int old;
    boolean gender;
    LayoutInflater flater;
    View[] view = new View[10];
    int view_counter = 0;
    String[][] breakfast = new String[3][];
    String[][] main_meal = new String[3][];
    String[][] soup = new String[3][];
    String[][] drink = new String[3][];
    String[][] dessert = new String[3][];
    SQLiteAgent sQLiteAgent;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View decorView = MainActivity.this.getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        sQLiteAgent = new SQLiteAgent(MainActivity.this);
        flater = LayoutInflater.from(this);
        sQLiteAgent.openDB();
        bodyData = sQLiteAgent.showSqliteBodyData();
        foodData = sQLiteAgent.showSqliteFoodData();
        HomePage();
    }


    private void HomePage() {
        setContentView(R.layout.activity_main);
        main = (FrameLayout) findViewById(R.id.main);
        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
        ImportDataBase();
        view_counter = view_counter + 1;
        view[view_counter] = flater.inflate(R.layout.content_main, null);
        main.removeAllViews();
        main.addView(view[view_counter]);
        imageView = (ImageView) view[view_counter].findViewById(R.id.imageView);
        imageView1 = (ImageView) view[view_counter].findViewById(R.id.imageView2);
        imageView2 = (ImageView) view[view_counter].findViewById(R.id.imageView3);
        imageView3 = (ImageView) view[view_counter].findViewById(R.id.imageView4);
        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
    }

    private void ImportDataBase() {
        breakfast[0] = new String[]{"火腿三明治", "巧克力吐司", "肉包", "飯糰", "稀飯", "蘿蔔糕", "饅頭", "燒餅"};
        breakfast[1] = new String[]{"231", "194", "225", "281", "140", "90", "280", "214", "263"};
        breakfast[2] = new String[]{String.valueOf(R.drawable.ham_sandwich), String.valueOf(R.drawable.chocolate_toast), String.valueOf(R.drawable.meat_buns), String.valueOf(R.drawable.onigiri), String.valueOf(R.drawable.porridge), String.valueOf(R.drawable.carrotcake), String.valueOf(R.drawable.steamed_bread), String.valueOf(R.drawable.shaobing)};

        main_meal[0] = new String[]{"煎餃", "豬肉漢堡 ", "香雞堡", "滷肉飯", "蔥抓餅", "炒飯", "炸醬麵", "牛肉麵", "乾麵", "肉圓"};
        main_meal[1] = new String[]{"910", "420", "440", "375", "404", "515", "650", "470", "425", "494"};
        main_meal[2] = new String[]{String.valueOf(R.drawable.gyoza), String.valueOf(R.drawable.pork_burger), String.valueOf(R.drawable.chicken_burger), String.valueOf(R.drawable.braised_pork_rice), String.valueOf(R.drawable.scallion_pancake), String.valueOf(R.drawable.fried_rice), String.valueOf(R.drawable.dry_noodles_with_minced_pork_and_cucumber), String.valueOf(R.drawable.beef_noodles), String.valueOf(R.drawable.dry_noodles), String.valueOf(R.drawable.meatballs)};

        soup[0] = new String[]{"蛋花湯", "豬血湯", "貢丸湯", "餛飩湯", "肉羹湯"};
        soup[1] = new String[]{"40", "100", "165", "235", "420"};
        soup[2] = new String[]{String.valueOf(R.drawable.egg_drop_soup), String.valueOf(R.drawable.pig_blood_soup), String.valueOf(R.drawable.meatball_soup), String.valueOf(R.drawable.wonton_soup), String.valueOf(R.drawable.pork_intestine_soup)};

        drink[0] = new String[]{"鮮乳", "奶茶", "可樂", "雪碧", "舒跑", "紅茶"};
        drink[1] = new String[]{"150", "65", "178", "147", "31", "145"};
        drink[2] = new String[]{String.valueOf(R.drawable.milk), String.valueOf(R.drawable.tea_with_milk), String.valueOf(R.drawable.cola), String.valueOf(R.drawable.sprite), String.valueOf(R.drawable.shupao), String.valueOf(R.drawable.black_tea)};

        dessert[0] = new String[]{"冰棒", "布丁", "起司蛋糕", "甜甜圈", "蛋塔", "咖啡凍"};
        dessert[1] = new String[]{"270", "320", "250", "275", "305", "114"};
        dessert[2] = new String[]{String.valueOf(R.drawable.popsicle), String.valueOf(R.drawable.pudding), String.valueOf(R.drawable.chesse_cake), String.valueOf(R.drawable.donuts), String.valueOf(R.drawable.egg_tart), String.valueOf(R.drawable.coffee_jelly)};
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                if (bodyData.size() == 0)
                    Toast.makeText(MainActivity.this, "請先記錄身體狀況", Toast.LENGTH_SHORT).show();
                else
                    FoodDetail_Normal();
                break;
            case R.id.imageView2:
                if (bodyData.size() == 0)
                    Toast.makeText(MainActivity.this, "請先記錄身體狀況", Toast.LENGTH_SHORT).show();
                else
                    FoodDetail_Reduce();
                break;
            case R.id.imageView3:
                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
                SettingProfile();
                break;
            case R.id.imageView4:
                MainActivity.this.finish();
                break;
            case R.id.button:
                ShowFoodList();
                break;
            case R.id.button2:
                view_counter = view_counter - 1;
                if (view_counter < 1) {
                    MainActivity.this.finish();
                } else {
                    main.removeAllViews();
                    main.addView(view[view_counter]);
                }
                break;
            case R.id.button3:
                AddBodyData();
                break;
            case R.id.clearData:
                foodData.clear();
                sQLiteAgent.addSqliteFoodData(foodData);
                myArrayAdapter2.notifyDataSetChanged();
                break;
        }
    }

    private void ShowFoodList() {
        view_counter = view_counter + 1;
        final FoodData data = new FoodData();
        view[view_counter] = flater.inflate(R.layout.food_chose, null);
        main.removeAllViews();
        main.addView(view[view_counter]);
        Button btn = (Button) view[view_counter].findViewById(R.id.button4);
        Spinner sp = (Spinner) view[view_counter].findViewById(R.id.spinner);
        Spinner sp1 = (Spinner) view[view_counter].findViewById(R.id.spinner3);
        Spinner sp2 = (Spinner) view[view_counter].findViewById(R.id.spinner4);
        Spinner sp3 = (Spinner) view[view_counter].findViewById(R.id.spinner5);
        Spinner sp4 = (Spinner) view[view_counter].findViewById(R.id.spinner6);
        final CheckBox cb1 = (CheckBox) view[view_counter].findViewById(R.id.checkBox);
        final CheckBox cb2 = (CheckBox) view[view_counter].findViewById(R.id.checkBox2);
        final CheckBox cb3 = (CheckBox) view[view_counter].findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox) view[view_counter].findViewById(R.id.checkBox4);
        final CheckBox cb5 = (CheckBox) view[view_counter].findViewById(R.id.checkBox5);
        final LinearLayout ll1 = (LinearLayout) view[view_counter].findViewById(R.id.breakfast);
        final LinearLayout ll2 = (LinearLayout) view[view_counter].findViewById(R.id.mainmenu);
        final LinearLayout ll3 = (LinearLayout) view[view_counter].findViewById(R.id.soup);
        final LinearLayout ll4 = (LinearLayout) view[view_counter].findViewById(R.id.drink);
        final LinearLayout ll5 = (LinearLayout) view[view_counter].findViewById(R.id.dessert);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String time = sDateFormat.format(new java.util.Date());
                if (!cb1.isChecked())
                    data.breakfast = 0;
                if (!cb2.isChecked())
                    data.main_menu = 0;
                if (!cb3.isChecked())
                    data.soup = 0;
                if (!cb4.isChecked())
                    data.drink = 0;
                if (!cb5.isChecked())
                    data.desert = 0;
                data.time = time;
                foodData.add(data);
                sQLiteAgent.addSqliteFoodData(foodData);
                view_counter = view_counter - 1;
                main.removeAllViews();
                main.addView(view[view_counter]);
                myArrayAdapter2.notifyDataSetChanged();
                int calorie = ReportTodayCalorie();
                TextView textView = (TextView) view[view_counter].findViewById(R.id.textView34);
                TextView textView2 = (TextView) view[view_counter].findViewById(R.id.textView35);
                textView.setText(String.valueOf(calorie));
                if(status) {
                    if (calorie < -500)
                        textView2.setText("吃太多了，進食7分飽就好囉");
                    else if (calorie > 500)
                        textView2.setText("吃太少了，要多吃一點噢");
                    else
                        textView2.setText("剛剛好噢!");
                }
                else {
                    if (calorie < -100)
                        textView2.setText("吃太多了，你真的想減肥嘛？");
                    else if (calorie > 700)
                        textView2.setText("吃太少了，減肥還是要吃飯噢");
                    else
                        textView2.setText("剛剛好，努力會變苗條噢");
                }
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ll1.setVisibility(View.VISIBLE);
                else
                    ll1.setVisibility(View.GONE);
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ll2.setVisibility(View.VISIBLE);
                else
                    ll2.setVisibility(View.GONE);
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ll3.setVisibility(View.VISIBLE);
                else
                    ll3.setVisibility(View.GONE);
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ll4.setVisibility(View.VISIBLE);
                else
                    ll4.setVisibility(View.GONE);
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ll5.setVisibility(View.VISIBLE);
                else
                    ll5.setVisibility(View.GONE);
            }
        });
        FoodSpinner(sp, breakfast);
        FoodSpinner(sp1, main_meal);
        FoodSpinner(sp2, soup);
        FoodSpinner(sp3, drink);
        FoodSpinner(sp4, dessert);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.breakfast = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.main_menu = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.soup = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.drink = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.desert = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void FoodSpinner(Spinner sp, String[][] item) {
        List<String> food = new ArrayList<String>();
        for (int i = 1; i < item[0].length + 1; i++)
            food.add(item[0][i - 1]);
        ListFoodAdapter myArrayAdapter1 = new ListFoodAdapter(MainActivity.this, R.layout.food_type, item, food);
        sp.setAdapter(myArrayAdapter1);
    }

    private void FoodDetail_Reduce() {
        status = false;
        view_counter = view_counter + 1;
        view[view_counter] = flater.inflate(R.layout.food_memory, null);
        ListFoodData();
        ListView ls = (ListView) view[view_counter].findViewById(R.id.listView2);
        FrameLayout clear = (FrameLayout) view[view_counter].findViewById(R.id.clearData);
        TextView textView = (TextView) view[view_counter].findViewById(R.id.textView34);
        TextView textView2 = (TextView) view[view_counter].findViewById(R.id.textView35);
        myArrayAdapter2 = new ListFoodListAdapter(MainActivity.this, foodData);
        ls.setAdapter(myArrayAdapter2);
        myArrayAdapter2.notifyDataSetChanged();
        int calorie = ReportTodayCalorie();
        textView.setText(String.valueOf(calorie));
        if (calorie < -100)
            textView2.setText("吃太多了，你真的想減肥嘛？");
        else if (calorie > 700)
            textView2.setText("吃太少了，減肥還是要吃飯噢");
        else
            textView2.setText("剛剛好，努力會變苗條噢");
        main.removeAllViews();
        main.addView(view[view_counter]);
        Button btn = (Button) view[view_counter].findViewById(R.id.button);
        btn.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    private void FoodDetail_Normal() {
        status = true;
        view_counter = view_counter + 1;
        view[view_counter] = flater.inflate(R.layout.food_memory, null);
        ListFoodData();
        ListView ls = (ListView) view[view_counter].findViewById(R.id.listView2);
        FrameLayout clear = (FrameLayout) view[view_counter].findViewById(R.id.clearData);
        TextView textView = (TextView) view[view_counter].findViewById(R.id.textView34);
        TextView textView2 = (TextView) view[view_counter].findViewById(R.id.textView35);
        myArrayAdapter2 = new ListFoodListAdapter(MainActivity.this, foodData);
        ls.setAdapter(myArrayAdapter2);
        myArrayAdapter2.notifyDataSetChanged();
        int calorie = ReportTodayCalorie();
        textView.setText(String.valueOf(calorie));
        if (calorie < -500)
            textView2.setText("吃太多了，進食7分飽就好囉");
        else if (calorie > 500)
            textView2.setText("吃太少了，要多吃一點噢");
        else
            textView2.setText("剛剛好噢!");
        main.removeAllViews();
        main.addView(view[view_counter]);
        Button btn = (Button) view[view_counter].findViewById(R.id.button);
        btn.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    private void ListFoodData() {
        foodData = sQLiteAgent.showSqliteFoodData();
    }

    private int ReportTodayCalorie() {
        double calorie = 0;
        int todayCalorie = 0;
        int count = bodyData.size();
        if (count != 0) {
            calorie = (665 + 1.38 * bodyData.get(count - 1).weigth + 5 * bodyData.get(count - 1).length - 6.8 * bodyData.get(count - 1).old) * bodyData.get(count - 1).sport;
            int size = foodData.size();
            for (int i = 0; i < size; i++) {
                if (foodData.get(i).breakfast != 0)
                    todayCalorie = todayCalorie + Integer.parseInt(breakfast[1][foodData.get(i).breakfast - 1]);
                if (foodData.get(i).main_menu != 0)
                    todayCalorie = todayCalorie + Integer.parseInt(main_meal[1][foodData.get(i).main_menu - 1]);
                if (foodData.get(i).drink != 0)
                    todayCalorie = todayCalorie + Integer.parseInt(drink[1][foodData.get(i).drink - 1]);
                if (foodData.get(i).desert != 0)
                    todayCalorie = todayCalorie + Integer.parseInt(dessert[1][foodData.get(i).desert - 1]);
                if (foodData.get(i).soup != 0)
                    todayCalorie = todayCalorie + Integer.parseInt(soup[1][foodData.get(i).soup - 1]);
            }
        }
        calorie = calorie - todayCalorie;
        return (int) calorie;
    }

    private void AddBodyData() {
        BodyData data = new BodyData();
        EditText length = (EditText) view[view_counter].findViewById(R.id.editText2);
        EditText weigth = (EditText) view[view_counter].findViewById(R.id.editText3);
        EditText old = (EditText) view[view_counter].findViewById(R.id.editText4);
        this.length = Double.valueOf(String.valueOf(length.getText()));
        this.weigth = Double.valueOf(String.valueOf(weigth.getText()));
        this.old = Integer.valueOf(String.valueOf(old.getText()));
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String time = sDateFormat.format(new java.util.Date());
        data.length = this.length;
        data.weigth = this.weigth;
        data.old = this.old;
        data.gender = gender;
        data.sport = sport;
        data.time = time;
        bodyData.add(data);
        myArrayAdapter.notifyDataSetChanged();
        sQLiteAgent.addSqliteBodyData(bodyData);
    }


    private void SettingProfile() {
        view_counter = view_counter + 1;
        view[view_counter] = flater.inflate(R.layout.normal_mode, null);
        main.removeAllViews();
        main.addView(view[view_counter]);
        btn1 = (Button) view[view_counter].findViewById(R.id.button3);
        bodyData = sQLiteAgent.showSqliteBodyData();
        ListBodyData();
        ListSport();
        SetRadioBox();
        btn1.setOnClickListener(this);
    }

    private void SetRadioBox() {
        final RadioButton rb1 = (RadioButton) view[view_counter].findViewById(R.id.radioButton);
        final RadioButton rb2 = (RadioButton) view[view_counter].findViewById(R.id.radioButton2);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = true;
                rb1.setChecked(true);
                rb2.setChecked(false);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = false;
                rb2.setChecked(true);
                rb1.setChecked(false);
            }
        });
    }

    private void ListSport() {
        Spinner sp = (Spinner) view[view_counter].findViewById(R.id.spinner2);
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


    private void ListBodyData() {
        ListView ls = (ListView) view[view_counter].findViewById(R.id.listView);
        myArrayAdapter = new ListBodyDtatAdapter(MainActivity.this, bodyData);
        ls.setAdapter(myArrayAdapter);
        myArrayAdapter.notifyDataSetChanged();
    }
}