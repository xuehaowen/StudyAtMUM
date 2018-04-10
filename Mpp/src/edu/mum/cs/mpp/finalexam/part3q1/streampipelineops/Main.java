package edu.mum.cs.mpp.finalexam.part3q1.streampipelineops;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.cs.mpp.finalexam.part3q1.model.Customer;
import edu.mum.cs.mpp.finalexam.part3q1.model.CustomerAccountPair;
import edu.mum.cs.mpp.finalexam.part3q1.model.CustomerTestData;
import edu.mum.cs.mpp.finalexam.part3q1.model.Transaction;

/**
 * USE STREAM PIPELINES TO SOLVE THE PROBLEMS GIVEN HERE
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Start...");

        prob1();
        prob2();
        prob3();
        prob4();
        prob5();
    }

    /* Create a stream pipeline that does the following: */

    // Print all Names of Customers whose address is in
    // the state of California, "CA"
    public static void prob1() {
        List<Customer> customers = CustomerTestData.getCustomers();
        // Implement your code here
        customers.stream().filter(c -> c.getAddress().getState().equals("CA"))
                .forEach(c -> System.out.println(c.getName()));
    }

    // Create a stream pipeline that obtains a list of
    // all customer/account pairs extracted from the list of Customers
    // for which there is at least one transaction in the account.
    // Print the list in ascending order of Customer names.
    public static void prob2() {
        //use this list
        List<Customer> customers = CustomerTestData.getCustomers();
        // Implement your code here
        System.out.println(customers.stream().filter(c -> c.getAccount().getTrxns().size() > 0)
                .map(c -> new CustomerAccountPair(c.getName(), c.getAccount().getAccountNumber()))
                .sorted(Comparator.comparing(CustomerAccountPair::getCustomerName))
                .collect(Collectors.toList()));
    }

    // Create a stream pipeline to print all the transactions for all the customers' accounts,
    // sorting them by amount (small to high), and print to console
    // (Note: there is an instance variable "amount" in Transaction)
    public static void prob3() {
        //use this list
        List<Customer> customers = CustomerTestData.getCustomers();
        // Implement your code here
        System.out.println(customers.stream().flatMap(c -> c.getAccount().getTrxns().stream())
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList()));
    }

    //Create a stream pipeline to find all transactions done after year 2000
    //and sort them by amount (small to high), and print to console
    //(Note: there is an instance variable "amount" in Transaction)
    public static void prob4() {
        //use this list
        List<Customer> customers = CustomerTestData.getCustomers();
        System.out.println(customers.stream().flatMap(c -> c.getAccount().getTrxns().stream())
                .filter(t -> t.getDate().isAfter(LocalDate.of(2000, 12, 31)))
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList()));
    }

    // Create a stream pipeline to find all Accounts with balance,
    // more than $5000
    // and print to console
    public static void prob5() {
        //Use this list
        List<Customer> customers = CustomerTestData.getCustomers();
        // Implement your code here
        System.out.println(customers.stream().map(c -> c.getAccount())
                .filter(a -> a.getBalance() > 5000)
                .collect(Collectors.toList()));
    }
}
