/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.time.LocalDate;
import java.util.ArrayList;

// parent class for hierarchical ihneritance
public abstract class SalesRecord {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected double amount;

    // parameterized constructor to create objects
    public SalesRecord(LocalDate startDate, LocalDate endDate, double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }
    
    //overloading - static polymorphism
    public SalesRecord(){
        
    }  

    //abstract method that will be overriden to fit the sub classes
    //method will be used to calculate the amounts, end dates and start dates of the types of sales
    //ArrayList<? extends SalesRecord> - the ? acts a wildcard, which allows the subtitution 
    //of an object of any subclass of the SalesRecord class
    public abstract ArrayList<? extends SalesRecord> calculateAmounts(ArrayList<Payment> payments);
    
    //concrete method that will be used to validate the time period in each class
    protected boolean inTimePeriod(LocalDate date, LocalDate start, LocalDate end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }
    
    //abstract method that will be overriden to fit the sub classes
    // calculates the average of the types of sales - weekly, monthly and yearly
    // when used, the parameter can be substituted by any type of object of the subclasses 
    public abstract double calculateAverage(ArrayList<? extends SalesRecord> sales);
    
    //abstract method that will be overriden to fit the sub classes
    //method to calculate the highest sales made in respective classes
    public abstract Object highestSales(ArrayList<? extends SalesRecord> sales); 
    
    //abstract method that will be overriden to fit the sub classes
    //method to calculate the lowest sales made in respective classes
    public abstract Object lowestSales(ArrayList<? extends SalesRecord> sales);
    
}

