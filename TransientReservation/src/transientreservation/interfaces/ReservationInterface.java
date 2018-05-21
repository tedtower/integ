package transientreservation.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
/**
 *
 * @author Ramos, Tatum Eiffel Dodge
 */
public interface ReservationInterface extends Remote{
    public int checkin(int reservationNo) throws RemoteException, SQLException;
    public int checkout(int reservationNo) throws RemoteException, SQLException;
    public void viewVacant() throws RemoteException, SQLException;
    public void viewOccupied() throws RemoteException, SQLException;
    public int makeReservation(String name,int noOfLodgers, int roomNo, String reserveDate, String checkIn, String checkout) throws RemoteException, SQLException;
}
