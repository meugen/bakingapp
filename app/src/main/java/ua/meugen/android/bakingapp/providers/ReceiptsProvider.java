package ua.meugen.android.bakingapp.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class ReceiptsProvider extends ContentProvider {

    private static final int MATCH_RECEIPTS_LIST = 1;
    private static final int MATCH_RECEIPTS_BY_ID = 2;
    private static final int MATCH_INGREDIENTS_LIST = 3;
    private static final int MATCH_INGREDIENTS_BY_RECEIPTS_ID = 4;
    private static final int MATCH_STEPS_LIST = 5;
    private static final int MATCH_STEPS_BY_RECEIPTS_ID = 6;

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
                ReceiptsContract.IngredientColumns.PATH,
                MATCH_INGREDIENTS_LIST);
        uriMatcher.addURI(ReceiptsContract.AUTHORITY,
                ReceiptsContract.ReceiptColumns.PATH + "/#/" + ReceiptsContract.IngredientColumns.PATH,
                MATCH_INGREDIENTS_BY_RECEIPTS_ID);
        uriMatcher.addURI(ReceiptsContract.AUTHORITY,
                ReceiptsContract.StepColumns.PATH,
                MATCH_STEPS_LIST);
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
        Cursor cursor;

        final int match = uriMatcher.match(uri);
        if (match == MATCH_RECEIPTS_LIST) {
            cursor = openHelper.getReadableDatabase().query(
                    ReceiptsContract.ReceiptColumns.TABLE,
                    projection, selection, selectionArgs,
                    null, null, null);
        } else if (match == MATCH_RECEIPTS_BY_ID) {
            final String id = uri.getLastPathSegment();
            final String _selection = ReceiptsContract.ReceiptColumns._ID + "=?";
            final String[] _selectionArgs = new String[] { id };
            cursor = openHelper.getReadableDatabase().query(
                    ReceiptsContract.ReceiptColumns.TABLE,
                    projection, _selection, _selectionArgs,
                    null, null, null);
        } else if (match == MATCH_INGREDIENTS_BY_RECEIPTS_ID) {
            final String id = uri.getPathSegments().get(1);
            final String _selection = ReceiptsContract.IngredientColumns.COLUMN_RECEIPT_ID + "=?";
            final String[] _selectionArgs = new String[] { id };
            cursor = openHelper.getReadableDatabase().query(
                    ReceiptsContract.IngredientColumns.TABLE,
                    projection, _selection, _selectionArgs,
                    null, null, null);
        } else if (match == MATCH_STEPS_BY_RECEIPTS_ID) {
            final String id = uri.getPathSegments().get(1);
            final String _selection = ReceiptsContract.StepColumns.COLUMN_RECEIPT_ID + "=?";
            final String[] _selectionArgs = new String[] { id };
            cursor = openHelper.getReadableDatabase().query(
                    ReceiptsContract.StepColumns.TABLE,
                    projection, _selection, _selectionArgs,
                    null, null, null);
        } else {
            throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull final Uri uri) {
        final int match = uriMatcher.match(uri);
        if (match == MATCH_RECEIPTS_LIST || match == MATCH_RECEIPTS_BY_ID) {
            return "bakingapp/receipt";
        } else if (match == MATCH_INGREDIENTS_LIST || match == MATCH_INGREDIENTS_BY_RECEIPTS_ID) {
            return "bakingapp/ingredient";
        } else if (match == MATCH_STEPS_LIST || match == MATCH_STEPS_BY_RECEIPTS_ID) {
            return "bakingapp/step";
        } else {
            throw new IllegalArgumentException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(
            @NonNull final Uri uri,
            @Nullable final ContentValues values) {
        Uri result;

        final int match = uriMatcher.match(uri);
        if (match == MATCH_RECEIPTS_LIST) {
            final long id = openHelper.getWritableDatabase().insertOrThrow(
                    ReceiptsContract.ReceiptColumns.TABLE,
                    null, values);
            result = ReceiptsContract.ReceiptColumns.URI.buildUpon()
                    .appendPath(Long.toString(id)).build();
        } else if (match == MATCH_INGREDIENTS_LIST) {
            final long id = openHelper.getWritableDatabase().insert(
                    ReceiptsContract.IngredientColumns.TABLE,
                    null, values);
            result = ReceiptsContract.IngredientColumns.URI.buildUpon()
                    .appendPath(Long.toString(id)).build();
        } else if (match == MATCH_STEPS_LIST) {
            final long id = openHelper.getWritableDatabase().insertOrThrow(
                    ReceiptsContract.StepColumns.TABLE,
                    null, values);
            result = ReceiptsContract.StepColumns.URI.buildUpon()
                    .appendPath(Long.toString(id)).build();
        } else {
            throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        final ContentResolver resolver = getContext().getContentResolver();
        resolver.notifyChange(uri, null);
        resolver.notifyChange(result, null);
        return result;
    }

    @Override
    public int delete(
            @NonNull final Uri uri,
            @Nullable final String selection,
            @Nullable final String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public int update(
            @NonNull final Uri uri,
            @Nullable final ContentValues values,
            @Nullable final String selection,
            @Nullable final String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
