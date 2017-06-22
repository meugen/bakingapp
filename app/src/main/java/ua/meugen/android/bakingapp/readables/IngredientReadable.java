package ua.meugen.android.bakingapp.readables;

import android.util.JsonReader;

import java.io.IOException;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.model.Ingredient;
import ua.meugen.android.bakingapp.utils.JsonReadable;
import ua.meugen.android.bakingapp.utils.ReadableUtils;


public class IngredientReadable implements JsonReadable<Ingredient> {

    private static final String FIELD_QUANTITY = "quantity";
    private static final String FIELD_MEASURE = "measure";
    private static final String FIELD_INGREDIENT = "ingredient";

    private final ReadableUtils utils;

    @Inject
    public IngredientReadable(final ReadableUtils utils) {
        this.utils = utils;
    }

    @Override
    public Ingredient readJson(final JsonReader reader) throws IOException {
        Integer quantity = null;
        String measure = null;
        String ingredient = null;

        reader.beginObject();
        while (reader.hasNext()) {
            final String name = reader.nextName();
            if (FIELD_QUANTITY.equals(name)) {
                quantity = reader.nextInt();
            } else if (FIELD_MEASURE.equals(name)) {
                measure = reader.nextString();
            } else if (FIELD_INGREDIENT.equals(name)) {
                ingredient = reader.nextString();
            } else {
                throw new IOException("Unknown field: " + name);
            }
        }
        reader.endObject();

        utils.checkFieldNotNull(quantity, FIELD_QUANTITY);
        utils.checkFieldNotNull(measure, FIELD_MEASURE);
        utils.checkFieldNotNull(ingredient, FIELD_INGREDIENT);

        final Ingredient result = new Ingredient();
        result.setQuantity(quantity);
        result.setMeasure(measure);
        result.setIngredient(ingredient);
        return result;
    }
}
