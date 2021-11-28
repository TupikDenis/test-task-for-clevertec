package com.company;

public class Card implements PrintInfo{
    private int number;
    private String name;
    private String surname;

    public Card(int number, String customer){
        this.number = number;

        String[] customerInfo = customer.split(" ");
        this.surname = customerInfo[0];
        this.name = customerInfo[1];
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void print(){
        System.out.println("////////////////////////////////////////////////////////////////////");
        System.out.println("INFORMATION ABOUT CARD №" + this.number + "\n");
        System.out.println("NUMBER - " + this.number);
        System.out.println("SURNAME - " + this.surname);
        System.out.println("NAME - " + this.name);
        System.out.println("////////////////////////////////////////////////////////////////////");
    }
}

