package transientreservation.interfaces;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import transientreservation.constructors.Payment;
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
public interface LandlordInterface extends Remote{
    public void setTransientName(String name) throws RemoteException;
    public void setTransientLocation(String Location) throws RemoteException;
    public int setRoomCapacity(int roomNo, int capacity) throws RemoteException, SQLException;
    public int addRoom(int roomNo, int capacity, int price) throws RemoteException, SQLException;
    public boolean addPayment(Payment payment) throws RemoteException, SQLException;
}
