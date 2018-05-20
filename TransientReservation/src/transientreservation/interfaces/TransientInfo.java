package transientreservation.interfaces;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Ramos, Tatum Eiffel Dodge
 */
public interface TransientInfo extends Remote{
    public void setTransientName(String name) throws RemoteException;
    public void setCapacity(int capacity) throws RemoteException;
    public void sendNotification(String notif) throws RemoteException;
}
