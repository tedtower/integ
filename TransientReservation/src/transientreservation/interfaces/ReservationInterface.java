package transientreservation.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import transientreservation.constructors.*;
/**
 *
 * @author Ramos, Tatum Eiffel Dodge
 */
public interface ReservationInterface extends Remote{
    public String viewTransInfo();
    public void checkin(int reservationNo) throws RemoteException, SQLException;
    public void checkout(int reservationNo) throws RemoteException, SQLException;
    public void viewVacant() throws RemoteException;
    public void viewOccupied() throws RemoteException;
}
