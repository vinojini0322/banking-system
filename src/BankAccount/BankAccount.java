package BankAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BankAccount {
    static HashMap<Long, CustomerDto> customerList = new HashMap<>();
    static HashMap<Long, AccountDto> accountList = new HashMap<>();
    static HashMap<Long, Long> customerAccountList = new HashMap<>();
    static long uniqueId = 0;
    static String seperator = "-------------------------";


    public static void main(String args[]) {
        System.out.println(seperator + "\nWelcome to the " +
                           "BankOfBank\n" + seperator);
        displayMenu();
    }

    private static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        char selectedService;

        do {
            menuList();

            selectedService = sc.next().toUpperCase().charAt(0);
            switch (selectedService) {
                case 'A':
                    System.out.println(seperator + "\nBalance\n" + seperator);
                    checkBalance();
                    break;
                case 'B':
                    System.out.println(seperator + "\nDeposit\n" + seperator);
                    deposit();
                    break;
                case 'C':
                    System.out.println(seperator + "\nWithdraw\n" + seperator);
                    withdraw();
                    break;
                case 'D':
                    System.out.println(seperator + "\nCheck Previous Transaction\n" + seperator);
                    checkPreviousTransaction();
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
                case 'E':
                    System.out.println(seperator + "\nThank you for reaching out BankOfBank!\n" +
                                       "Have a nice day!\n" + seperator);
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
        System.out.println("You have successfully entered the details of : " + firstName + " " + lastName);
        CustomerDto customer = new CustomerDto(uniqueId, firstName, lastName, nic);
        customerList.put(customer.getCustomerId(), customer);
        return customer;
    }

    private static AccountDto createBankAccount() {
        Scanner sc = new Scanner(System.in);
        AccountDto account = null;
        System.out.println("Enter Customer Id");
        long customerId = sc.nextLong();
        CustomerDto customer = customerList.get(customerId);
        if (customerAccountList.containsKey(customerId)) {
            System.out.println("This customer already has a bank account");
            System.out.println();
            getAccountByAccountNumber(customerAccountList.get(customerId));
        } else {
            if (customer != null) {
                long accountNumber;
                ArrayList<Long> arrayList = new ArrayList<Long>();
                Random rN = new Random();
                long genNum;
                do {
                    genNum = rN.nextLong();
                } while (arrayList.contains(genNum) && genNum > 0);
                arrayList.add(genNum);
                accountNumber = genNum;
                System.out.println("Enter account balance");
                double balance = sc.nextDouble();
                account = new AccountDto(customerId, accountNumber, balance);
                accountList.put(accountNumber, account);
                customerAccountList.put(customerId, accountNumber);
                System.out.println("Successfully created an account for " + customer.getFirstName() + " " + customer.getLastName());
            } else {
                System.out.println("Please create user first");
            }
        }
        return account;
    }

    public static AccountDto getAccountByAccountNumber(long accNumber) {
        AccountDto acc = accountList.get(accNumber);
        System.out.println("Account number: " + acc.getAccountNumber());
        return acc;
    }

    public static Double checkBalance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer Id: ");
        long id = sc.nextLong();
        AccountDto account = getAccountByAccountNumber(customerAccountList.get(id));
        System.out.println(account.getBalance());
        return account.getBalance();
    }

    public static Double withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer Id: ");
        long id = sc.nextLong();
        long accNumber = customerAccountList.get(id);
        AccountDto acc = accountList.get(accNumber);
        System.out.println("Enter amount you want to withdraw: ");
        double withdrawelAmount = sc.nextDouble();
        double accbalance = acc.getBalance();
        if (accbalance > withdrawelAmount && (accbalance - withdrawelAmount) > 0) {
            acc.setBalance(accbalance - withdrawelAmount);
            accountList.replace(id, acc);
            System.out.println("Successfully withdrawed : Rs " + withdrawelAmount + " from your " +
                               "account" +
                               ".\nYour current balance is: " + acc.getBalance());
            acc.setPreviousTransaction(-withdrawelAmount);
            return acc.getWithdrawel();
        } else {
            System.out.println("The transaction cannot be proceeded because of insufficient " +
                               "balance");
            return null;
        }
    }

    public static Double deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer Id: ");
        long id = sc.nextLong();
        long accNumber = customerAccountList.get(id);
        AccountDto acc = accountList.get(accNumber);
        System.out.println("Enter amount you want to withdraw: ");
        double depositAmount = sc.nextDouble();
        acc.setBalance(acc.getBalance() + depositAmount);
        acc.setPreviousTransaction(depositAmount);
        return depositAmount;
    }

    public static Double checkPreviousTransaction() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer Id: ");
        long id = sc.nextLong();
        long accNumber = customerAccountList.get(id);
        AccountDto acc = accountList.get(accNumber);
        if (acc.getPreviousTransaction() > 0) {
            System.out.println("Previous transcation is a deposit of " + acc.getPreviousTransaction());
        } else {
            System.out.println("Previous transcation is a withdrawel of " + acc.getPreviousTransaction());
        }
        return acc.getPreviousTransaction();
    }
}
