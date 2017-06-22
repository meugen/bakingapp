package ua.meugen.android.bakingapp.utils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReadableUtils {

    @Inject
    public ReadableUtils() {}

    public void checkFieldNotNull(final Object value, final String name) throws IOException {
        if (value == null) {
            throw new IOException("Field '" + name + "' is not specified.");
        }
    }
}
