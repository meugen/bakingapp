package ua.meugen.android.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ua.meugen.android.bakingapp.R;
import ua.meugen.android.bakingapp.services.StoreReceiptsService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, StoreReceiptsService.class));
    }
}
