package ua.meugen.android.bakingapp.injections;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.bakingapp.model.Ingredient;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.model.Step;
import ua.meugen.android.bakingapp.readables.IngredientReadable;
import ua.meugen.android.bakingapp.readables.ReceiptReadable;
import ua.meugen.android.bakingapp.readables.StepReadable;
import ua.meugen.android.bakingapp.utils.JsonReadable;

@Module
public abstract class ModelModule {

    @Binds @Singleton
    public abstract JsonReadable<Ingredient> bindIngredientReadable(
            final IngredientReadable impl);

    @Binds @Singleton
    public abstract JsonReadable<Step> bindStepReadable(
            final StepReadable impl);

    @Binds @Singleton
    public abstract JsonReadable<Receipt> bindReceiptReadable(
            final ReceiptReadable impl);
}
