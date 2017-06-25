package ua.meugen.android.bakingapp.ui.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.meugen.android.bakingapp.databinding.FragmentReceiptsListBinding;
import ua.meugen.android.bakingapp.providers.ReceiptsContract;
import ua.meugen.android.bakingapp.ui.adapters.ReceiptsAdapter;

/**
 * @author meugen
 */

public class ReceiptsListFragment extends Fragment {

    private static final int RECEIPTS_LOADER_ID = 1;

    private FragmentReceiptsListBinding binding;

    private ReceiptsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        binding = FragmentReceiptsListBinding.inflate(
                inflater, container, false);
        binding.recycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(RECEIPTS_LOADER_ID, null,
                new ReceiptsLoaderCallbacks());
    }

    private void setupAdapter(final Cursor cursor) {
        if (adapter == null) {
            adapter = new ReceiptsAdapter(getContext(), cursor);
            adapter.setOnReceiptClickListener(
                    (ReceiptsAdapter.OnReceiptClickListener) getActivity());
            binding.recycler.setAdapter(adapter);
        } else {
            adapter.swapCursor(cursor);
        }
    }

    private class ReceiptsLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
            final String[] columns = new String[] {
                    ReceiptsContract.ReceiptColumns._ID,
                    ReceiptsContract.ReceiptColumns.COLUMN_NAME,
                    ReceiptsContract.ReceiptColumns.COLUMN_SERVINGS };
            return new CursorLoader(getContext(),
                    ReceiptsContract.ReceiptColumns.URI,
                    columns, null, null, null);
        }

        @Override
        public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
            setupAdapter(data);
        }

        @Override
        public void onLoaderReset(final Loader<Cursor> loader) {}
    }
}
