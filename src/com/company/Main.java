package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Product> addProducts(String[] args){
        ArrayList<Product> products = new ArrayList<>();
        String id = "", productName = "", price = "", sale = "", fileName = "";

        for(int i=0; i < args.length; i++) {
                if(args[i].equals("products.txt")){
                    fileName = args[i];
                }
        }

        try{
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            int numberLine = 1;
            while (line != null) {
                if(!line.equals("-------------------------------------")){
                    String[] info = line.split("-");

                    switch(numberLine){
                        case 1:
                            id = info[1];
                            break;
                        case 2:
                            productName = info[1];
                            break;
                        case 3:
                            price = info[1];
                            break;
                        case 4:
                            sale = info[1];
                            break;
                    }
                    numberLine++;
                    line = reader.readLine();
                } else {
                    products.add(new Product(Integer.parseInt(id), productName, Double.parseDouble(price), Boolean.parseBoolean(sale)));
                    numberLine = 1;
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //example work of print for show interface work
        for(Product product : products){
            if (product.getId() == 4){
                product.print();
            }
        }

        return products;
    }

    private static ArrayList<Card> addCards(String[] args){
        ArrayList<Card> cards = new ArrayList<>();
        String number = "", customerName = "", fileName = "";

        for(int i=0; i < args.length; i++) {
            if(args[i].equals("cards.txt")){
                fileName = args[i];
            }
        }

        try{
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            int numberLine = 1;
            while (line != null) {
                if(!line.equals("-------------------------------------")){
                    String[] info = line.split("-");

                    switch(numberLine){
                        case 1:
                            number = info[1];
                            break;
                        case 2:
                            customerName = info[1];
                            break;
                    }
                    numberLine++;
                    line = reader.readLine();
                } else {
                    cards.add(new Card(Integer.parseInt(number), customerName));
                    numberLine = 1;
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //example work of print for show interface work
        for(Card card : cards){
            if (card.getNumber() == 1234){
                card.print();
            }
        }

        return cards;
    }

    private static void addCheck(ArrayList<Product> products, ArrayList<Card> cards, String[] args) throws ProductNotFound {
        Check check = new Check(products, cards, args);
        check.print();
    }

    public static void main(String[] args) throws ProductNotFound {
        try {
            addCheck(addProducts(args), addCards(args), args);
        } catch (ProductNotFound e){
            System.out.println(e.getMessage());
        }
    }
}
