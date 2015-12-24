package com.android.jianhua.hidenwindows;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;

public class MyIntentService extends Service
{
    public static final String OPERATION = "operation";
    public static final int OPERATION_SHOW = 100;
    public static final int OPERATION_HIDE = 101;

    private static final int HANDLE_CHECK_ACTIVITY = 200;

    private boolean isAdded = false; // 岆瘁眒崝樓唑腹敦
    private static WindowManager wm;
    private static WindowManager.LayoutParams params;
    private Button btn_floatView;

    private List<String> homeList; // 袤醱茼蚚最唗婦靡蹈桶
    private ActivityManager mActivityManager;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        homeList = getHomes();
        createFloatView();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        int operation = intent.getIntExtra(OPERATION, OPERATION_SHOW);
        switch (operation)
        {
            case OPERATION_SHOW:
                mHandler.removeMessages(HANDLE_CHECK_ACTIVITY);
                mHandler.sendEmptyMessage(HANDLE_CHECK_ACTIVITY);
                break;
            case OPERATION_HIDE:
                mHandler.removeMessages(HANDLE_CHECK_ACTIVITY);
                break;
        }
    }

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case HANDLE_CHECK_ACTIVITY:
                    if (isHome())
                    {
                        if (!isAdded)
                        {
                            wm.addView(btn_floatView, params);
                            isAdded = true;
                        }
                    } else
                    {
                        if (isAdded)
                        {
                            wm.removeView(btn_floatView);
                            isAdded = false;
                        }
                    }
                    mHandler.sendEmptyMessageDelayed(HANDLE_CHECK_ACTIVITY, 1000);
                    break;
            }
        }
    };

    /**
     * 斐膘唑腹敦
     */
    private void createFloatView()
    {
        btn_floatView = new Button(getApplicationContext());
        btn_floatView.setText("劑豢");
        btn_floatView.setBackgroundResource(R.drawable.earth);


        wm = (WindowManager) getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();

        // 扢离window type
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		/*
		 * 彆扢离峈params.type = WindowManager.LayoutParams.TYPE_PHONE; 饒繫蚥珂撰頗蔥腴珨虳,
		 * 撈嶺狟籵眭戲祥褫獗
		 */

        params.format = PixelFormat.RGBA_8888; // 扢离芞跡宒ㄛ虴彆峈掖劓芵隴

        // 扢离Window flag
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * 狟醱腔flags扽俶腔虴彆倛肮※坶隅§﹝ 唑腹敦祥褫揖類ㄛ祥諉忳睡岈璃,肮奀祥荌砒綴醱腔岈璃砒茼﹝
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */

        // 扢离唑腹敦腔酗腕遵
        params.width = 80;
        params.height = 80;

        // 扢离唑腹敦腔Touch潼泭
        btn_floatView.setOnTouchListener(new OnTouchListener()
        {
            int lastX, lastY;
            int paramX, paramY;

            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        paramX = params.x;
                        paramY = params.y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        params.x = paramX + dx;
                        params.y = paramY + dy;
                        // 載陔唑腹敦弇离
                        wm.updateViewLayout(btn_floatView, params);
                        break;
                }
                return true;
            }
        });
        wm.addView(btn_floatView, params);
        isAdded = true;
    }

    /**
     * 鳳腕扽衾袤醱腔茼蚚腔茼蚚婦靡備
     *
     * @return 殿隙婦漪垀衄婦靡腔趼睫揹蹈桶
     */
    private List<String> getHomes()
    {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        // 扽俶
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo)
        {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }

    /**
     * 瓚剿絞賜醱岆瘁岆袤醱
     */
    public boolean isHome()
    {
        if (mActivityManager == null)
        {
            mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        }
        List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return true;
        // return homeList.contains(rti.get(0).topActivity.getPackageName());
    }

}
