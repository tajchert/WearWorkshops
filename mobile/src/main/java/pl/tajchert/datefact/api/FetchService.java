package pl.tajchert.datefact.api;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Calendar;

import pl.tajchert.datefact.NotificationFactory;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchService extends Service {

    private static final String TAG = "FetchService";
    private static final String API_NUMBERS_URL = "https://numbersapi.p.mashape.com/";
    public FetchService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Calendar cal = Calendar.getInstance();
        getFact((cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
        return super.onStartCommand(intent, flags, startId);
    }

    private void getFact(String date) {
        date = API_NUMBERS_URL + date;
        IDateApi articleGetter = getHostAdapter(date).create(IDateApi.class);
        articleGetter.getFactForDate("false", "true", new Callback<DateApi>() {
            @Override
            public void success(DateApi dateApi, Response response) {
                Notification notification = NotificationFactory.showNotifictionMobile(FetchService.this, "Fact about today!", dateApi.text);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FetchService.this);
                notificationManager.notify(876, notification);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "failure :" + error.getResponse());
            }
        });
    }

    public RestAdapter getHostAdapter(String baseHost) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseHost)
                .setConverter(new LoganSquareConverter())
                .build();
        return restAdapter;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
