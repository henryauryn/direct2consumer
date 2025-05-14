package org.henrylightfoot;

import java.util.Scanner;

public class D2C {
    public static void main(String[] args) {
        System.out.println("Do you want to create a new customer or view all existing ones? Type 1 or 2 respectively.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            createNewCustomer();
        } else {
            viewAllCustomers();
        }
    }

    public static void viewAllCustomers() {
        databaseThings.viewAllCustomers();
    }

    public static void createNewCustomer() {
        String customerName;
        String customerDOB;
        String customerEmail;

        ConcreteCustomerFactory customerFactory = new ConcreteCustomerFactory();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        customerName = scanner.nextLine();
        System.out.println("Enter customer DOB: ");
        customerDOB = scanner.nextLine();
        System.out.println("Enter customer email: ");
        customerEmail = scanner.nextLine();
        databaseThings.insertCustomer(customerName, customerDOB, customerEmail);
        Customer myFirstCustomer = customerFactory.createCustomer(customerName, customerDOB, customerEmail);
        System.out.println(myFirstCustomer);
    }
}
