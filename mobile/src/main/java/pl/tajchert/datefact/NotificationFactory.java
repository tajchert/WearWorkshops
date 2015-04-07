package pl.tajchert.datefact;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class NotificationFactory {

    public static Notification showNotifictionMobile(Context context, String title, String text) {
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Intent wikipediaWebIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://en.wikipedia.org/wiki/Portal:Current_events"));
        PendingIntent wikipediaPendingIntent = PendingIntent.getActivity(context, 0, wikipediaWebIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                        .setContentTitle(title)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentText(text)
                        .setLocalOnly(true)
                        .addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "Open Wikipedia", wikipediaPendingIntent))
                        .setContentIntent(contentPendingIntent);

        return notificationBuilder.build();
    }

    public static Notification showNotifictionWear(Context context, String title, String text) {
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Intent wikipediaWebIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://en.wikipedia.org/wiki/Portal:Current_events"));
        PendingIntent wikipediaPendingIntent = PendingIntent.getActivity(context, 0, wikipediaWebIntent, 0);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "Open Wikipedia", wikipediaPendingIntent));

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                        .setContentTitle(title)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentText(text)
                        .extend(wearableExtender)
                        .setContentIntent(contentPendingIntent);

        return notificationBuilder.build();
    }
}