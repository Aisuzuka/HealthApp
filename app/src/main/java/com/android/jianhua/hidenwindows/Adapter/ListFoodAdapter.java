package com.android.jianhua.hidenwindows.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jianhua.hidenwindows.DataForm.BodyData;
import com.android.jianhua.hidenwindows.MainActivity;
import com.android.jianhua.hidenwindows.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jianhua on 2015/12/24.
 */
public class ListFoodAdapter extends ArrayAdapter<String> {
    Context context;
    private ViewHolder holder;
    String[][] foodArray;

    public ListFoodAdapter(MainActivity mainActivity, int resourceID, String[][] foodArray, List<String> food) {
        super(mainActivity, resourceID, (List<String>) food);
        this.context = mainActivity;
        this.foodArray = foodArray;
    }

    static class ViewHolder {
        TextView foodName;
        ImageView foodPicture;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return setContent(position, convertView, parent);
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {


//        anim = AnimationUtils.loadAnimation(context, R.anim.alpha_anim);

        return setContent(position, rowView, parent);
    }

    private View setContent(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.food_type, parent, false);
        holder = new ViewHolder();
        ComponentInit(row, parent);
        int imageResource = context.getResources().getIdentifier(foodArray[2][position], null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        holder.foodPicture.setImageDrawable(image);
        holder.foodName.setText(foodArray[0][position]);
        return row;
    }

    private void ComponentInit(View rowView, ViewGroup parent) {

        holder.foodName = (TextView) rowView
                .findViewById(R.id.textView6);
        holder.foodPicture = (ImageView) rowView
                .findViewById(R.id.imageView5);

    }
}
