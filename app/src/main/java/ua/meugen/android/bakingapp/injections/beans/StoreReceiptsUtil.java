package ua.meugen.android.bakingapp.injections.beans;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ua.meugen.android.bakingapp.injections.components.DaggerModelComponent;
import ua.meugen.android.bakingapp.model.Ingredient;
import ua.meugen.android.bakingapp.model.Receipt;
import ua.meugen.android.bakingapp.model.Step;
import ua.meugen.android.bakingapp.providers.ReceiptsContract;

/**
 * @author meugen
 */

@Singleton
public class StoreReceiptsUtil {

    private static final String PREF_RECEIPTS_STORED = "receipts_stored";

    private final Context context;

    @Inject
    public StoreReceiptsUtil(final Context context) {
        this.context = context;
    }

    public void storeReceipts() {
        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        if (!prefs.getBoolean(PREF_RECEIPTS_STORED, false)) {
            synchronized (StoreReceiptsUtil.class) {
                if (!prefs.getBoolean(PREF_RECEIPTS_STORED, false)) {
                    _storeReceipts();
                    prefs.edit()
                            .putBoolean(PREF_RECEIPTS_STORED, true)
                            .commit();
                }
            }
        }
    }

    private void _storeReceipts() {
        final FetchReceiptsUtil util = DaggerModelComponent
                .create().fetchReceiptsUtil();
        final List<Receipt> receipts = util.fetchReceipts(context);
        final ContentResolver resolver = context.getContentResolver();
        for (Receipt receipt : receipts) {
            final ContentValues receiptValues = new ContentValues();
            receiptValues.put(ReceiptsContract.ReceiptColumns.COLUMN_SERVINGS,
                    receipt.getServings());
            receiptValues.put(ReceiptsContract.ReceiptColumns.COLUMN_IMAGE,
                    receipt.getImage());
            receiptValues.put(ReceiptsContract.ReceiptColumns.COLUMN_NAME,
                    receipt.getName());
            final Uri uri = resolver.insert(ReceiptsContract.ReceiptColumns.URI,
                    receiptValues);

            final long id = Integer.parseInt(uri.getLastPathSegment());
            for (Ingredient ingredient : receipt.getIngredients()) {
                final ContentValues ingredientValues = new ContentValues();
                ingredientValues.put(ReceiptsContract.IngredientColumns.COLUMN_RECEIPT_ID,
                        id);
                ingredientValues.put(ReceiptsContract.IngredientColumns.COLUMN_INGREDIENT,
                        ingredient.getIngredient());
                ingredientValues.put(ReceiptsContract.IngredientColumns.COLUMN_MEASURE,
                        ingredient.getMeasure());
                ingredientValues.put(ReceiptsContract.IngredientColumns.COLUMN_QUANTITY,
                        ingredient.getQuantity());
                resolver.insert(ReceiptsContract.IngredientColumns.URI,
                        ingredientValues);
            }
            for (Step step : receipt.getSteps()) {
                final ContentValues stepValues = new ContentValues();
                stepValues.put(ReceiptsContract.StepColumns.COLUMN_RECEIPT_ID,
                        id);
                stepValues.put(ReceiptsContract.StepColumns.COLUMN_SHORT_DESCRIPTION,
                        step.getShortDescription());
                stepValues.put(ReceiptsContract.StepColumns.COLUMN_DESCRIPTION,
                        step.getDescription());
                stepValues.put(ReceiptsContract.StepColumns.COLUMN_THUMBNAIL_URL,
                        step.getThumbnailURL());
                stepValues.put(ReceiptsContract.StepColumns.COLUMN_VIDEO_URL,
                        step.getVideoURL());
                resolver.insert(ReceiptsContract.StepColumns.URI,
                        stepValues);
            }
        }
    }
}
