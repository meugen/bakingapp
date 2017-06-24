package ua.meugen.android.bakingapp.injections.beans;

import android.content.Context;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.injections.Model;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.utils.IOUtils;
import ua.meugen.android.bakingapp.utils.JsonReadable;

/**
 * @author meugen
 */

@Model
public class FetchReceiptsUtil {

    private final JsonReadable<List<Receipt>> receiptsReadable;

    @Inject
    public FetchReceiptsUtil(final JsonReadable<List<Receipt>> receiptsReadable) {
        this.receiptsReadable = receiptsReadable;
    }

    public List<Receipt> fetchReceipts(final Context context) {
        try {
            JsonReader reader = null;
            try {
                final InputStream inputStream = context.getAssets().open("baking.json");
                reader = new JsonReader(new InputStreamReader(inputStream));
                return receiptsReadable.readJson(reader);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
