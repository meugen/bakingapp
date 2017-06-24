package ua.meugen.android.bakingapp.model;

public class Ingredient {

    private double quantity;
    private String measure;
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(final double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(final String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(final String ingredient) {
        this.ingredient = ingredient;
    }
}
