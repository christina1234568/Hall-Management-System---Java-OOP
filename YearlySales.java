/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;


/**
 *
 * @author user
 */
public class YearlySales extends SalesRecord{

    public YearlySales(LocalDate startDate, LocalDate endDate, double amount) {
        super(startDate, endDate, amount);
    }

    public YearlySales() {
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
    
    @Override
    public String toString() {
        return startDate + ";" + endDate + ";" + amount;
    }
    
    @Override
    public ArrayList<YearlySales> calculateAmounts(ArrayList<Payment> payments) {
        ArrayList<YearlySales> sales = new ArrayList<>();
        double amount = 0.0;
        
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!payments.isEmpty()) {
            LocalDate start = startDate;
            LocalDate end = startDate.with(TemporalAdjusters.lastDayOfYear());
            // setting end date to the end date of the current year provided by
            // TemporalAdjusters.lastDayOfYear() -takes into account leap years
            
            ArrayList<Payment> processedPayments = new ArrayList<>();

            // Calculate yearly amount
            for (Payment payment : payments) {
                if (inTimePeriod(LocalDate.parse(payment.getPaymentDate(), formatter), start, end)) {
                    amount += payment.getAmount();
                    processedPayments.add(payment);
                }
            }
            
            if (amount > 0){
                YearlySales yearlySales = new YearlySales(start, end, amount);
                sales.add(yearlySales);
                amount = 0.0; 
            }
            
            payments.removeAll(processedPayments);
            startDate = startDate.plusYears(1);
            // adding one year to the current startDate to calculate amount for next year
        }
        return sales;
    }
    
    
    @Override
    public double calculateAverage(ArrayList<? extends SalesRecord> sales) {
        int count = 0;
        double sum = 0;
        double average;
        
        for (Object obj : sales) {
            if (obj instanceof YearlySales yearlySale) {
                sum += yearlySale.getAmount(); 
                count++;
            }
        }
        
        average = sum/count;
        return average;
    }
    
    
    @Override
     public YearlySales highestSales(ArrayList<? extends SalesRecord> sales) {
        YearlySales highestSale = null;

        for (Object obj : sales) {
            if (obj instanceof YearlySales currentSale) {
                if (highestSale == null || currentSale.getAmount() > highestSale.getAmount()) {
                    highestSale = currentSale;
                }
            }
        }

        return highestSale;
     }
     
     @Override
     public YearlySales lowestSales(ArrayList<? extends SalesRecord> sales) {
        YearlySales lowestSale = null;

        for (Object obj : sales) {
            if (obj instanceof YearlySales currentSale) {
                if (lowestSale == null || currentSale.getAmount() < lowestSale.getAmount()) {
                    lowestSale = currentSale;
                }
            }
        }

        return lowestSale;
     }
}
