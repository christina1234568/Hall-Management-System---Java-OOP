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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;


public class Project {
    protected String projectID;
    protected String projectName;
    protected String description;
    protected String startDate;
    protected String endDate;
    protected double budget;
    protected ArrayList<Scheduler> schedulers;
    //aggregation
    //project needs staffs but can exist without staff - staff can be rmeoved or reassigned
    //if the staff leaves the comapny - can just reassign staff - project will not be deleted
    //staff can exist even if project is deleted - so aggregation

    public Project(String projectID, String projectName, String description, String startDate, String endDate, double budget, ArrayList<Scheduler> schedulers) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.schedulers = schedulers;
    }

    

    public ArrayList<Scheduler> getSchedulers() {
        return schedulers;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getBudget() {
        return budget;
    }
    
    
    
    //all following methods are static as they do not depend or need the instance of this class to operate
    
    //static method to check if the generated iD is unique, that is not present in the text file
    public static boolean checkUniqueID(String ID, String fileName){
        File checkFile = new File(fileName);
        if (!checkFile.exists()) { //checking if file does not exist - if does not exist, no project contains the current project ID
            return true;
        }
        try (BufferedReader buffer = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = buffer.readLine()) != null) {//looping through lines in the text file
                if (line.startsWith(ID)) { // checking if the lines starts with the ID - ID generated is not unique - returning false
                    return false; 
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true; 
    }
    
    // method to generate ID
    // based on type of project - prefix will change
    public static String generateID(String type){
        UUID uuID = UUID.randomUUID();
        String newID = uuID.toString().replace("-", "").substring(0, 8);
        if ("MarketingProject".equals(type)){
            return "PM" + newID;
        }
        else if("ConstructionProject".equals(type)){
            return "PC" + newID;
        }
        else{ // for other types projects that may be implemented later 
            return "P" + newID;
        }
    }
    
    // method that combines generateID and checkUniqueID to create unique IDs when creating new projects
    public static String generateUniqueProjectID(String fileName, String type) {
        String newID;
        do {
            newID = generateID(type);
        } while (!checkUniqueID(newID, fileName));
        return newID;
    }

    // used to validate the date input of a user 
    // check if it is in the Localdate format required
    public static boolean validDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);  
            return true;  
        } catch (DateTimeParseException e) {
            return false;  
        }
    }
    
    // check if the date entered is in the future, that is not today and not in the past
    public static boolean validFutureDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate dateEntered = LocalDate.parse(date, formatter); // parsing teh date entered to LocalDate to correct format using formatter to be used for validation
            LocalDate dateToday = LocalDate.now(); // finding the day on which the user is creating the project
            return dateEntered.isAfter(dateToday); // isAfter() returns boolean value true if dateToday is after dateEntered and false if not   
        }
        catch (DateTimeParseException e) {
            return false;  
        }
        
    }
    

    // check if the end date entered is after the start date entered
    public static boolean endDateAfterStart(String endDate, String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate endDateEntered = LocalDate.parse(endDate, formatter);
            LocalDate startDateEntered = LocalDate.parse(startDate, formatter);
            return endDateEntered.isAfter(startDateEntered); 
            // isAfter() returns boolean value true if endDateEntered is after startDateEntered and false if not 
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // method to validate budget 
    public static boolean validBudget(String budget){
        try {
            double amount = Double.parseDouble(budget); // try to parse the budget input by user into a double amount
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // method to load scheduler objects from scheduler.txt
    public static ArrayList<Scheduler> schedulerLoader(){
        ArrayList<Scheduler> schedulers = new ArrayList();
        File checkFile = new File("scheduler.txt");
        if (!checkFile.exists()) { //checking if the file exist first
            JOptionPane.showMessageDialog(null, "No scheduler has been added yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            FileOperations file = new FileOperations();
            ArrayList<Casting> schedulerRecords = file.readFile("scheduler.txt");
            if (!schedulerRecords.isEmpty()){ // check if ArrayList is empty
                Casting cast = new Casting();
                ArrayList<Object> schedulerObjects = cast.convertRecordsToObjects(schedulerRecords, "Scheduler");

                // adding the scheduler objects to the schedulers ArrayList
                for (Object obj : schedulerObjects) {
                    if (obj instanceof Scheduler scheduler) {
                        schedulers.add(scheduler);
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Unexpected object type: " + obj.getClass().getName(), "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No scheduler has been added yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return schedulers;
    }
    
    
}
