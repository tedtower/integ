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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class TransientInfoImpl implements TransientInfo{
    private Connection con;
    
    public TransientInfoImpl(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transient_house","root","");
        }catch(SQLException ex){
            System.out.println("Encountered a problem connection to the database!");
            System.exit(0);
        }
    }

    @Override
    public void setTransientName(String name) throws RemoteException {
        
    }

    @Override
    public void setRoomCapacity(int capacity) throws RemoteException {
        
    }

    @Override
    public void sendNotification(String notif) throws RemoteException {
        
    }
}
