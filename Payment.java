/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author user
 */

public class Payment {
    protected String paymentID;
    protected String paymentDate;
    protected Booking booking;
    protected double amount;
    protected String paymentType;

    public String getPaymentID() {
        return paymentID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Payment(String paymentDate, double amount) {
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

         
    public Payment(Booking booking, double amount){
        this.booking = booking;
        this.amount = amount;
        paymentID = generateUniquePID();
        paymentDate = LocalDate.now().toString();
    }
    
    
    public String generateUniquePID(){
        String newID;
        do {
            newID = "P" + String.format("%05d", (int)(Math.random() * 100000));
        } while (!isPIDUnique(newID));
        return newID;
    }
    
    public boolean isPIDUnique(String x){
        try {
            File f = new File("Payment.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null){
                if (line.startsWith(x)){
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public String makePayment(){
        return "";
    };
}




