package ua.meugen.android.bakingapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.BakingApp;
import ua.meugen.android.bakingapp.injections.beans.StoreReceiptsUtil;

public class StoreReceiptsService extends IntentService {

    @Inject StoreReceiptsUtil util;

    public StoreReceiptsService() {
        super("StoreReceiptsService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BakingApp.appComponent(this).inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        if (intent != null) {
            util.storeReceipts();
        }
    }
}
