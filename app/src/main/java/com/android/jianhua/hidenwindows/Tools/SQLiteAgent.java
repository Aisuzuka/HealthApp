package com.android.jianhua.hidenwindows.Tools;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.jianhua.hidenwindows.DataForm.BodyData;
import com.android.jianhua.hidenwindows.DataForm.FoodData;
import com.android.jianhua.hidenwindows.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jianhua on 2015/12/28.
 */
public class SQLiteAgent {
    Activity mActivity;

    SQLite sqLite = null;

    public SQLiteAgent(MainActivity mainActivity) {
        mActivity = mainActivity;

    }
    public void openDB() {
        sqLite = new SQLite(mActivity);
    }

    public void closeDB() {
        sqLite.close();
    }

    public void addSqliteBodyData(ArrayList<BodyData> data) {
        int size = data.size();
        JSONArray json1 = new JSONArray();
        JSONArray json2 = new JSONArray();
        JSONArray json3 = new JSONArray();
        JSONArray json4 = new JSONArray();
        JSONArray json5 = new JSONArray();
        JSONArray json6 = new JSONArray();
        for (int i = 0; i < size; i++) {
            json1.put(data.get(i).length);
            json2.put(data.get(i).weigth);
            json3.put(data.get(i).old);
            json4.put(data.get(i).gender);
            json5.put(data.get(i).sport);
            json6.put(data.get(i).time);
        }
        JSONObject json = new JSONObject();
        try {
            json.put("Length", json1);
            json.put("Weigth", json2);
            json.put("Old", json3);
            json.put("Gender", json4);
            json.put("Sport", json5);
            json.put("Time", json6);
            String arrayList = json.toString();

            SQLiteDatabase db = sqLite.getReadableDatabase();
            String[] colum = {"_id", "api", "json"};
            Cursor c = db.query("api",
                    colum, "api=" + "'" + "BodyData" + "'",
                    null, null, null, null);
            ContentValues cv = new ContentValues();
            if (c.getCount() <= 0) {
                cv.put("api", "BodyData");
                cv.put("json", arrayList);
                long status = db.insert(
                        "api", null, cv);
            } else {
                c.moveToFirst();
                if ((!(c.getString(2).equals(arrayList))) && (arrayList != null)) {
                    cv.put("json", arrayList);
                    int count = db.update(
                            "api", cv,
                            "api=" + "'" + "BodyData" + "'",
                            null);
                }
            }
            c.close();
            db.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Exception
        }
    }
    public ArrayList<BodyData> showSqliteBodyData() {
        ArrayList<BodyData> Array_bodyDatas = new ArrayList<BodyData>();

        SQLiteDatabase db = sqLite.getReadableDatabase();
        String[] colum = {"_id", "api", "json"};
        Cursor c = db.query("api", colum, "api=" + "'" + "BodyData"
                + "'", null, null, null, null);
        if (c.getCount() <= 0) {
            db.close();
            return Array_bodyDatas;
        }
        c.moveToFirst();
        String result = c.getString(2);
        db.close();
        try {
            JSONObject json = new JSONObject(result);
            int size = json.getJSONArray("Length").length();
            BodyData[] bodyData = new BodyData[size];
            Array_bodyDatas.clear();
            for (int i = 0; i < size; i++) {
                bodyData[i] = new BodyData();
                bodyData[i].length = json.getJSONArray("Length").getDouble(i);
                bodyData[i].weigth = json.getJSONArray("Weigth").getDouble(i);
                bodyData[i].old = json.getJSONArray("Old").getInt(i);
                bodyData[i].gender = json.getJSONArray("Gender").getBoolean(i);
                bodyData[i].sport = json.getJSONArray("Sport").getDouble(i);
                bodyData[i].time = json.getJSONArray("Time").getString(i);
                Array_bodyDatas.add(bodyData[i]);
            }
            return Array_bodyDatas;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Array_bodyDatas;
        } catch (JSONException e) {
            e.printStackTrace();
            return Array_bodyDatas;
        } catch (Exception e) {
            return Array_bodyDatas;
            // Exception
        }
    }





    public void addSqliteFoodData(ArrayList<FoodData> data) {
        int size = data.size();
        JSONArray json1 = new JSONArray();
        JSONArray json2 = new JSONArray();
        JSONArray json3 = new JSONArray();
        JSONArray json4 = new JSONArray();
        JSONArray json5 = new JSONArray();
        JSONArray json6 = new JSONArray();
        for (int i = 0; i < size; i++) {
            json1.put(data.get(i).breakfast);
            json2.put(data.get(i).main_menu);
            json3.put(data.get(i).soup);
            json4.put(data.get(i).drink);
            json5.put(data.get(i).desert);
            json6.put(data.get(i).time);
        }
        JSONObject json = new JSONObject();
        try {
            json.put("Breakfast", json1);
            json.put("Main_menu", json2);
            json.put("Soup", json3);
            json.put("Drink", json4);
            json.put("Desert", json5);
            json.put("Time", json6);
            String arrayList = json.toString();

            SQLiteDatabase db = sqLite.getReadableDatabase();
            String[] colum = {"_id", "api", "json"};
            Cursor c = db.query("api",
                    colum, "api=" + "'" + "FoodData" + "'",
                    null, null, null, null);
            ContentValues cv = new ContentValues();
            if (c.getCount() <= 0) {
                cv.put("api", "FoodData");
                cv.put("json", arrayList);
                long status = db.insert(
                        "api", null, cv);
            } else {
                c.moveToFirst();
                if ((!(c.getString(2).equals(arrayList))) && (arrayList != null)) {
                    cv.put("json", arrayList);
                    int count = db.update(
                            "api", cv,
                            "api=" + "'" + "FoodData" + "'",
                            null);
                }
            }
            c.close();
            db.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Exception
        }
    }
    public ArrayList<FoodData> showSqliteFoodData() {
        ArrayList<FoodData> Array_foodDatas = new ArrayList<FoodData>();

        SQLiteDatabase db = sqLite.getReadableDatabase();
        String[] colum = {"_id", "api", "json"};
        Cursor c = db.query("api", colum, "api=" + "'" + "FoodData"
                + "'", null, null, null, null);
        if (c.getCount() <= 0) {
            db.close();
            return Array_foodDatas;
        }
        c.moveToFirst();
        String result = c.getString(2);
        db.close();
        try {
            JSONObject json = new JSONObject(result);
            int size = json.getJSONArray("Breakfast").length();
            FoodData[] foodData = new FoodData[size];
            Array_foodDatas.clear();
            for (int i = 0; i < size; i++) {
                foodData[i] = new FoodData();
                foodData[i].breakfast = json.getJSONArray("Breakfast").getInt(i);
                foodData[i].main_menu = json.getJSONArray("Main_menu").getInt(i);
                foodData[i].soup = json.getJSONArray("Soup").getInt(i);
                foodData[i].drink = json.getJSONArray("Drink").getInt(i);
                foodData[i].desert = json.getJSONArray("Desert").getInt(i);
                foodData[i].time = json.getJSONArray("Time").getString(i);
                Array_foodDatas.add(foodData[i]);
            }
            return Array_foodDatas;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Array_foodDatas;
        } catch (JSONException e) {
            e.printStackTrace();
            return Array_foodDatas;
        } catch (Exception e) {
            return Array_foodDatas;
            // Exception
        }
    }
}
