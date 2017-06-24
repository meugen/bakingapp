package ua.meugen.android.bakingapp.injections.components;

import dagger.Component;
import ua.meugen.android.bakingapp.injections.Model;
import ua.meugen.android.bakingapp.injections.beans.FetchReceiptsUtil;
import ua.meugen.android.bakingapp.injections.modules.ListModelModule;
import ua.meugen.android.bakingapp.injections.modules.ModelModule;

@Component(modules = { ModelModule.class, ListModelModule.class })
@Model
public interface ModelComponent {

    FetchReceiptsUtil fetchReceiptsUtil();
}
