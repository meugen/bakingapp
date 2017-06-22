package ua.meugen.android.bakingapp.readables;

import android.util.JsonReader;

import java.io.IOException;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.model.Step;
import ua.meugen.android.bakingapp.utils.JsonReadable;
import ua.meugen.android.bakingapp.utils.ReadableUtils;

public class StepReadable implements JsonReadable<Step> {

    private static final String FIELD_ID = "id";
    private static final String FIELD_SHORT_DESCRIPTION = "shortDescription";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_VIDEO_URL = "videoURL";
    private static final String FIELD_THUMBNAIL_URL = "thumbnailURL";

    private final ReadableUtils utils;

    @Inject
    public StepReadable(final ReadableUtils utils) {
        this.utils = utils;
    }

    @Override
    public Step readJson(final JsonReader reader) throws IOException {
        Integer id = null;
        String shortDescription = null;
        String description = null;
        String videoURL = null;
        String thumbnailURL = null;

        reader.beginObject();
        while (reader.hasNext()) {
            final String name = reader.nextName();
            if (FIELD_ID.equals(name)) {
                id = reader.nextInt();
            } else if (FIELD_SHORT_DESCRIPTION.equals(name)) {
                shortDescription = reader.nextString();
            } else if (FIELD_DESCRIPTION.equals(name)) {
                description = reader.nextString();
            } else if (FIELD_VIDEO_URL.equals(name)) {
                videoURL = reader.nextString();
            } else if (FIELD_THUMBNAIL_URL.equals(name)) {
                thumbnailURL = reader.nextString();
            } else {
                throw new IOException("Unknown field: " + name);
            }
        }
        reader.endObject();

        utils.checkFieldNotNull(id, FIELD_ID);
        utils.checkFieldNotNull(shortDescription, FIELD_SHORT_DESCRIPTION);
        utils.checkFieldNotNull(description, FIELD_DESCRIPTION);
        utils.checkFieldNotNull(videoURL, FIELD_VIDEO_URL);
        utils.checkFieldNotNull(thumbnailURL, FIELD_THUMBNAIL_URL);

        final Step result = new Step();
        result.setId(id);
        result.setShortDescription(shortDescription);
        result.setDescription(description);
        result.setVideoURL(videoURL);
        result.setThumbnailURL(thumbnailURL);
        return result;
    }
}
