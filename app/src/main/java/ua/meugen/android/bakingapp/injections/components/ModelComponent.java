package ua.meugen.android.bakingapp.injections.components;

import java.util.List;

import dagger.Component;
import ua.meugen.android.bakingapp.injections.Model;
import ua.meugen.android.bakingapp.injections.modules.ListModelModule;
import ua.meugen.android.bakingapp.injections.modules.ModelModule;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.utils.JsonReadable;

@Component(modules = { ModelModule.class, ListModelModule.class })
@Model
public interface ModelComponent {

    JsonReadable<List<Receipt>> receiptsReadable();
}
