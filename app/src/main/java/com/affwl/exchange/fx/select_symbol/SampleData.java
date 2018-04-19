package com.affwl.exchange.fx.select_symbol;

import java.util.ArrayList;
import java.util.List;



public class SampleData {
    public  static List<Customer> addSampleCustomers(){
        List<Customer> customers = new ArrayList<Customer> ();

        Customer customer1 = new Customer();
        customer1.setId((long) 1);
        customer1.setName("EURUSD");
        customer1.setEmailAddress("Euro vs US Dollar");
        customers.add(customer1);


        Customer customer2 = new Customer();
        customer2.setId((long) 2);
        customer2.setName("USDSEK");
        customer2.setEmailAddress("US Dollar vs Sweden Kronor");
        customers.add(customer2);


        Customer customer3 = new Customer();
        customer3.setId((long) 3);
        customer3.setName("NZDUSD");
        customer3.setEmailAddress("New Zealand Dollar vs US Dollar");
        customers.add(customer3);


        Customer customer4 = new Customer();
        customer4.setId((long) 4);
        customer4.setName("USDMXN");
        customer4.setEmailAddress("US Dollar vs Mexican Peso");
        customers.add(customer4);


        Customer customer5 = new Customer();
        customer5.setId((long) 5);
        customer5.setName("USDNH");
        customer5.setEmailAddress("US Dollar vs Chinese Yuan");
        customers.add(customer5);

        Customer customer6 = new Customer();
        customer6.setId((long) 6);
        customer6.setName("USDRUB");
        customer6.setEmailAddress("US Dollar vs Russian Ruble");
        customers.add(customer6);

        Customer customer7 = new Customer();
        customer7.setId((long) 7);
        customer7.setName("GBPUSD");
        customer7.setEmailAddress("Great Britain Pound vs Dollar");
        customers.add(customer7);

        Customer customer8 = new Customer();
        customer8.setId((long) 8);
        customer8.setName("AUDUSD");
        customer8.setEmailAddress("Astralian Dollar vs US Dollar");
        customers.add(customer8);


        Customer customer9 = new Customer();
        customer9.setId((long) 9);
        customer9.setName("USDCAD");
        customer9.setEmailAddress("US Dollar vs Cannadian Dollar");
        customers.add(customer9);


        Customer customer10 = new Customer();
        customer10.setId((long) 10);
        customer10.setName("USDCAD");
        customer10.setEmailAddress("Us Dollar vs Canadian Dollar");
        customers.add(customer10);


        Customer customer11 = new Customer();
        customer11.setId((long) 11);
        customer11.setName("DFGHJK");
        customer11.setEmailAddress("A Dollar vs B Dollar");
        customers.add(customer11);

        Customer customer12 = new Customer();
        customer12.setId((long) 12);
        customer12.setName("SFDAGH");
        customer12.setEmailAddress("XZ Dollar vs ZX Dollar");
        customers.add(customer12);


        return customers;
    }
}
