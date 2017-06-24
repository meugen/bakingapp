package ua.meugen.android.bakingapp.ui.adapters;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @author meugen
 */

public abstract class BindingViewHolder<BINDING extends ViewDataBinding, DATA>
        extends RecyclerView.ViewHolder {

    protected final BINDING binding;

    public BindingViewHolder(final BINDING binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public abstract void bind(final DATA data);
}
