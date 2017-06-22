package ua.meugen.android.bakingapp.injections.beans;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ua.meugen.android.bakingapp.utils.IOUtils;

public class DbUpgradeUtil {

    private static final int BUF_SIZE = 256;

    private final AssetManager assetManager;
    private final Pattern pattern;

    @Inject
    public DbUpgradeUtil(final Context context) {
        this.assetManager = context.getAssets();
        this.pattern = Pattern.compile("\\s*([^;]+);");
    }

    public List<String> fetchSqlStatements(final String name, final int version) {
        try {
            Reader reader = null;
            try {
                final InputStream inputStream = assetManager.open(
                        "db/" + name + "/upgrade/" + version + ".sql");
                reader = new BufferedReader(new InputStreamReader(inputStream));
                final StringBuilder builder = new StringBuilder();
                final char[] buffer = new char[BUF_SIZE];

                int count;
                while ((count = reader.read(buffer)) != -1) {
                    builder.append(buffer, 0, count);
                }

                final List<String> result = new ArrayList<>();
                final Matcher matcher = pattern.matcher(builder);
                while (matcher.find()) {
                    result.add(matcher.group(1));
                }
                return result;
            } finally {
                IOUtils.closeQuietly(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
