/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author user
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

    

/*public class File_Operations {
    private String filename;
    private String line; 
    private String identity;
    private int position;
    private int record;
    
    public File_Operations(String filename){
        this.filename = filename;
    }
    
    public File_Operations(String filename, String line, String identity, int position, int record) {
        this.filename = filename;
        this.line = line;
        this.identity = identity;
        this.position = position;
        this.record = record;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
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
    
    public ArrayList<String> searchRecord(String fileName, String ID, int position) {
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
    }

    
    public String modify(String fileName, int position, String oldValue, String newValue) {
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
    } catch (IOException e) {
        status = e.getMessage();
    }
    
    return status;
    }
    
    public class Record {
        private String[] fields;

        public Record(String[] fields) {
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
    }
    
    public ArrayList<Record> readFile() {
        ArrayList<Record> records = new ArrayList<>();
        
        try (BufferedReader bReader = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(";");
                Record record = new Record(parts);
                records.add(record);
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e.getMessage());
        }
        
        return records;
    }
    
    public ArrayList<Object> convertRecordsToObjects(ArrayList<Record> records, String type) {
    ArrayList<Object> objects = new ArrayList<>();
    
    for (Record record : records) {
        String[] fields = record.getFields();
        
        switch (type) {
            case "Scheduler":
                if (fields.length >= 5) {
                    String id = fields[0];
                    String name = fields[1];
                    int cnum = Integer.parseInt(fields[2]);
                    String address = fields[3];
                    double salary = Double.parseDouble(fields[4]);
                    Scheduler scheduler = new Scheduler(name, id, cnum, address, salary);
                    objects.add(scheduler);
                }
                break;
               
            default:
                System.out.println("Unknown type: " + type);
        }
    }
    
    return objects;
    }
    
    // General method to write objects to a file
    public <placeholder> String writeToFile(String fileName, ArrayList<placeholder> objects) {
        String status = "";
        File file = new File(fileName);

        try {
            // Create the file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter print = new PrintWriter(new FileWriter(file, true))) { // Append mode
                for (placeholder obj : objects) {
                    if (obj instanceof Scheduler) {
                        Scheduler scheduler = (Scheduler) obj;
                        print.println(scheduler.toString());
                    }
                    
                    else {
                        status = "Unknown object type: " + obj.getClass().getSimpleName();
                        return status;
                    }
                }
                status = "Records successfully written to file.";
            }
        } catch (IOException e) {
            status = "Error encountered: " + e.getMessage();
        }

        return status;
    }
}*/

