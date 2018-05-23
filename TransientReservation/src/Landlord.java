/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Ambo, Melissa
 * @author Calines, Carla
 * @author Catayao, Roxanne
 * @author Domaoa, Jeane Cris
 * @author Marquez, Art Lester
 * @author Olivas, Marvin
 * @author Ramos, Tatum Eiffel Dodge
 * @author Solomon, Jessa Lyn
 */
public class Landlord implements LandlordInterface{

    @Override
    public int setRoomCapacity(Connection con, int roomNo, int capacity) throws RemoteException, SQLException {
        String query = "select room_no from room where room_no=?;";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, roomNo);
        ResultSet result = stmt.executeQuery();
        
        if(!result.next()){
            System.out.println("You have entered an invalid Room Number. It doesn't exist in the database!");
        }else{
            query = "update room set capacity=? where room_no=?;";
            stmt = con.prepareStatement(query);
            stmt.setInt(1,capacity);
            stmt.setInt(2,roomNo);
            
            return stmt.executeUpdate();
        }
        return 0;
    }

    @Override
    public int setRoomPrice(Connection con, int roomNo, int price) throws RemoteException, SQLException {
        String query = "select room_no from room where room_no=?;";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, roomNo);
        ResultSet result = stmt.executeQuery();
        
        if(!result.next()){
            System.out.println("You have entered an invalid Room Number. It doesn't exist in the database!");
        }else{
            query = "update room set price=? where room_no=?;";
            stmt = con.prepareStatement(query);
            stmt.setInt(1,price);
            stmt.setInt(2,roomNo);
            
            return stmt.executeUpdate();
        }
        return 0;
    }

    @Override
    public int addRoom(Connection con, int roomNo, int capacity, int price) throws RemoteException, SQLException {
        String query = "select room_no form room where room_no=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,roomNo);
        ResultSet result = stmt.executeQuery();
        
        if(!result.next()){
            query = "insert into room (room_no,capacity,price,status) values (?,?,?,?);";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, roomNo);
            stmt.setInt(2, capacity);
            stmt.setInt(3, price);
            stmt.setString(4, "vacant");
            
            return stmt.executeUpdate();
        }
        return 0;
    }

    @Override
    public boolean addPayment(Connection con, Payment payment) throws SQLException{
        String query = "select * from payment where reservation_no=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,payment.getReservationNo());
        boolean success = false;
        
        ResultSet result = stmt.executeQuery();
        if(!result.next()){
            return success;
        }
        query = "insert into payment (reservation_no, payment_date, amount) values (?,?,?);";
        String updateQuery = "update reservation set pay_status='paid' where reservation_no=?;";
        PreparedStatement stmt1 = con.prepareStatement(updateQuery);
        stmt = con.prepareStatement(query);
        stmt.setInt(1, payment.getReservationNo());
        stmt.setString(2, payment.getDate());
        stmt.setInt(3, payment.getAmount());
        
        stmt1.setInt(1,payment.getReservationNo());
        if(stmt.executeUpdate() > 0 && stmt1.executeUpdate() > 0){
            success = true;
        }
        return success;  
    }
    
    
}
