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
    
    public Customer(String name, String username, String cnum, String address, String password){
        super(name, address, cnum, username, password);
        id = createCustomerID();
        type = "customer";
    }
    
    public Customer(String id, String name, String address, String cnum, String username, String password, String type){
        super(id, name, address, cnum, username, password, type);
    }

   
    
    @Override
    public String toString(){
        return id + ";" + name + ";" + address + ";" + cnum + ";" + username + ";" + password + ";" + "customer";
    }

}

