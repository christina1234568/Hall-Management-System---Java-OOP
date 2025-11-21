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
public class Customer extends User{
     /*
    private String name;
    private String address;
    private String phoneNum;
    private String username;
    private String password;
    private String customerID;
    */
    
    
    /*public Customer(){
        
    }*/
    
    /*
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    */
    
    public Customer(String name, String username, String cnum, String address, String password){
        super(name, address, cnum, username, password);
        id = createCustomerID();
        type = "customer";
    }
    
    public Customer(String id, String name, String address, String cnum, String username, String password, String type){
        super(id, name, address, cnum, username, password, type);
    }
    /*
    public Customer(String name, String address, String phoneNum, String username, String password) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.customerID = generateUniqueCID();
    }
    */
    
    /*
    public Customer(String ID,String name, String address, String phoneNum, String username, String password) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.customerID = ID;
    }
    */

    /*
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomerID() {
        return customerID;
    }
    
    
    public static boolean validatePhoneNum(String A){
        if (A.length() > 10){
            //System.out.println("Phone Number cannot exceed 10 digits");
            return false;
        }
        
        for (char i : A.toCharArray()) {
            if (Character.isLetter(i)){
                //System.out.println("Phone number cannot have alphabets");
                return false;
            }
        }
        return true;
    }
    
    
    public static boolean validateName(String inputname){
        for (char i : inputname.toCharArray()){
            if (Character.isDigit(i)){
                return false;
            }
        }
        return true;
    }
    
    
    public String generateUniqueCID(){
        String newID;
        do {
            newID = "C" + String.format("%05d", (int)(Math.random() * 100000));
        } while (!isCIDUnique(newID));
        return newID;
    }
    
    public boolean isCIDUnique(String x){
        try {
            File f = new File("customer.txt");
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
    */
    
    /*
    public String toString(){
        return (customerID + ";" + name + ";" + address + ";" + phoneNum + ";" + username + ";" + password);
    }
    */
    
    @Override
    public String toString(){
        return id + ";" + name + ";" + address + ";" + cnum + ";" + username + ";" + password + ";" + "customer";
    }
    /*
    public static boolean validatePassword(String x){
        if (x.length() < 7) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }
        
        boolean hasDigit = false;
        boolean hasLetter = false;
        
        for (char i : x.toCharArray()){
            if (Character.isDigit(i)){
                hasDigit = true;
            }
            if (Character.isLetter(i)){
                hasLetter = true;
            }
        }
        
        if (hasDigit && hasLetter){
            return true;
        }
        
        System.out.println("Password must contain both digits and alphabets");
        return false;
    }
    */

    /*private String name;
    private String address;
    private String phoneNum;
    private String username;
    private String password;
    private String customerID;
    
    public Customer(){
        
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    public Customer(String name, String address, String phoneNum, String username, String password) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.customerID = generateUniqueCID();
    }
    
    public Customer(String ID,String name, String address, String phoneNum, String username, String password) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.customerID = ID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomerID() {
        return customerID;
    }
    
    
    public static boolean validatePhoneNum(String A){
        if (A.length() > 10){
            //System.out.println("Phone Number cannot exceed 10 digits");
            return false;
        }
        
        for (char i : A.toCharArray()) {
            if (Character.isLetter(i)){
                //System.out.println("Phone number cannot have alphabets");
                return false;
            }
        }
        return true;
    }
    
    /*
    public static boolean validateName(String inputname){
        for (char i : inputname.toCharArray()){
            if (Character.isDigit(i)){
                return false;
            }
        }
        return true;
    }
    
    
    public String generateUniqueCID(){
        String newID;
        do {
            newID = "C" + String.format("%05d", (int)(Math.random() * 100000));
        } while (!isCIDUnique(newID));
        return newID;
    }
    
    public boolean isCIDUnique(String x){
        try {
            File f = new File("customer.txt");
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
    
    
    public String customerDetails(){
        return (customerID + ";" + name + ";" + address + ";" + phoneNum + ";" + username + ";" + password);
    }
    
    public static boolean validatePassword(String x){
        if (x.length() < 7) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }
        
        boolean hasDigit = false;
        boolean hasLetter = false;
        
        for (char i : x.toCharArray()){
            if (Character.isDigit(i)){
                hasDigit = true;
            }
            if (Character.isLetter(i)){
                hasLetter = true;
            }
        }
        
        if (hasDigit && hasLetter){
            return true;
        }
        
        System.out.println("Password must contain both digits and alphabets");
        return false;
    }*/
}
