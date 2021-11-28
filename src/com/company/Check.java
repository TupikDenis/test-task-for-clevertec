package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Check implements PrintInfo{
    private Map<Integer, Integer> info;
    private ArrayList<Product> product;
    private double totalCost = 0.0;
    private double totalCostWithSale = 0.0;
    private int code = 0;
    private boolean checkSaleProduct = false;

    private String checkString(){
        String checkInfo = "";

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        checkInfo += "CHECK\n";

        if(this.code == 0){
            checkInfo += "NO CARD\n";
        } else {
            checkInfo += "CARD №" + this.code + "\n";
        }

        checkInfo += "\nNUMBER  NAME                 PRICE      COST       COST WITH SALE (10%)\n";
        checkInfo += "--------------------------------------------------------------------\n";
        for(int i=0; i < this.product.size(); i++){
            checkInfo += String.format("%-7s %-20s %-10s %-10s %-10s %n", df.format(this.product.get(i).getNumber()), this.product.get(i).getName(),
                    df.format(this.product.get(i).getPrice()), df.format(this.product.get(i).getCost()), df.format(this.product.get(i).getCostSale()));
        }
        checkInfo += "--------------------------------------------------------------------\n";
        checkInfo += "--------------------------------------------------------------------\n";
        checkInfo += "Total cost - " + df.format(this.totalCost) + "\n";
        if(this.checkSaleProduct){
            checkInfo += "TOTAL COST (with sale) - " + df.format(this.totalCostWithSale) + "\n";
            checkInfo += "\n* - sale product (taken into account when the quantity of goods exceeds 5 and have a sale card) \n";
        }
        checkInfo += "--------------------------------------------------------------------\n";
        return checkInfo;
    }

    private void printCheckInFile(){
        try(FileWriter writer = new FileWriter("check.txt", false)) {
            writer.write(checkString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public Check(ArrayList<Product> products, ArrayList<Card> cards, String[] args) throws ProductNotFound{
        info = new HashMap<>();
        for(int i=0; i < args.length; i++){
            if(!args[i].contains(".txt")) {
                String[] information = args[i].split("-");
                if (information[0].equals("card")) {
                    for(Card card : cards){
                        if(card.getNumber() == Integer.parseInt(information[1])) {
                            this.code = Integer.parseInt(information[1]);
                            break;
                        }
                    }
                } else {
                    boolean flag = true;
                    for(Product product : products) {
                        if (product.getId() == Integer.parseInt(information[0])) {
                            info.put(Integer.parseInt(information[0]), Integer.parseInt(information[1]));
                            flag = false;
                        }
                    }

                    if(flag){
                        throw new ProductNotFound("Product not found!");
                    }


                }
            }
        }

        this.product = new ArrayList<>();
        for(Product product : products){
            for(int key : this.info.keySet()){
                    if (key == product.getId()) {
                        product.setNumber(this.info.get(key));
                        product.setCost(this.code);
                        this.totalCost += product.getCost();
                        if (product.isSale() && this.code != 0) {
                            product.setName(product.getName() + "*");
                            this.totalCostWithSale += product.getCostSale();
                            this.checkSaleProduct = true;
                        } else {
                            this.totalCostWithSale += product.getCost();
                        }
                        this.product.add(product);
                        break;
                    }

            }
        }
    }

    public void print(){
        System.out.println(checkString());
        printCheckInFile();
    }
}
