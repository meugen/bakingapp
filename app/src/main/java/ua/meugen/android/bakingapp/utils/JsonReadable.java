package ua.meugen.android.bakingapp.utils;

import android.util.JsonReader;

import java.io.IOException;

public interface JsonReadable<T> {

    T readJson(JsonReader reader) throws IOException;
}
