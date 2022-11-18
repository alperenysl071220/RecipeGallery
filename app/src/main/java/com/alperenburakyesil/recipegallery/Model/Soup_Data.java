package com.alperenburakyesil.recipegallery.Model;

public class Soup_Data {
    String publisher;
    String soup_name;
    String quantity;
    String cooking_time;
    String ingredient;
    String cooking_type;
    String image;

    public Soup_Data() {
    }

    public Soup_Data(String publisher, String soup_name, String quantity, String cooking_time, String ingredient, String cooking_type, String image) {
        this.publisher = publisher;
        this.soup_name = soup_name;
        this.quantity = quantity;
        this.cooking_time = cooking_time;
        this.ingredient = ingredient;
        this.cooking_type = cooking_type;
        this.image = image;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSoup_name() {
        return soup_name;
    }

    public void setSoup_name(String soup_name) {
        this.soup_name = soup_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getCooking_type() {
        return cooking_type;
    }

    public void setCooking_type(String cooking_type) {
        this.cooking_type = cooking_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
