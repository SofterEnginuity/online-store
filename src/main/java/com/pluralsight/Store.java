
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
                switch (choice) {
                    case 1:
                        displayProducts(inventory, cart, scanner);
                        break;
                    case 2:
                        System.out.println("Your cart");
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

        }

        public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
            System.out.println("Available Products:");
            for (Product product : inventory) {
                System.out.println(product);
            }
            while (true) {
                System.out.println("Please enter the id of an item to add it to your cart (or type 'X' to return to the main menu):");
                String id = scanner.nextLine();
                if (id.equalsIgnoreCase("X")) {
                    break;
                }
                Product product = findProductById(id, inventory, cart, scanner);
                if (product == null) {
                    System.out.println("Invalid product ID. Please try again.");
                    continue;
                }
                cart.add(product);
                System.out.println(product.getName() + " added to your cart");
                System.out.println("Cart size: " + cart.size());
                }
            }

        public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
            System.out.println("Items in your Carts:");
            for (Product product : cart) {
                System.out.println(product);
            }
            double total = 0.0;
            for (Product product : cart) {
                total += product.getPrice();
            }
            System.out.println("Cart total: $" + total);

            System.out.println("Please enter the ID of an item to remove it from your cart,");
            System.out.println("or enter 'C' to check out, or 'R' to return to the products menu:");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("C")) {
                checkOut(cart, 0);
                return;
            } else if (input.equalsIgnoreCase("R")) {
                return;
            } else {
                boolean found = false;
                for (Product product : cart) {
                    if (product.getId().equalsIgnoreCase(input)) {
                        cart.remove(product);
                        System.out.println("Removed " + product.getName() + " from your cart!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Item with ID '" + input + "' not found in your cart.");
                }
            }

        }

        public static void checkOut(ArrayList<Product> cart, double totalAmount) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Checkout Items");
            totalAmount = 0;
            for (Product product : cart) {
                System.out.println(product);
                totalAmount += product.getPrice();
            }
            System.out.printf("Cart total: $%.2f%n", totalAmount);

            System.out.println("Would you like to confirm this purchase?");
            System.out.println("Please Select 'Y' for Yes, and 'N' for No.");

            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                boolean paid = false;
                while (!paid) {
                    System.out.println("Enter cash amount:");
                    double cash = scanner.nextDouble();
                    scanner.nextLine(); // clear newline

                    if (cash >= totalAmount) {
                        double change = cash - totalAmount;
                        System.out.printf("Payment successful. Your change is $%.2f%n", change);
                        cart.clear();
                        paid = true;
                    } else {
                        System.out.println("Insufficient funds. Try again or type 'cancel' to return.");
                        System.out.println("Select any key to try again or type 'X' to cancel checkout.");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("X")) {
                            System.out.println("Checkout canceled. Returning to main menu.");
                            break;
                        }
                    }
                }
            } else if (confirm.equalsIgnoreCase("no")) {
                System.out.println("Returning to main menu.");
            } else {
                System.out.println("Invalid selection.");
            }
        }

        public static Product findProductById(String id, ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
            for (Product product : inventory) {
                if (product.getId().equalsIgnoreCase(id)) {
                    return product;
                }
            }
            return null;
        }
     }



