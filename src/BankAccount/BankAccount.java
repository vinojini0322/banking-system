package BankAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BankAccount {
    static HashMap<Long, CustomerDto> customerList = new HashMap<>();
    static HashMap<Long, AccountDto> accountList = new HashMap<>();
    static long uniqueId = 0;
    static String seperator = "-------------------------";

    public static void main(String args[]) {
        displayMenu();
    }

    private static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        char selectedService;
        System.out.println(seperator + "\nWelcome to the " +
                           "BankOfBank\n" + seperator);
        do {
            menuList();

            selectedService = sc.next().toUpperCase().charAt(0);
            switch (selectedService) {
                case 'A':
                    System.out.println(seperator + "\nBalance\n" + seperator);
                    break;
                case 'B':
                    System.out.println(seperator + "\nDeposit\n" + seperator);
                    break;
                case 'C':
                    System.out.println(seperator + "\nWithdraw\n" + seperator);
                    break;
                case 'D':
                    System.out.println(seperator + "\nPreviouse Transaction\n" + seperator);
                    break;
                case 'E':
                    System.out.println(seperator + "\nExit\n" + seperator);
                    break;
                case 'X': {
                    System.out.println(seperator + "\nCreate a new account\n" + seperator);
                    createCustomer();
                    break;
                }
                case 'Y': {
                    System.out.println(seperator + "\nCreate a new customer\n" + seperator);
                    createBankAccount();
                    break;
                }
                default:
                    System.out.println(seperator + "\nInvalid option! Enter a " +
                                       "valid option again\n" + seperator);
                    break;
            }
        } while (selectedService != 'E');
    }

    private static void menuList() {

        System.out.println(seperator + "\nPlease choose a " +
                           "service\n" + seperator);
        System.out.println("A. Check Balance\nB. Deposit\nC. Withdraw\nD. Previous " +
                           "transaction\nX. Create customer\nY. Create Account\nE" +
                           ". Exit");
    }

    private static CustomerDto createCustomer() {
        uniqueId++;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer First Name");
        String firstName = sc.nextLine();
        System.out.println("Enter Customer Last Name");
        String lastName = sc.nextLine();
        System.out.println("Enter Customer nic");
        String nic = sc.nextLine();
        System.out.println("Enter Customer Account Number");
        CustomerDto customer = new CustomerDto(uniqueId, firstName, lastName, nic);
        customerList.put(customer.getCustomerId(), customer);
        System.out.println(customerList.toString());
        return customer;
    }

    private static AccountDto createBankAccount() {
        Scanner sc = new Scanner(System.in);
        AccountDto account = null;
        System.out.println("Enter Customer Id");
        long customerId = sc.nextLong();
        CustomerDto customerDto1 = customerList.get(customerId);
        if (accountList.containsKey(customerId)) {
            System.out.println("This customer already has a bank account");
            System.out.println();
        } else {
            if (customerDto1 != null) {
                long accountNumber;
                ArrayList<Long> arrayList = new ArrayList<Long>();
                Random rN = new Random();
                long genNum;
                do {
                    genNum = rN.nextLong();
                } while (arrayList.contains(genNum));
                arrayList.add(genNum);
                accountNumber = genNum;
                System.out.println("Enter account balance");
                double balance = sc.nextDouble();
                account = new AccountDto(customerId, accountNumber, balance);
                accountList.put(customerId, account);
            } else {
                System.out.println("Please enter user details first");
                displayMenu();
            }
        }
        return account;
    }

    public static Long getAccountByCustomerId(long cusId) {
        AccountDto acc = accountList.get(cusId);
        return acc.getAccountNumber();
    }
}
