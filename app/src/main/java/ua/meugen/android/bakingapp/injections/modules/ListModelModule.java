package ua.meugen.android.bakingapp.injections.modules;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ua.meugen.android.bakingapp.injections.Model;
import ua.meugen.android.bakingapp.model.Ingredient;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.model.Step;
import ua.meugen.android.bakingapp.readables.ListReadable;
import ua.meugen.android.bakingapp.utils.JsonReadable;

@Module
public class ListModelModule {

    @Provides @Model
    public JsonReadable<List<Ingredient>> provideIngredientsReadable(
            final JsonReadable<Ingredient> ingredientReadable) {
        return new ListReadable<>(ingredientReadable);
    }

    @Provides @Model
    public JsonReadable<List<Step>> provideStepsReadable(
            final JsonReadable<Step> stepReadable) {
        return new ListReadable<>(stepReadable);
    }

    @Provides @Model
    public JsonReadable<List<Receipt>> provideReceiptsReadable(
            final JsonReadable<Receipt> receiptReadable) {
        return new ListReadable<>(receiptReadable);
    }
}
