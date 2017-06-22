package ua.meugen.android.bakingapp.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class ReceiptsProvider extends ContentProvider {

    private static final int MATCH_RECEIPTS_LIST = 1;
    private static final int MATCH_RECEIPTS_BY_ID = 2;
    private static final int MATCH_INGREDIENTS_BY_RECEIPTS_ID = 3;
    private static final int MATCH_STEPS_BY_RECEIPTS_ID = 4;

    private ReceiptsOpenHelper openHelper;
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        openHelper = new ReceiptsOpenHelper(getContext());

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ReceiptsContract.AUTHORITY,
                ReceiptsContract.ReceiptColumns.PATH,
                MATCH_RECEIPTS_LIST);
        uriMatcher.addURI(ReceiptsContract.AUTHORITY,
                ReceiptsContract.ReceiptColumns.PATH + "/#",
                MATCH_RECEIPTS_BY_ID);
        uriMatcher.addURI(ReceiptsContract.AUTHORITY,
                ReceiptsContract.ReceiptColumns.PATH + "/#/" + ReceiptsContract.IngredientColumns.PATH,
                MATCH_INGREDIENTS_BY_RECEIPTS_ID);
        uriMatcher.addURI(ReceiptsContract.AUTHORITY,
                ReceiptsContract.ReceiptColumns.PATH + "/#/" + ReceiptsContract.StepColumns.PATH,
                MATCH_STEPS_BY_RECEIPTS_ID);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull final Uri uri,
            @Nullable final String[] projection,
            @Nullable final String selection,
            @Nullable final String[] selectionArgs,
            @Nullable final String sortOrder) {

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull final Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(
            @NonNull final Uri uri,
            @Nullable final ContentValues values) {
        return null;
    }

    @Override
    public int delete(
            @NonNull final Uri uri,
            @Nullable final String selection,
            @Nullable final String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull final Uri uri, @Nullable final ContentValues values, @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return 0;
    }
}
