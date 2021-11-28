package com.company;

public class Product implements PrintInfo{
    private int id;
    private String name;
    private double price;
    private int number;
    private double cost;
    private double costSale;
    private boolean sale;
    private final double saleCoefficient = 0.9;
    private final int saleProduct = 5;

    public Product(int id, String name, double price, boolean sale){
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale = sale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCost(int code) {
        if(this.isSale() && this.number >= this.saleProduct && code != 0){
            this.cost = this.price * this.number;
            this.costSale = this.price * this.number * this.saleCoefficient;
        } else {
            this.cost = this.price * this.number;
            this.costSale = this.cost;
        }
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getNumber() {
        return this.number;
    }

    public double getCost() {
        return this.cost;
    }

    public double getCostSale() {
        return costSale;
    }

    public boolean isSale() {
        return this.sale;
    }

    public void print(){
        System.out.println("////////////////////////////////////////////////////////////////////");
        System.out.println("INFORMATION ABOUT PRODUCT \"" + this.name + "\"\n");
        System.out.println("NAME - " + this.name);
        System.out.println("PRICE - " + this.price);
        System.out.println("SALE PRODUCT - " + this.sale);
        System.out.println("////////////////////////////////////////////////////////////////////");
    }
}
