
package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

    public class Store {

        public static void main(String[] args) {
            ArrayList<Product> inventory = new ArrayList<Product>();
            ArrayList<Product> cart = new ArrayList<Product>();

            double totalAmount = 0.0;

            loadInventory("products.csv", inventory);


            Scanner scanner = new Scanner(System.in);
            int choice = -1;

            while (choice != 3) {
                System.out.println("Welcome to the Online Store!");
                System.out.println("1. Show Products");
                System.out.println("2. Show Cart");
                System.out.println("3. Exit");

                choice = scanner.nextInt();
                scanner.nextLine();

                // Call the appropriate method based on user choice
                switch (choice) {
                    case 1:
                        System.out.println("thanks for choosing to display all");
//                        System.out.println();
                        displayProducts(inventory, cart, scanner);
                        break;
                    case 2:
                        System.out.println("Here is your cart");
                        displayCart(cart, scanner, totalAmount);
                        break;
                    case 3:
                        System.out.println("Thank you for shopping with us!");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            }
        }

        public static void loadInventory(String fileName, ArrayList<Product> inventory) {
            try (BufferedReader bufReader = new BufferedReader(new FileReader(fileName))) {
                String line = "";
                while ((line = bufReader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        String id = parts[0];
                        String name = parts[1];
                        double price = Double.parseDouble(parts[2]);
                        Product product = new Product(id, name, price);
                        inventory.add(product);
                    }
                }
            } catch (Exception e) {
                System.out.println("There was an issue with reading the file: " + e.getMessage());
                e.printStackTrace();
            }


            // This method should read a CSV file with product information and
            // populate the inventory ArrayList with com.pluralsight.Product objects. Each line
            // of the CSV file contains product information in the following format:
            // id,name,price
            // where id is a unique string identifier, name is the product name,
            // price is a double value representing the price of the product
        }

        public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
            System.out.println("Available Products:");
            for (Product product : inventory) {
                System.out.println(product);
            }
            System.out.println("Please enter the id of an item to add it to your cart");
            String id = scanner.nextLine();
            boolean found = false;
            for (Product product: inventory){
                if (product.getId().equalsIgnoreCase(id)) {
                    cart.add(product);
                    System.out.println("Added to cart: " + product.getName());
                    found = true;
                    break;
                }
            }

        }
            // and prompt the user to add items to their cart. The method should
            // prompt the user to enter the ID of the product they want to add to
            // their cart. The method should
            // add the selected product to the cart ArrayList.


        public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
           for (Product product: cart){

               System.out.println();
           }
            // This method should display the items in the cart ArrayList, along
            // with the total cost of all items in the cart. The method should
            // prompt the user to remove items from their cart by entering the ID
            // of the product they want to remove. The method should update the cart ArrayList and totalAmount
            // variable accordingly.
        }

        public static void checkOut(ArrayList<Product> cart, double totalAmount) {
            // This method should calculate the total cost of all items in the cart,
            // and display a summary of the purchase to the user. The method should
            // prompt the user to confirm the purchase, and calculate change and clear the cart
            // if they confirm.
        }

        public static Product findProductById(String id, ArrayList<Product> inventory) {
            // This method should search the inventory ArrayList for a product with
            // the specified ID, and return the corresponding com.pluralsight.Product object. If
            // no product with the specified ID is found, the method should return
            // null.
            return null;
        }
    }

