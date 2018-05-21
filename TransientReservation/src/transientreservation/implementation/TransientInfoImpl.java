/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transientreservation.implementation;

import java.rmi.RemoteException;
import transientreservation.interfaces.TransientInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import transientreservation.constructors.Transient;
/**
 *
 * @author HP
 */
public class TransientInfoImpl implements TransientInfo{
    private Connection con;
    
    public TransientInfoImpl() throws SQLException{
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transient_house","root","");
    }

    @Override
    public void setTransientName(String name) throws RemoteException {
        Transient trans = new Transient();
        trans.setName(name);
    }

    @Override
    public int setRoomCapacity(int roomNo, int capacity) throws RemoteException, SQLException {
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
    public void sendNotification(String notif) throws RemoteException {
        
    }

    @Override
    public int addRoom(int roomNo, int capacity, int price) throws RemoteException, SQLException {
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
}
