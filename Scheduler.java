/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author user
 */
public class Scheduler extends User{
    double salary;

    // Constructor with full details
    public Scheduler(String id, String name, String address, String cnum, String username, String password, String type, double salary) {
        super(id, name, address, cnum, username, password, type);
        this.salary = salary;

    }
    
    public Scheduler(String id, String name, String address, String cnum, String username, String password, String type) {
        super(id, name, address, cnum, username, password, type);
        
    }
    
    // Constructor with ID and salary only (useful for reading from scheduler.txt)
    public Scheduler(String id, double salary) {
        super(id, null, null, null, null, null, "scheduler");
        this.salary = salary;
        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Scheduler other = (Scheduler) obj;
        return id.equals(other.id);  // Compare based on staffID
    }

    @Override
    public int hashCode() {
        return id.hashCode();  // Use staffID's hash code
    }
    

    public Scheduler(String id, String name) {
        super(id, name);
    }
    

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    
    @Override
    public String toString() {
        return id + ";" + name + ";" + address + ";" + cnum + ";" + username + ";" + password + ";" + type + ";" + salary;
    }
    
    public String Scheudlerdetailas(){
        return id + ";" + name + ";" + address + ";" + cnum + ";" + username + ";" + password + ";" + type;
    }
    
    public String SchedulertxtDetails() {
        // For scheduler.txt: return only id and salary
        return id + ";" + salary;
    }
    
    public String toStringForProject() {
        return id + ":" + name; 
    }


    
    
    

}
