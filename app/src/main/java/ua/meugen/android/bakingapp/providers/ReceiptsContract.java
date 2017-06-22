package ua.meugen.android.bakingapp.providers;

import android.net.Uri;
import android.provider.BaseColumns;

public interface ReceiptsContract {

    String AUTHORITY = "ua.meugen.android.bakingapp";

    Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    interface ReceiptColumns extends BaseColumns {

        String PATH = "receipts";

        Uri URI = BASE_URI.buildUpon().appendPath(PATH).build();

        String COLUMN_NAME = "name";
        String COLUMN_SERVINGS = "servings";
        String COLUMN_IMAGE = "image";
    }

    interface IngredientColumns extends BaseColumns {

        String PATH = "ingredients";

        Uri URI = BASE_URI.buildUpon().appendPath(PATH).build();

        String COLUMN_RECEIPT_ID = "receipt_id";
        String COLUMN_QUANTITY = "quantity";
        String COLUMN_MEASURE = "measure";
        String COLUMN_INGREDIENT = "ingredient";
    }

    interface StepColumns extends BaseColumns {

        String PATH = "steps";

        Uri URI = BASE_URI.buildUpon().appendPath(PATH).build();

        String COLUMN_ID = "id";
        String COLUMN_RECEIPT_ID = "receipt_id";
        String COLUMN_SHORT_DESCRIPTION = "short_description";
        String COLUMN_DESCRIPTION = "description";
        String COLUMN_VIDEO_URL = "video_url";
        String COLUMN_THUMBNAIL_URL = "thumbnail_url";
    }
}
