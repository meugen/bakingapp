package ua.meugen.android.bakingapp.utils;

import java.io.IOException;

public class ReadableUtils {

    private ReadableUtils() {}

    public static void checkFieldNotNull(
            final Object value, final String name) throws IOException {
        if (value == null) {
            throw new IOException("Field '" + name + "' is not specified.");
        }
    }
}
