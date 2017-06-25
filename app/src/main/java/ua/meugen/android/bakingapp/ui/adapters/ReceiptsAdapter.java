package ua.meugen.android.bakingapp.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.meugen.android.bakingapp.databinding.ItemReceiptBinding;
import ua.meugen.android.bakingapp.providers.ReceiptsContract;

/**
 * @author meugen
 */

public class ReceiptsAdapter extends RecyclerView.Adapter<BindingViewHolder<?, Cursor>> {

    private final LayoutInflater inflater;
    private Cursor cursor;

    public OnReceiptClickListener onReceiptClickListener;

    public ReceiptsAdapter(final Context context, final Cursor cursor) {
        this.inflater = LayoutInflater.from(context);
        this.cursor = cursor;
    }

    public void swapCursor(final Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public OnReceiptClickListener getOnReceiptClickListener() {
        return onReceiptClickListener;
    }

    public void setOnReceiptClickListener(final OnReceiptClickListener onReceiptClickListener) {
        this.onReceiptClickListener = onReceiptClickListener;
    }

    @Override
    public BindingViewHolder<?, Cursor> onCreateViewHolder(
            final ViewGroup parent, final int viewType) {
        return new ReceiptItemViewHolder(ItemReceiptBinding
                .inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(
            final BindingViewHolder<?, Cursor> holder, final int position) {
        cursor.moveToPosition(position);
        holder.bind(cursor);
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    private void callOnReceiptClick(final int id) {
        if (onReceiptClickListener != null) {
            final Uri uri = ReceiptsContract.ReceiptColumns.URI.buildUpon()
                    .appendPath(Integer.toString(id)).build();
            onReceiptClickListener.onReceiptClick(uri);
        }
    }

    private class ReceiptItemViewHolder extends BindingViewHolder<ItemReceiptBinding, Cursor>
            implements View.OnClickListener {

        private int id;

        public ReceiptItemViewHolder(final ItemReceiptBinding binding) {
            super(binding);
        }

        @Override
        public void bind(final Cursor cursor) {
            this.id = cursor.getInt(0);
            binding.setName(cursor.getString(1));
            binding.setServings(cursor.getInt(2));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            callOnReceiptClick(id);
        }
    }

    public interface OnReceiptClickListener {

        void onReceiptClick(Uri uri);
    }
}
