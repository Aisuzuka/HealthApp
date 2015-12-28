package com.android.jianhua.hidenwindows.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jianhua.hidenwindows.DataForm.FoodData;
import com.android.jianhua.hidenwindows.MainActivity;
import com.android.jianhua.hidenwindows.R;

import java.util.ArrayList;

/**
 * Created by Chienhua on 2015/12/29.
 */
public class ListFoodListAdapter extends ArrayAdapter<FoodData> {
    Context context;
    private ViewHolder holder;
    ArrayList<FoodData> foodDatas;
    String[][] breakfast = new String[3][];
    String[][] main_meal = new String[3][];
    String[][] soup = new String[3][];
    String[][] drink = new String[3][];
    String[][] dessert = new String[3][];

    public ListFoodListAdapter(MainActivity mainActivity, ArrayList<FoodData> foodDatas) {
        super(mainActivity, R.layout.food_list, foodDatas);
        this.context = mainActivity;
        this.foodDatas = foodDatas;
    }

    static class ViewHolder {
        TextView num, time, foodName1, foodName2, foodName3, foodName4, foodName5;
        ImageView foodImage1, foodImage2, foodImage3, foodImage4, foodImage5;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        anim = AnimationUtils.loadAnimation(context, R.anim.alpha_anim);
        ImportDataBase();
        if (rowView == null) {
            holder = new ViewHolder();
            rowView = inflater.inflate(R.layout.food_list, parent, false);
            ComponentInit(rowView, parent);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        setText(position);
        return rowView;
    }

    private void setText(int position) {
        int imageResource;
        Drawable image;
        if(foodDatas.get(position).breakfast != 0){
            holder.foodName1.setText(breakfast[0][foodDatas.get(position).breakfast - 1]);
            imageResource= context.getResources().getIdentifier(breakfast[2][foodDatas.get(position).breakfast - 1], null, context.getPackageName());
            image = context.getResources().getDrawable(imageResource);
            holder.foodImage1.setImageDrawable(image);
            holder.foodName1.setVisibility(View.VISIBLE);
            holder.foodImage1.setVisibility(View.VISIBLE);
        }
        else{
            holder.foodName1.setVisibility(View.GONE);
            holder.foodImage1.setVisibility(View.GONE);
        }
        if(foodDatas.get(position).main_menu != 0){
            holder.foodName2.setText(main_meal[0][foodDatas.get(position).main_menu - 1]);
            imageResource= context.getResources().getIdentifier(main_meal[2][foodDatas.get(position).main_menu - 1], null, context.getPackageName());
            image = context.getResources().getDrawable(imageResource);
            holder.foodImage2.setImageDrawable(image);
            holder.foodName2.setVisibility(View.VISIBLE);
            holder.foodImage2.setVisibility(View.VISIBLE);
        }
        else{
            holder.foodName2.setVisibility(View.GONE);
            holder.foodImage2.setVisibility(View.GONE);
        }
        if(foodDatas.get(position).soup != 0){
            holder.foodName3.setText(soup[0][foodDatas.get(position).soup - 1]);
            imageResource= context.getResources().getIdentifier(soup[2][foodDatas.get(position).soup - 1], null, context.getPackageName());
            image = context.getResources().getDrawable(imageResource);
            holder.foodImage3.setImageDrawable(image);
            holder.foodName3.setVisibility(View.VISIBLE);
            holder.foodImage3.setVisibility(View.VISIBLE);
        }
        else{
            holder.foodName3.setVisibility(View.GONE);
            holder.foodImage3.setVisibility(View.GONE);
        }
        if(foodDatas.get(position).drink != 0){
            holder.foodName4.setText(drink[0][foodDatas.get(position).drink - 1]);
            imageResource= context.getResources().getIdentifier(drink[2][foodDatas.get(position).drink - 1], null, context.getPackageName());
            image = context.getResources().getDrawable(imageResource);
            holder.foodImage4.setImageDrawable(image);
            holder.foodName4.setVisibility(View.VISIBLE);
            holder.foodImage4.setVisibility(View.VISIBLE);
        }
        else{
            holder.foodName4.setVisibility(View.GONE);
            holder.foodImage4.setVisibility(View.GONE);
        }
        if(foodDatas.get(position).desert != 0){
            holder.foodName5.setText(dessert[0][foodDatas.get(position).desert - 1]);
            imageResource= context.getResources().getIdentifier(dessert[2][foodDatas.get(position).desert - 1], null, context.getPackageName());
            image = context.getResources().getDrawable(imageResource);
            holder.foodImage5.setImageDrawable(image);
            holder.foodName5.setVisibility(View.VISIBLE);
            holder.foodImage5.setVisibility(View.VISIBLE);
        }
        else{
            holder.foodName5.setVisibility(View.GONE);
            holder.foodImage5.setVisibility(View.GONE);
        }
        holder.num.setText(String.valueOf(position + 1));
        holder.time.setText(foodDatas.get(position).time);

    }

    private void ComponentInit(View rowView, ViewGroup parent) {

        holder.foodImage1 = (ImageView) rowView
                .findViewById(R.id.imageView6);
        holder.foodImage2 = (ImageView) rowView
                .findViewById(R.id.imageView7);
        holder.foodImage3 = (ImageView) rowView
                .findViewById(R.id.imageView8);
        holder.foodImage4 = (ImageView) rowView
                .findViewById(R.id.imageView9);
        holder.foodImage5 = (ImageView) rowView
                .findViewById(R.id.imageView10);
        holder.foodName1 = (TextView) rowView
                .findViewById(R.id.textView29);
        holder.foodName2 = (TextView) rowView
                .findViewById(R.id.textView30);
        holder.foodName3 = (TextView) rowView
                .findViewById(R.id.textView31);
        holder.foodName4 = (TextView) rowView
                .findViewById(R.id.textView32);
        holder.foodName5 = (TextView) rowView
                .findViewById(R.id.textView33);
        holder.num = (TextView) rowView
                .findViewById(R.id.textView27);
        holder.time = (TextView) rowView
                .findViewById(R.id.textView28);


    }

    private void ImportDataBase() {
        breakfast[0] = new String[]{"火腿三明治", "巧克力吐司", "肉包", "飯糰", "稀飯", "蘿蔔糕", "饅頭", "燒餅"};
        breakfast[1] = new String[]{"231.5", "194.6", "225", "281", "140", "90", "280", "214", "263"};
        breakfast[2] = new String[]{String.valueOf(R.drawable.ham_sandwich), String.valueOf(R.drawable.chocolate_toast), String.valueOf(R.drawable.meat_buns), String.valueOf(R.drawable.onigiri), String.valueOf(R.drawable.porridge), String.valueOf(R.drawable.carrotcake), String.valueOf(R.drawable.steamed_bread), String.valueOf(R.drawable.shaobing)};

        main_meal[0] = new String[]{"煎餃", "豬肉漢堡 ", "香雞堡", "滷肉飯", "蔥抓餅", "炒飯", "炸醬麵", "牛肉麵", "乾麵", "肉圓"};
        main_meal[1] = new String[]{"910.8", "420", "440", "375", "404", "515", "650", "470", "425", "494"};
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
}
