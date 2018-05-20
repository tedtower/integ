/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transientreservation.implementation;


import transientreservation.interfaces.ReservationInterface;
import transientreservation.constructors.Room;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import transientreservation.constructors.Room;
/**
 *
 * @author HP
 */
public class Reservation implements ReservationInterface{
    private Connection con;
    
    public Reservation(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transient_house","root","");
        }catch(SQLException ex){
            System.out.println("Encountered a problem connection to the database!");
            System.exit(0);
        }
    }

    @Override
    public String viewTransInfo() {
        return "";
    }

    @Override
    public void checkin(int roomNo, short month, short day, String time) throws RemoteException {
        
    }

    @Override
    public void checkout(int roomNo, short month, short day, String time) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewVacant() throws RemoteException{
        try {
            Statement stmt = con.createStatement();
            String query = "select * from room where room_status='vacant'";
            ResultSet result = stmt.executeQuery(query);
            
            if(result == null){
                System.out.println("There are currently no rooms available!");
            }else{
                System.out.println("|-----------------------------|");
                System.out.println("| Room No | Capacity |  Price  |");
                while(result.next()){
                    System.out.printf("|    %-2s   |    %-2s    |  %-6f |", result.getInt("room_no"), result.getInt("capacity"), result.getDouble("price"));
                    System.out.println("|-----------------------------|");
                }
            }
            
        } catch (SQLException ex) {
        }
    }
    
    public void viewOccupiedDays(){
        Statement stmt = con.createStatement();
    }

    @Override
    public void viewOccupied() throws RemoteException {
        try {
            Statement stmt = con.createStatement();
            String query = "select * from room where room_status='occupied'";
            ResultSet result = stmt.executeQuery(query);
            
            if(result == null){
                System.out.println("All rooms are vacant! :-)");
            }else{
                System.out.println("-------------------------------");
                System.out.println("| Room No | Capacity |  Price  |");
                while(result.next()){
                    System.out.printf("|    %-2s   |    %-2s    |  %-6f |", result.getInt("room_no"), result.getInt("capacity"), result.getDouble("price"));
                    System.out.println("|-----------------------------|");
                }
            }
        } catch (SQLException ex) {
            
        }
    }

    
}
