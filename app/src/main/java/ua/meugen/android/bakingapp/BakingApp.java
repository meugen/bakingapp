package ua.meugen.android.bakingapp;

import android.app.Application;
import android.content.Context;

import ua.meugen.android.bakingapp.injections.components.AppComponent;
import ua.meugen.android.bakingapp.injections.components.DaggerAppComponent;
import ua.meugen.android.bakingapp.injections.modules.AppModule;

public class BakingApp extends Application {

    public static BakingApp from(final Context context) {
        return (BakingApp) context.getApplicationContext();
    }

    public static AppComponent appComponent(final Context context) {
        return from(context).getAppComponent();
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
