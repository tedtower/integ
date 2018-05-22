package transientreservation.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import transientreservation.constructors.Reservation;
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
public interface TenantInterface extends Remote{
    public int checkin(int reservationNo) throws RemoteException, SQLException;
    public int checkout(int reservationNo) throws RemoteException, SQLException;
    public void viewVacant() throws RemoteException, SQLException;
    public void viewOccupied() throws RemoteException, SQLException;
    public int makeReservation(Reservation reservation) throws RemoteException, SQLException;
}
