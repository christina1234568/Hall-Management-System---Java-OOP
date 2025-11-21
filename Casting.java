/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class Casting {

    public Casting() {
    }
    
    private String[] fields;

        public Casting(String[] fields) {
            this.fields = fields;
        }

        public String[] getFields() {
            return fields;
        }

        public void setFields(String[] fields) {
            this.fields = fields;
        }

        @Override
        public String toString() {
            return String.join(";", fields);
        }
    
        
        // Method to convert records to objects
    public ArrayList<Object> convertRecordsToObjects(ArrayList<Casting> records, String type) {
        ArrayList<Object> objects = new ArrayList<>();
        FileOperations fileOps = new FileOperations();
        List<String> invalidRecords = new ArrayList<>();

        for (Casting record : records) {
            String[] fields = record.getFields();

            try {
                switch (type) {
                    case "Scheduler":
                    if (fields.length >= 2) {
                        String id = fields[0];
                        double salary = 0.0;

                        try {
                            salary = Double.parseDouble(fields[1]);
                            
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid salary format: " + fields[1]);
                            
                        }

                        // Fetch additional user details
                        ArrayList<Casting> userFields = fileOps.searchRecord("user.txt", id, 0);
                        if (userFields != null && !userFields.isEmpty()) {
                            Casting userRecord = userFields.get(0);
                            String[] userFieldsArray = userRecord.getFields();
                            if (userFieldsArray.length >= 7) {
                                String name = userFieldsArray[1];
                                String address = userFieldsArray[2];
                                String cnum = userFieldsArray[3];
                                String username = userFieldsArray[4];
                                String password = userFieldsArray[5];
                                String usertype = userFieldsArray[6];
                                
                                
                                Scheduler scheduler = new Scheduler(id, name, address, cnum, username, password, usertype, salary);
                                
                                
                                objects.add(scheduler);
                                } else {
                                    System.out.println("Invalid user record format.");
                                }
                            } else {
                                System.out.println("No user record found for ID: " + id);
                            }   
                        } else {
                            System.out.println("Scheduler record does not have enough fields.");
                        }
                        break;

                    case "Payment":
                        if (fields.length >= 5) {
                            try {
                                String date = fields[3];
                                double amount = Double.parseDouble(fields[2]); // Ensure amount field is parsed correctly
                                Payment payment = new Payment(date, amount);
                                objects.add(payment);
                            } catch (NumberFormatException e) {
                                addInvalidRecord("Payment", "", "Invalid amount format with value: " + fields[2]);
                            }
                        } else {
                            addInvalidRecord("Payment", "", "Not enough fields in the payment record.");
                        }
                        break;

                    case "CustomerIssue":
                        if (fields.length >= 8) {
                            try {
                                String issueID = fields[0];
                                String issueDescription = fields[1];
                                String status = fields[2];
                                String staffID = fields[3];
                                String staffName = fields[4];
                                String response = fields[5];
                                String bookingID = fields[6];
                                String customerID = fields[7];
                                CustomerIssue customerIssue = new CustomerIssue(issueID, issueDescription, status, staffID, staffName, response, bookingID, customerID);
                                objects.add(customerIssue);
                            } catch (Exception e) {
                                addInvalidRecord("CustomerIssue", "", "Unable to create CustomerIssue object.");
                            }
                        } else {
                            addInvalidRecord("CustomerIssue", "", "Not enough fields in the customer issue record.");
                        }
                        break;
                    
                    case "MarketingProject":
                        if (fields.length == 8){
                            try{
                                String projectID = fields[0];
                                String projectName = fields[1];
                                String description = fields[2];
                                String startDate = fields[3];
                                String endDate = fields[4];
                                double budget = Double.parseDouble(fields[5]);

                                ArrayList<Scheduler> schedulerList = new ArrayList<>();
                                String[] schedulerArray = fields[6].split(","); 

                                for (String schedulerString : schedulerArray) {
                                    String[] schedulerParts = schedulerString.split(":");
                                    if (schedulerParts.length == 2) {
                                        String schedulerID = schedulerParts[0];
                                        String schedulerName = schedulerParts[1];
                                        Scheduler scheduler = new Scheduler(schedulerID, schedulerName); 
                                        schedulerList.add(scheduler);
                                    } else {
                                        addInvalidRecord("Scheduler", schedulerString, "Malformed scheduler data.");
                                    }
                                }

                                String targetAudience = fields[7];

                                MarketingProject marketingProject = new MarketingProject(projectID, projectName, description, startDate, endDate, budget, schedulerList, targetAudience);
                                objects.add(marketingProject);
                                
                            }catch(Exception e) {
                                addInvalidRecord("MarketingProject", "", "Unable to create MarketingProject object.");
                            }
                        }
                        break;
                    // return projectID + ";" + projectName + ";" + description + ";" + startDate + ";" + endDate + ";" + budget + ";" + schedulerString + ";" + constructionSite + ";" + contractor;
                    case "ConstructionProject":
                        if (fields.length == 9){
                            try{
                                String projectID = fields[0];
                                String projectName = fields[1];
                                String description = fields[2];
                                String startDate = fields[3];
                                String endDate = fields[4];
                                double budget = Double.parseDouble(fields[5]);

                                ArrayList<Scheduler> schedulerList = new ArrayList<>();
                                String[] schedulerArray = fields[6].split(","); 

                                for (String schedulerString : schedulerArray) {
                                    String[] schedulerParts = schedulerString.split(":");
                                    if (schedulerParts.length == 2) {
                                        String schedulerID = schedulerParts[0];
                                        String schedulerName = schedulerParts[1];
                                        Scheduler scheduler = new Scheduler(schedulerID, schedulerName); 
                                        schedulerList.add(scheduler);
                                    } else {
                                        addInvalidRecord("Scheduler", schedulerString, "Malformed scheduler data.");
                                    }
                                }

                                String constructionSite = fields[7];
                                String contractor = fields[8];

                                ConstructionProject constructionProject = new ConstructionProject(projectID, projectName, description, startDate, endDate, budget, schedulerList, constructionSite, contractor);
                                objects.add(constructionProject);
                                
                            }catch(Exception e) {
                                addInvalidRecord("MarketingProject", "", "Unable to create MarketingProject object.");
                            }
                        }
                        break;

                    default:
                        addInvalidRecord("Unknown", "", "Unknown type: " + type);
                }
            } catch (Exception e) {
                addInvalidRecord("Unknown", "", "Error processing record: " + record);
            }
        }

        // Log invalid records
        logInvalidRecords(invalidRecords);

        return objects;
    }

    private void addInvalidRecord(String type, String id, String errorMessage) {
        // Add invalid record details to a list or file
        System.out.println("Invalid " + type + " record with ID: " + id + " - " + errorMessage);
    }

    private void logInvalidRecords(List<String> invalidRecords) {
        // Implement logging of invalid records
        System.out.println("Logging invalid records:");
        for (String record : invalidRecords) {
            System.out.println(record);
        }

    }
        
    
    
}




