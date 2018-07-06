package com.example.foush.otgmvp.ui.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Balance.BalanceActivity;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by foush on 20/04/18.
 */

public class MyFCMService extends FirebaseMessagingService {
    DataManager mDataManager;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        public static final java.lang.String TAG = "ContentValues";
        super.onMessageReceived(remoteMessage);

        /*** the next step it will be  number 19 in the checklist [Retrive the custom message this will depend on the order structure that hani will implement]***/

        Log.d("test message 2 ", "Notification all:  "+remoteMessage);
        Log.d("test message 3 ", "Notification title:  "+remoteMessage.getNotification().getTitle());
        Log.d("test message 4 ", "Notification body:  "+remoteMessage.getNotification().getBody());






        sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

    }

//
//    /******************** //parse int  [order_id 50 ]*********************/
//    final String order_id="product_id 50";
//
//    String[] parts = order_id.split(" ");
//    String part1 = parts[0]; // product_id
//    String part2 = parts[1]; // 50
//    int test =Integer.parseInt(part2);
//        Log.d("test parse notification", "onViewClicked: "+part2);
//
//
    private void sendNotification(String messageBody,String notificationTitle) {

        /** Get the order id*/
        String order_id=messageBody;
            String[] parts = order_id.split(" ");
            String part1 = parts[0]; // order
            String part2 = parts[1]; // created:70

        String[] parts2 = order_id.split(":");
        String part11 = parts2[0]; // created
        String part22 = parts2[1]; // 70
        Log.d("test parse notification", "sendNotification:"+part22);
        int orderID =Integer.parseInt(part22);



        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putString("name", "Elena");
        editor.putInt("orderID", orderID);
        editor.apply();








//        mDataManager.saveOrderID(Integer.parseInt(part22));


//        Intent intent = new Intent(this, NotificationActivity.class);
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Log.d("test message ", "Notification data:  "+messageBody);
//        Toast.makeText(this, ""+messageBody, Toast.LENGTH_SHORT).show();


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
