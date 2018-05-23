

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
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
public interface TenantInterface extends Remote{
    public int checkin(Connection con,int reservationNo) throws RemoteException, SQLException;
    public int checkout(Connection con,int reservationNo) throws RemoteException, SQLException;
    public void viewVacant(Connection con) throws RemoteException, SQLException;
    public void viewOccupied(Connection con) throws RemoteException, SQLException;
    public void viewOccupiedDays(Connection con)throws RemoteException, SQLException;
    public int makeReservation(Connection con,Reservation reservation) throws RemoteException, SQLException;
}
