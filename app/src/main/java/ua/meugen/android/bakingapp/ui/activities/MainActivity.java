package ua.meugen.android.bakingapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ua.meugen.android.bakingapp.R;
import ua.meugen.android.bakingapp.services.StoreReceiptsService;
import ua.meugen.android.bakingapp.ui.adapters.ReceiptsAdapter;

public class MainActivity extends AppCompatActivity implements
        ReceiptsAdapter.OnReceiptClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, StoreReceiptsService.class));
    }

    @Override
    public void onReceiptClick(final Uri uri) {
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }
}
