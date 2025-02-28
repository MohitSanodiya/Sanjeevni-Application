/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author HP
 */
public class DateTimeDemo {
   
    public static void main(String[] args) {
       LocalDateTime today=LocalDateTime.now();
       System.out.println(today);
       DateTimeFormatter obj=DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm:ss a");
       String str=today.format(obj);
        System.out.println(str); 
        
    }
    
}