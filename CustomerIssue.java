/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author user
 */
public class CustomerIssue {
     String issueID;
    String issueDescription;
    String status;
    String staffID;
    String staffName;
    String response;
    String bookingID;
    String customerID;
    private Customer customer;

    public String getCustomerID() {
        return customerID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getIssueID() {
        return issueID;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getStaffName() {
        return staffName;
    }
    
    
    
    public CustomerIssue(String issueDescription, String bookingID, String customerID,String D, String name, String address, String phoneNum, String username, String password){
        issueID = generateUniqueIssueID();
        this.issueDescription = issueDescription;
        status = "Open";
        staffID ="-";
        staffName = "-";
        response = "-";
        this.bookingID = bookingID;
        this.customerID = customerID;
        customer = new Customer(D, name, address, phoneNum, username, password, "customer");//composition
    }
    
    public CustomerIssue(String issueID, String issueDescription, String status, String staffID, String staffName, String response, String bookingID, String customerID){
       this.issueID = issueID;
       this.issueDescription = issueDescription;
       this.status = status;
       this.staffName = staffName;
       this.staffID = staffID;
       this.response = response;
       this.bookingID = bookingID;
       //customer = new Customer();
       //customer.setCustomerID(customerID);
       this.customerID = customerID;
    }
    
    
    public String generateUniqueIssueID(){
        String newID;
        do {
            newID = "I" + String.format("%05d", (int)(Math.random() * 100000));
        } while (!isIssueIDUnique(newID));
        return newID;
    }
    
    public boolean isIssueIDUnique(String x){
        try {
            File f = new File("Issue.txt");
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
    
    public String toString(){
        return (issueID + ";" + issueDescription + ";" + status + ";" + staffID + ";" + staffName + ";" + response + ";" + bookingID + ";" + customerID);
    }

    /*String issueID;
    String issueDescription;
    String status;
    String staffID;
    String staffName;
    String response;
    String bookingID;
    String customerID;
    private Customer customer;

    public String getCustomerID() {
        return customerID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public CustomerIssue() {
    }

    public String getIssueID() {
        return issueID;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    
    
    
    
    public CustomerIssue(String issueDescription, String bookingID, String customerID,String D, String name, String address, String phoneNum, String username, String password){
        issueID = generateUniqueIssueID();
        this.issueDescription = issueDescription;
        status = "Open";
        staffID ="-";
        staffName = "-";
        response = "-";
        this.bookingID = bookingID;
        this.customerID = customerID;
        customer = new Customer(D, name, address, phoneNum, username, password, "Customer");//composition
    }
    
    public CustomerIssue(String issueID, String issueDescription, String status, String staffID, String staffName, String response, String bookingID, String customerID){
       this.issueID = issueID;
       this.issueDescription = issueDescription;
       this.status = status;
       this.staffName = staffName;
       this.staffID = staffID;
       this.response = response;
       this.bookingID = bookingID;
       //customer = new Customer();
       //customer.setCustomerID(customerID);
       this.customerID = customerID;
    }
    
    
    public String generateUniqueIssueID(){
        String newID;
        do {
            newID = "I" + String.format("%05d", (int)(Math.random() * 100000));
        } while (!isIssueIDUnique(newID));
        return newID;
    }
    
    public boolean isIssueIDUnique(String x){
        try {
            File f = new File("Issue.txt");
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
    
    public String createIssue(){
        return (issueID + ";" + issueDescription + ";" + status + ";" + staffID + ";" + staffName + ";" + response + ";" + bookingID + ";" + customerID);
    }*/
}
/*public class CustomerIssue {
    String issueID;
    String issueDescription;
    String status;
    String staffID;
    String staffName;
    String response;
    String bookingID;
    String customerID;
    private Customer customer;
    
    public CustomerIssue(String A, String B, String C, String D, String E, String F, String G, String H, String I){
        issueID = generateUniqueIssueID();
        issueDescription = A;
        status = "Open";
        staffID ="-";
        staffName = "-";
        response = "-";
        bookingID = B;
        customerID = C;
        //customer = new Customer(D, E, F, G, H, I);//composition
        customer = new Customer();//composition
    }

    public CustomerIssue() {
    }
    
    
    
    public String generateUniqueIssueID(){
        return "";
    }
}*/
