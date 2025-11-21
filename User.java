/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author user
 */
public abstract class User {

    String id;
    String name;
    String address; 
    String cnum;
    String username;    
    String password;
    String type;

    // Constructors
    public User(String name, String address, String cnum, String username, String password) {
        this.name = name;
        this.address = address;
        this.cnum = cnum;
        this.username = username;
        this.password = password;
    }

    public User(String id, String name, String address, String cnum, String username, String password, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cnum = cnum;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    // Method to populate fields
    public void populateUserFields(String[] fields) {
        if (fields.length >= 7) { // Ensure all required fields are present
            this.id = fields[0];
            this.name = fields[1];
            this.address = fields[2];
            this.cnum = fields[3];
            this.username = fields[4];
            this.password = fields[5];
            this.type = fields[6];
        }
    }

    // ID creation methods
    public static String createCustomerID() {
        String prefix = "C"; // ID prefix for Customer
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000); // Generate a random 5-digit number
        return prefix + randomNumber;
    }

    public static String createSchedulerID() {
        String prefix = "S"; // ID prefix for Scheduler
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000); // Generate a random 5-digit number
        return prefix + randomNumber;
    }

    // Validation methods
    public static boolean validatePhoneNum(String phoneNum) {
        if (phoneNum.length() > 10) {
            return false;
        }
        for (char c : phoneNum.toCharArray()) {
            if (Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isValidNumber(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean validatePassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }

        boolean hasDigit = false;
        boolean hasLetter = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }

        if (hasDigit && hasLetter) {
            return true;
        }

        System.out.println("Password must contain both digits and letters");
        return false;
    }

    public static boolean nameValidation(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                return true; // Return true if a digit is found
            }
        }
        return false; // Return false if no digits are found
    }

    // Abstract toString method

    
    public String saveotherDetails(){
        return null;
    }

    
    public String toString() {
        return id + ";" + name + ";" + address + ";" + cnum + ";" + username + ";" + password + ";" + type;
    }


    public User getUserById(String id, FileOperations fileOps, String userFileName, String schedulerFileName) {
        ArrayList<Casting> userRecords = fileOps.searchRecord(userFileName, id, 0);

        if (userRecords != null && !userRecords.isEmpty()) {
            Casting userRecord = userRecords.get(0);
            String[] fields = userRecord.getFields();

            if (fields.length >= 7) { // Ensure all required fields are present
                populateUserFields(fields);

                if ("scheduler".equals(this.type)) {
                    ArrayList<Casting> schedulerRecords = fileOps.searchRecord(schedulerFileName, id, 0);
                    double salary = 0;

                    if (schedulerRecords != null && !schedulerRecords.isEmpty()) {
                        Casting schedulerRecord = schedulerRecords.get(0);
                        String[] schedulerFields = schedulerRecord.getFields();

                        if (schedulerFields.length >= 2) {
                            try {
                                salary = Double.parseDouble(schedulerFields[1]);
                            } catch (NumberFormatException e) {
                                System.out.println("Error parsing salary: " + e.getMessage());
                            }
                        }
                    }
                    return new Scheduler(this.id, this.name, this.address, this.cnum, this.username, this.password, this.type, salary);
                } else if ("customer".equals(this.type)) {
                    //return new Customer(this.id, this.name, this.address, this.cnum, this.username, this.password, this.type);
                }
            }
        }

        return null; // User not found
    }


    

}
