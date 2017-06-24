package ua.meugen.android.bakingapp.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ua.meugen.android.bakingapp.databinding.ItemReceiptBinding;

/**
 * @author meugen
 */

public class ReceiptsAdapter extends RecyclerView.Adapter<BindingViewHolder<?, Cursor>> {

    private final LayoutInflater inflater;
    private Cursor cursor;

    public ReceiptsAdapter(final Context context, final Cursor cursor) {
        this.inflater = LayoutInflater.from(context);
        this.cursor = cursor;
    }

    public void swapCursor(final Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
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

    private class ReceiptItemViewHolder extends BindingViewHolder<ItemReceiptBinding, Cursor> {

        public ReceiptItemViewHolder(final ItemReceiptBinding binding) {
            super(binding);
        }

        @Override
        public void bind(final Cursor cursor) {
            binding.setName(cursor.getString(0));
            binding.setServings(cursor.getInt(1));
        }
    }
}
