package ua.meugen.android.bakingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.BakingApp;
import ua.meugen.android.bakingapp.R;
import ua.meugen.android.bakingapp.injections.beans.ModelImportUtil;

public class MainActivity extends AppCompatActivity {

    @Inject ModelImportUtil modelImportUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BakingApp.appComponent(this).inject(this);
        modelImportUtil.importModel();
    }
}
