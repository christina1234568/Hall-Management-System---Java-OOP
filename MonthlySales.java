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
public class MonthlySales extends SalesRecord{

    public MonthlySales(LocalDate startDate, LocalDate endDate, double amount) {
        super(startDate, endDate, amount);
    }

    public MonthlySales() {
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
    public ArrayList<MonthlySales> calculateAmounts(ArrayList<Payment> payments) {
        ArrayList<MonthlySales> sales = new ArrayList<>();
        double amount = 0.0;
        
        LocalDate startDate = LocalDate.of(2020, 1, 1);  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!payments.isEmpty()) {
            LocalDate start = startDate;
            LocalDate end = startDate.withDayOfMonth(startDate.lengthOfMonth());
            // setting end date to the end date of the current month provided by
            // startDate.lengthOfMonth() -takes into account leap years
  
            ArrayList<Payment> processedPayments = new ArrayList<>();
            
            // Calculate monthly amount
            for (Payment payment : payments) {
                if (inTimePeriod(LocalDate.parse(payment.getPaymentDate(), formatter), start, end)) {
                    amount += payment.getAmount();
                    processedPayments.add(payment);
                }
            }
            
            if (amount > 0){
                MonthlySales monthlySales = new MonthlySales(start, end, amount);
                sales.add(monthlySales);
                amount = 0.0; 
            }
            
            payments.removeAll(processedPayments);
            startDate = startDate.plusMonths(1);
            // adding one month to the startDate to calculate amount for next month
        }
        return sales;
    }
    
    
    @Override
    public double calculateAverage(ArrayList<? extends SalesRecord> sales) {
        int count = 0;
        double sum = 0;
        double average;
        
        for (Object obj : sales) {
            if (obj instanceof MonthlySales monthlySale) {
                sum += monthlySale.getAmount(); 
                count++;
            }
        }
        
        average = sum/count;
        return average;
    }
    
    @Override
     public MonthlySales highestSales(ArrayList<? extends SalesRecord> sales) {
        MonthlySales highestSale = null;

        for (Object obj : sales) {
            if (obj instanceof MonthlySales currentSale) {
                if (highestSale == null || currentSale.getAmount() > highestSale.getAmount()) {
                    highestSale = currentSale;
                }
            }
        }

        return highestSale;
     }
     
     @Override
     public MonthlySales lowestSales(ArrayList<? extends SalesRecord> sales) {
        MonthlySales lowestSale = null;

        for (Object obj : sales) {
            if (obj instanceof MonthlySales currentSale) {
                if (lowestSale == null || currentSale.getAmount() < lowestSale.getAmount()) {
                    lowestSale = currentSale;
                }
            }
        }

        return lowestSale;
     }
}
