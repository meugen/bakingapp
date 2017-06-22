package ua.meugen.android.bakingapp.injections.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(final Application app) {
        this.app = app;
    }

    @Provides @Singleton
    public Context provideContext() {
        return app;
    }
}
