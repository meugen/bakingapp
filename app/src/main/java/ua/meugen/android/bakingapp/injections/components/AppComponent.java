package ua.meugen.android.bakingapp.injections.components;

import javax.inject.Singleton;

import dagger.Component;
import ua.meugen.android.bakingapp.activities.MainActivity;
import ua.meugen.android.bakingapp.injections.modules.AppModule;
import ua.meugen.android.bakingapp.injections.beans.DbUpgradeUtil;

@Component(modules = {AppModule.class })
@Singleton
public interface AppComponent {

    void inject(MainActivity activity);

    DbUpgradeUtil dbUpgradeUtil();
}
