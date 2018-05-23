
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public interface LandlordInterface extends Remote{
    public int setRoomCapacity(Connection con, int roomNo, int capacity) throws RemoteException, SQLException;
    public int setRoomPrice(Connection con,int roomNo, int price) throws RemoteException, SQLException;
    public int addRoom(Connection con,int roomNo, int capacity, int price) throws RemoteException, SQLException;
    public boolean addPayment(Connection con,Payment payment) throws RemoteException, SQLException;
}
