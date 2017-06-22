package ua.meugen.android.bakingapp.injections;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.utils.JsonReadable;

@Component(modules = { ModelModule.class, ListModelModule.class })
@Singleton
public interface AppComponent {

    JsonReadable<List<Receipt>> receiptsReadable();
}
