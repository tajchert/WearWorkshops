package pl.tajchert.datefact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import pl.tajchert.datefact.api.FetchService;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(MainActivity.this, FetchService.class));
    }
}
