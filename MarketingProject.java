/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;


import java.util.ArrayList;


public class MarketingProject extends Project{
    private String targetAudience;

    public MarketingProject(String projectID, String projectName, String description, String startDate, String endDate, double budget, ArrayList<Scheduler> schedulers, String targetAudience) {
        super(projectID, projectName, description, startDate, endDate, budget, schedulers);
        this.targetAudience = targetAudience;
    }

    public String getTargetAudience() {
        return targetAudience;
    }
    
    
     
    @Override
    public String toString(){
        StringBuilder schedulerString = new StringBuilder(); // used for building and chnaging strings rapidly without creation of new objects
        for (int i = 0; i < schedulers.size(); i++) { //looping through scheduler objects in the schedulers ArrayList
            schedulerString.append(schedulers.get(i).toStringForProject()); // Use toStringForProject for specific format
            if (i < schedulers.size() - 1) { // check is it is the last object, to not put a ", " after the last object
                schedulerString.append(","); // Separating each schedulerstring with a comma to separate the scheduler objects
            }
        }

        // correctly formatted string to be used to save and read marketing projects
        return projectID + ";" + projectName + ";" + description + ";" + startDate + ";" + endDate + ";" + budget + ";" + schedulerString + ";" + targetAudience;
    }

}
