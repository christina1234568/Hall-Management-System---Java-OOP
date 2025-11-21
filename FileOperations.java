/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 *
 * @author user
 */
public class FileOperations{
    
    public FileOperations(){
    }
    

    
    public static void clearFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error encountered while clearing the file: " + e.getMessage());
        }
    }
    
    public <T> String writeToFile(String fileName, ArrayList<T> objects) {
        String status = "";
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter print = new PrintWriter(new FileWriter(file, true))) { // Append mode
                for (T obj : objects) {
                    print.println(obj.toString());
                }
                status = "Records successfully written to file.";
            }
        } catch (IOException e) {
            status = "Error encountered: " + e.getMessage();
        }

        return status;
    }
    

    public ArrayList<Casting> readFile(String filename) {
        ArrayList<Casting> records = new ArrayList<>();
        
        try (BufferedReader bReader = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(";");
                Casting record = new Casting(parts);
                records.add(record);
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e.getMessage());
        }
        
        return records;
    }
    
    
    public String delete(String fileName, String identity, int position){
        String status = "";
        try{
            File file = new File(fileName);
            if (!file.exists()) {
                status = "Original file does not exist.";
                return status;
            }
            File tempFile = new File("TemporaryFile.txt");
            try (BufferedReader bReader = new BufferedReader(new FileReader(file));
                PrintWriter print = new PrintWriter(tempFile)){
               
                String line = bReader.readLine();
                while (line != null){
                    String[] parts = line.split(";");
                    if (parts.length > position && parts[position].equals(identity)){

                    }
                    else{
                        print.println(line);
                    }
                    line = bReader.readLine();   
               }
            }
            
            if(!file.delete()) {//deleting old file
                status = "Error encountered while deleting old file.";
                return status;
            }
            else{
                if(!tempFile.renameTo(file))//renaming new file
                {
                    status = "Error encountered while renaming new file.";
                    return status;
                }
                else{
                    status = "Succesfully deleted.";
                }
            }   
        }       
        catch(IOException e){
            status = e.getMessage();
        }
        
        return status;
    }
    
    
    public String modify(String fileName, int oldValuePosition, String oldValue, String newValue, String ID, int idPosition) {
        String status = "";

        try {
            File file = new File(fileName);

            // Check if the original file exists
            if (!file.exists()) {
                return "Original file does not exist.";
            }

            // Create FileReader and BufferedReader to read from the original file
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);

            // Create a temporary file to write the updated contents
            File tempFile = new File("TemporaryFile.txt");
            PrintWriter print = new PrintWriter(tempFile);

            String line = bReader.readLine();

            // Read lines from the original file
            while (line != null) {
                String[] parts = line.split(";");

                // Check if the current line contains the old value at the specified position
                if (parts.length > oldValuePosition && parts[oldValuePosition].equals(oldValue) && parts[idPosition].equals(ID)) {
                    // Update the value at the specified position
                    parts[oldValuePosition] = newValue;

                }

                // Join the parts array into a single line with ';' delimiter
                line = String.join(";", parts);
                // Write the (possibly updated) line to the temp file
                print.println(line);
                // Read the next line
                line = bReader.readLine();
            }

            // Close the readers
            bReader.close();
            print.close();

            // Replace the original file with the updated temp file
            if (!file.delete()) {
                status = "Error encountered while deleting old file.";
            } else if (!tempFile.renameTo(file)) {
                status = "Error encountered while renaming new file.";
            } else {
                status = "Record updated successfully.";
            }
        } catch (IOException e) {
            status = e.getMessage();
        }

        return status;
    }
    

    /*public String modify(String fileName, int position, String oldValue, String newValue) {
        String status = "";

        try {
            File file = new File(fileName);
            

            // Check if the original file exists
            if (!file.exists()) {
                return "Original file does not exist.";
            }

            // Create FileReader and BufferedReader to read from the original file
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);

            // Create a temporary file to write the updated contents
            File tempFile = new File("TemporaryFile.txt");
            PrintWriter print = new PrintWriter(tempFile);

            String line = bReader.readLine();

            // Read lines from the original file
            while (line != null) {
                String[] parts = line.split(";");

                // Check if the current line contains the old value at the specified position
                if (parts.length > position && parts[position].equals(oldValue)) {
                    // Update the value at the specified position
                    parts[position] = newValue;

                }

                // Join the parts array into a single line with ';' delimiter
                line = String.join(";", parts);
                // Write the (possibly updated) line to the temp file
                print.println(line);
                // Read the next line
                line = bReader.readLine();
            }

            // Close the readers
            bReader.close();
            print.close();

            // Replace the original file with the updated temp file
            if (!file.delete()) {
                status = "Error encountered while deleting old file.";
            } else if (!tempFile.renameTo(file)) {
                status = "Error encountered while renaming new file.";
            } else {
                status = "Record updated successfully.";
            }
        } 
        catch (IOException e) {
            status = e.getMessage();
        }
        return status;
    }*/
    
    public ArrayList<Casting> searchRecord(String fileName, String ID, int position) {
        ArrayList<Casting> matches = new ArrayList<>();

        // Try-with-resources to automatically close the BufferedReader
        try (BufferedReader bReader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(";"); // The data in the text file is separated by a semicolon
                // Checking if the current line contains the ID at the given position
                if (parts.length > position && parts[position].equals(ID)) {
                    // Assuming the Casting class has a constructor that takes a String[] or individual parameters
                    Casting casting = new Casting(parts); // You may need to adjust this based on your actual Casting class
                    matches.add(casting); // Adding the matching record to the ArrayList
                }
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e.getMessage());
        }

        return matches;
        // Can be used for validation purposes as well (just check the length of the returned ArrayList)
    }
    //pascal original
    /*public ArrayList<String> searchRecord(String fileName, String ID, int position) {
        ArrayList<String> matches = new ArrayList<>();

        // try with resources is used to automatically close the buffer reader
        try (BufferedReader bReader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(";"); // The data saved in the text file will be separated using semi colon
                // Checking if the current line contains the ID in the given position
                if (parts.length > position && parts[position].equals(ID)) {
                    matches.add(line); // Adding the lines containing the data being searched in the arraylist 
                }
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e.getMessage());
            
        }

        return matches;
        // Can be used for validation purposes as well ( Just check the length of the returnd arraylist)
    }*/
    
    
    /*public Boolean searchRecord(String fileName, String ID, int position) {
        Boolean result = false;

        // useing try with resources to automatically close the filereader and buffer readerr
        try (BufferedReader bReader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(";");
                // Check if the current line contains the ID in the given position
                if (parts.length > position && parts[position].equals(ID)) {
                    result = true;
                    break; //checking if length of array is greater than position 
                }
                else{
                     result = false;
                }
                
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e.getMessage());
            result = false;
        }
        return result;
    }*/
}
