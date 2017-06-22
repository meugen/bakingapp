package ua.meugen.android.bakingapp.readables;

import android.util.JsonReader;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.model.Ingredient;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.model.Step;
import ua.meugen.android.bakingapp.utils.JsonReadable;
import ua.meugen.android.bakingapp.utils.ReadableUtils;


public class ReceiptReadable implements JsonReadable<Receipt> {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_INGREDIENTS = "ingredients";
    private static final String FIELD_STEPS = "steps";
    private static final String FIELD_SERVINGS = "servings";
    private static final String FIELD_IMAGE = "image";

    private final JsonReadable<List<Ingredient>> ingredientsReadable;
    private final JsonReadable<List<Step>> stepsReadable;

    @Inject
    public ReceiptReadable(
            final JsonReadable<List<Ingredient>> ingredientsReadable,
            final JsonReadable<List<Step>> stepsReadable) {
        this.ingredientsReadable = ingredientsReadable;
        this.stepsReadable = stepsReadable;
    }

    @Override
    public Receipt readJson(final JsonReader reader) throws IOException {
        Integer id = null;
        String name = null;
        List<Ingredient> ingredients = null;
        List<Step> steps = null;
        String servings = null;
        String image = null;

        reader.beginObject();
        while (reader.hasNext()) {
            final String _name = reader.nextName();
            if (FIELD_ID.equals(_name)) {
                id = reader.nextInt();
            } else if (FIELD_NAME.equals(_name)) {
                name = reader.nextName();
            } else if (FIELD_INGREDIENTS.equals(_name)) {
                ingredients = ingredientsReadable.readJson(reader);
            } else if (FIELD_STEPS.equals(_name)) {
                steps = stepsReadable.readJson(reader);
            } else if (FIELD_SERVINGS.equals(_name)) {
                servings = reader.nextString();
            } else if (FIELD_IMAGE.equals(_name)) {
                image = reader.nextString();
            } else {
                throw new IOException("Unknown field: " + name);
            }
        }
        reader.endObject();

        ReadableUtils.checkFieldNotNull(id, FIELD_ID);
        ReadableUtils.checkFieldNotNull(name, FIELD_NAME);
        ReadableUtils.checkFieldNotNull(ingredients, FIELD_INGREDIENTS);
        ReadableUtils.checkFieldNotNull(steps, FIELD_STEPS);
        ReadableUtils.checkFieldNotNull(servings, FIELD_SERVINGS);
        ReadableUtils.checkFieldNotNull(image, FIELD_IMAGE);

        final Receipt result = new Receipt();
        result.setId(id);
        result.setName(name);
        result.setIngredients(ingredients);
        result.setSteps(steps);
        result.setServings(servings);
        result.setImage(image);
        return result;
    }
}
