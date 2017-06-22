package ua.meugen.android.bakingapp.injections.beans;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ModelImportUtil {

    private final Context context;

    @Inject
    public ModelImportUtil(final Context context) {
        this.context = context;
    }

    public void importModel() {
        // TODO implement this method
    }
}
