package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product extends BaseEntity {
    private String name;
    private double price;

    public Product() {}

    @JsonBackReference
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, Set<Category> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if (category == null) return;
        if (this.categories.add(category)) {
            category.addProduct(this);
        }
    }

    public void removeCategory(Category category) {
        if (category == null) return;
        if (this.categories.remove(category)) {
            category.removeProduct(this);
        }
    }
}
