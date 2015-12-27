package com.android.jianhua.hidenwindows.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.jianhua.hidenwindows.DataForm.BodyData;
import com.android.jianhua.hidenwindows.MainActivity;
import com.android.jianhua.hidenwindows.R;

import java.util.ArrayList;

/**
 * Created by Jianhua on 2015/12/24.
 */
public class ListBodyDtatAdapter extends ArrayAdapter<BodyData> {
    Context context;
    private ViewHolder holder;
    ArrayList<BodyData> bodyData;
    public ListBodyDtatAdapter(MainActivity mainActivity, ArrayList<BodyData> bodyData) {
        super(mainActivity, R.layout.profile_list, bodyData);
        this.context = mainActivity;
        this.bodyData = bodyData;
    }
    static class ViewHolder{
        TextView length, weigth, old, gender, sport, time;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        anim = AnimationUtils.loadAnimation(context, R.anim.alpha_anim);

        if (rowView == null) {
            holder = new ViewHolder();
            rowView = inflater.inflate(R.layout.profile_list, parent, false);
            ComponentInit(rowView, parent);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        setText(position);
        return rowView;
    }

    private void setText(int position) {
        holder.length.setText(String.valueOf(bodyData.get(position).length));
        holder.weigth.setText(String.valueOf(bodyData.get(position).weigth));
        holder.old.setText(String.valueOf(bodyData.get(position).old));
        holder.time.setText(bodyData.get(position).time);
        if(bodyData.get(position).gender)
            holder.gender.setText("男生");
        else
            holder.gender.setText("女生");

        switch(String.valueOf(bodyData.get(position).sport)){
            case "1.1":
                holder.sport.setText("懶散");
                break;
            case "1.2":
                holder.sport.setText("健康");
                break;
            case "1.3":
                holder.sport.setText("積極");
                break;
        }

    }

    private void ComponentInit(View rowView, ViewGroup parent) {

        holder.length = (TextView) rowView
                .findViewById(R.id.editText7);
        holder.weigth = (TextView) rowView
                .findViewById(R.id.editText8);
        holder.old = (TextView) rowView
                .findViewById(R.id.editText9);
        holder.gender = (TextView) rowView
                .findViewById(R.id.editText10);
        holder.sport = (TextView) rowView
                .findViewById(R.id.editText11);
        holder.time = (TextView) rowView
                .findViewById(R.id.textView22);


    }
}
