package pl.tajchert.datefact;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import pl.tajchert.datefact.common.Tools;

/**
 * Created by tajchert on 07.04.15.
 */
public class ListenerService extends WearableListenerService {
    private static final String TAG = "ListenerService";
    
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        Log.d(TAG, "onMessageReceived message path: " + messageEvent.getPath() + ", data: " + messageEvent.getData());
        if(Tools.MESSAGE_PATH_MAIN.equals(messageEvent.getPath())) {
            String text = new String(messageEvent.getData());
            Log.d(TAG, "onMessageReceived our text: " + text);
            Notification notification = showNotifictionMobile(ListenerService.this, "Wear Date Fact", text);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ListenerService.this);
            notificationManager.notify(876, notification);
        }
    }

    public static Notification showNotifictionMobile(Context context, String title, String text) {
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                        .setContentTitle(title)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentText(text)
                        .setLocalOnly(true)
                        .setContentIntent(contentPendingIntent);

        return notificationBuilder.build();
    }
}
