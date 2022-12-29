package com.example.registerproduct;

public class ProductModel {
    private int productId;
    private String name;
    private String quantity;
    private int price;

    public ProductModel(int productId, String name, String quantity, int price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public ProductModel(){

    }
    @Override
    public String toString() {
        return "Product:" +
                " " + productId + '\''+
                ", name='" + name + '\'' +
                ", quantity=" + quantity + '\''+
                ",  price=" + price +
                ' ';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
