package ua.meugen.android.bakingapp.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import ua.meugen.android.bakingapp.BakingApp;
import ua.meugen.android.bakingapp.injections.beans.DbUpgradeUtil;


public class ReceiptsOpenHelper extends SQLiteOpenHelper {

    private static final String NAME = "receipts";
    private static final int VERSION = 1;

    private final Context context;

    public ReceiptsOpenHelper(final Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        onUpgrade(db, 0, VERSION);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        try {
            final DbUpgradeUtil dbUpgradeUtil = BakingApp
                    .appComponent(context).dbUpgradeUtil();

            db.beginTransaction();
            for (int version = oldVersion + 1; version <= newVersion; version++) {
                final List<String> sqlList = dbUpgradeUtil.fetchSqlStatements(NAME, VERSION);
                for (String sql : sqlList) {
                    db.execSQL(sql);
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
