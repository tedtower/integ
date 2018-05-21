package transientreservation.interfaces;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
/**
 *
 * @author Ramos, Tatum Eiffel Dodge
 */
public interface TransientInfo extends Remote{
    public void setTransientName(String name) throws RemoteException;
    public void setRoomCapacity(int capacity) throws RemoteException, SQLException;
    public void sendNotification(String notif) throws RemoteException;
    public void addRoom(int roomNo, int capacity, int price) throws RemoteException, SQLException;
}
