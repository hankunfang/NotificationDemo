package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * 类WishListNotificationService.java的实现描述：TODO 类实现描述
 * 
 * @author hankunfang 2014年1月16日 下午2:49:26
 */
public class NotificationService extends Service {

    public static final String TAG = "NotificationService";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.d(TAG, "onStartCommand() executed");

        new Thread(new Runnable() {

            @Override
            public void run() {
                // 开始执行后台任务
                try {
                    Thread.sleep(5000);
                    showNotification();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "test").start();

        // Timer timer = new Timer();
        // TimerTask task = new TimerTask() {
        //
        // @Override
        // public void run() {
        // try {
        // android.util.Log.d(TAG, "NT1");
        // showNotification();
        //
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
        // };
        //
        // timer.schedule(task, 0, 5000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.util.Log.d(TAG, "onDestroy() executed");
    }

    public void showNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher).setContentTitle("My Notification").setContentText("Hello World");
        Intent intent = new Intent(this, NotificationActivity.class);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of your application to the Home screen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotificationActivity.class);

        // Adds the intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(0, mBuilder.build());
    }

}
