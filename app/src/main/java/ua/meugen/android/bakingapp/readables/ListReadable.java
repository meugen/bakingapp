package ua.meugen.android.bakingapp.readables;

import android.util.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.meugen.android.bakingapp.utils.JsonReadable;


public class ListReadable<T> implements JsonReadable<List<T>> {

    private final JsonReadable<T> itemReadable;

    public ListReadable(final JsonReadable<T> itemReadable) {
        this.itemReadable = itemReadable;
    }

    @Override
    public List<T> readJson(final JsonReader reader) throws IOException {
        final List<T> result = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            result.add(itemReadable.readJson(reader));
        }
        reader.endArray();

        return result;
    }
}
