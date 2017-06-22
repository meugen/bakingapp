package ua.meugen.android.bakingapp.model;

import java.util.List;

public class Receipt {

    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private String servings;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(final List<Step> steps) {
        this.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(final String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }
}
