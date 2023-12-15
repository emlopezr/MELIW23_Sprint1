package com.example.be_java_hisp_w23_g3.entity;

import java.time.LocalDate;

public class Post {
    private Long id;
    private Seller seller;
    private Product product;
    private LocalDate date;
    private int category;
    private Double price;

    public Post(Long id, Seller seller, Product product, LocalDate date, int category, Double price) {
        this.id = id;
        this.seller = seller;
        this.product = product;
        this.date = date;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
