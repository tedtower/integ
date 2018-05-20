package transientreservation.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import transientreservation.constructors.*;
/**
 *
 * @author Ramos, Tatum Eiffel Dodge
 */
public interface ReservationInterface extends Remote{
    public String viewTransInfo();
    public void checkin(int roomNo, int lodgerNo, short month, short day, String time) throws RemoteException;
    public void checkout(int roomNo, short month, short day, String time) throws RemoteException;
    public void viewVacant() throws RemoteException;
    public void viewOccupied() throws RemoteException;
}
