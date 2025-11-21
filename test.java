/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class test {

    /**
     * @param args the command line arguments
     */
    
    private static void testReadFile(FileOperations fileOps) {
        String filename = "scheduler.txt"; // Ensure this file is correctly formatted and placed
        ArrayList<Casting> records = fileOps.readFile(filename);

        Casting casting = new Casting(new String[0]); // Initialize with dummy data
        ArrayList<Object> schedulers = casting.convertRecordsToObjects(records, "Scheduler");

        System.out.println("Testing readFile Method:");
        for (Object obj : schedulers) {
            if (obj instanceof Scheduler) {
                Scheduler scheduler = (Scheduler) obj;
                System.out.println(scheduler);
            } else {
                System.out.println("Unexpected object type: " + obj.getClass().getName());
            }
        }
    }


    private static void testSearchRecord(FileOperations fileOps) {
        String filename = "scheduler.txt"; // The file where records are stored
        String idToSearch = "S38333"; // ID of the specific record you want to retrieve
        int position = 0; // Assuming ID is in the first position

        ArrayList<Casting> records = fileOps.searchRecord(filename, idToSearch, position);

        if (records != null && !records.isEmpty()) {
            Casting casting = new Casting(new String[0]); // Dummy instance for method call
            ArrayList<Object> objects = casting.convertRecordsToObjects(records, "Scheduler");
            System.out.println("Objects: "+objects);

            System.out.println("Testing searchRecord Method:");
            for (Object obj : objects) {
                if (obj instanceof Scheduler) {
                    Scheduler scheduler = (Scheduler) obj;
                    System.out.println(scheduler);
                } else {
                    System.out.println("Unexpected object type: " + obj.getClass().getName());
                }
            }
        } else {
            System.out.println("No records found for ID: " + idToSearch);
        }
    }

    
    
    public static void main(String[] args) {
         FileOperations fileOps = new FileOperations();

        // Test readFile method
        testReadFile(fileOps);

        // Test searchRecord method
        testSearchRecord(fileOps);

    }
    
}
