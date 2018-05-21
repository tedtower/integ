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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class Reservation implements ReservationInterface{
    private Connection con;
    
    public Reservation() throws SQLException{
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transient_house","root","");
    }

    @Override
    public int checkin(int reservationNo) throws SQLException{
        Statement stmt = con.createStatement();
        String query = "select room_status, room_no from room where room_no='"+ reservationNo + "' ;";
        ResultSet result = stmt.executeQuery(query);
        int numOfAffectedRows = 0;
        if(!result.next()){
            System.out.println("You haven't reserved a room yet. Reserve a room first before you can perform a check-in!");
        }else{
            query = "update room set room_status='occupied' where room_no=" + result.getInt("room_no");
            numOfAffectedRows = stmt.executeUpdate(query);
        }
        return numOfAffectedRows;
    }

    @Override
    public int checkout(int reservationNo) throws RemoteException, SQLException{
        Statement stmt = con.createStatement();
        String query = "select room_status, room_no from room where room_no='"+ reservationNo + "' ;";
        ResultSet result = stmt.executeQuery(query);
        int numOfAffectedRows = 0;
        if(!result.next()){
            System.out.println("You haven't reserved a room yet. Reserve a room first before you can perform a check-in!");
        }else{
            query = "update room set room_status='vacant' where room_no=" + result.getInt("room_no");
            numOfAffectedRows = stmt.executeUpdate(query);
        }
        return numOfAffectedRows;
    }

    @Override
    public void viewVacant() throws RemoteException, SQLException{
   
        Statement stmt = con.createStatement();
        String query = "select * from room where room_status='vacant'";
        ResultSet result = stmt.executeQuery(query);

        if(!result.next()){
            System.out.println("There are currently no rooms available!");
        }else{
            System.out.println("|-----------------------------|");
            System.out.println("| Room No | Capacity |  Price per day  |");
            while(result.next()){
                System.out.printf("|    %-2s   |    %-2s    |      %-6f     |", result.getInt("room_no"), result.getInt("capacity"), result.getDouble("price"));
                System.out.println("|-----------------------------|");
            }
        }
    }
/*    
    public void viewOccupiedDays() throws SQLException{
        Statement stmt = con.createStatement();
        String query = "select * from room "
    }
*/
    @Override
    public void viewOccupied()throws RemoteException, SQLException{
        Statement stmt = con.createStatement();
        String query = "select * from room where room_status='occupied'";
        ResultSet result = stmt.executeQuery(query);

        if(!result.next()){
            System.out.println("All rooms are vacant! :-)");
        }else{
            System.out.println("-------------------------------");
            System.out.println("| Room No | Capacity |  Price per day  |");
            while(result.next()){
                System.out.printf("|    %-2s   |    %-2s    |  %-6f |", result.getInt("room_no"), result.getInt("capacity"), result.getDouble("price"));
                System.out.println("|-----------------------------|");
            }
        }
    }

    @Override
    public int makeReservation(String name, int noOfLodgers, int roomNo, String reserveDate, String checkIn, String checkOut) throws RemoteException, SQLException {
        
        String query = "select room_no, check_in, check_out from reservation where room_no=? and ((check_in between ? and ?) or (check_out between ? and ?) or (check_in <= ? and check_out >= ?) )";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,roomNo);
        stmt.setString(2,checkIn);
        stmt.setString(3,checkOut);
        stmt.setString(4,checkIn);
        stmt.setString(5,checkOut);
        stmt.setString(6,checkIn);
        stmt.setString(7,checkOut);
        
        int numOfInsertedRows = 0;
        ResultSet result = stmt.executeQuery();
        if(!result.next()){
            int amountPayable = computeAmount(0,"","");
    
            query = "insert into reservation (applicant_name, room_no, reserve_date, check_in, check_out, no_of_lodgers, amount_payable) values (?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setInt(2,roomNo);
            stmt.setString(3,reserveDate);
            stmt.setString(4,checkIn);
            stmt.setString(5,checkOut);
            stmt.setInt(6,noOfLodgers);
            stmt.setInt(7,amountPayable);
            
            numOfInsertedRows = stmt.executeUpdate();
        }
        return numOfInsertedRows;
    }
    
    public int computeAmount(int roomNo, String checkIn, String checkOut){
        return 0;
    }

    
}
