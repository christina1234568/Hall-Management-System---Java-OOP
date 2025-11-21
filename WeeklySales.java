/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class WeeklySales extends SalesRecord{

    public WeeklySales(LocalDate startDate, LocalDate endDate, double amount) {
        super(startDate, endDate, amount);
    }

    public WeeklySales() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAmount() {
        return amount;
    }

    //used for saving records in file
    @Override
    public String toString() {
        return startDate + ";" + endDate + ";" + amount;
    }
    
    //dynamic polymorphism
    @Override
    public ArrayList<WeeklySales> calculateAmounts(ArrayList<Payment> payments) {
        ArrayList<WeeklySales> sales = new ArrayList<>();
        double amount = 0.0;

        //setting the start date to the start date of operations
        LocalDate startDate = LocalDate.of(2020, 1, 1); 
        
        // parsing the date into correct date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!payments.isEmpty()) { //if no payments have been made yet
            LocalDate start = startDate; // start date of the first week
            LocalDate end = start.plusDays(6);
            // setting the end of the week by adding 6 days
            
            ArrayList<Payment> processedPayments = new ArrayList<>();

            // Calculate weekly amount if payment date is in period
            // parsing the payment date from string to LocalDate using formatter
            for (Payment payment : payments) {
                if (inTimePeriod(LocalDate.parse(payment.getPaymentDate(), formatter), start, end)) {
                    amount += payment.getAmount();
                    processedPayments.add(payment);
                }
            }
            
            if (amount > 0){
                WeeklySales weeklySales = new WeeklySales(start, end, amount);
                sales.add(weeklySales);
                amount = 0.0; // Resetting the amount for the next week
            }
            
            payments.removeAll(processedPayments); // removing the processed payments  
            startDate = startDate.plusWeeks(1); 
            // adding one week to the startDate to calculate amount for next week
        }

        return sales; // returning the calculated sales 
    }
    
    
    //dynamic polymorphism
    //ensuring sames ignature as abstratct method in parent class
    //method will accept and ArrayList of WeeklySales obejcts when implemented in code
    @Override
    public double calculateAverage(ArrayList<? extends SalesRecord> sales) {
        int count = 0;
        double sum = 0;
        double average;
        
        // finding sum of all the sales
        // finding number of sales by incrementing count 
        for (Object obj : sales) {
            if (obj instanceof WeeklySales weeklySale) {
                sum += weeklySale.getAmount(); 
                count++;
            }
        }
        // calculating avaerage
        average = sum/count;
        return average;
    }
    
    //dynamic polymorphism
    @Override
     public WeeklySales highestSales(ArrayList<? extends SalesRecord> sales) {
        WeeklySales highestSale = null; // setting to null as it is an object

        // looping through the WeeklySales to find highest sale object 
        for (Object obj : sales) {
            if (obj instanceof WeeklySales currentSale) {
                if (highestSale == null || currentSale.getAmount() > highestSale.getAmount()) {
                    highestSale = currentSale;
                }
            }
        }
        return highestSale;
     }
     
     //dynamic polymorphism
    @Override
     public WeeklySales lowestSales(ArrayList<? extends SalesRecord> sales) {
        WeeklySales lowestSale = null;

        // looping through the WeeklySales to find lowest sale object
        for (Object obj : sales) {
            if (obj instanceof WeeklySales currentSale) {
                if (lowestSale == null || currentSale.getAmount() < lowestSale.getAmount()) {
                    lowestSale = currentSale;
                }
            }
        }
        return lowestSale;
     }  
    
}
