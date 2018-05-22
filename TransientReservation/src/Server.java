
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.sql.SQLException;
import transientreservation.implementation.Landlord;
import transientreservation.implementation.Tenant;
import transientreservation.interfaces.LandlordInterface;
import transientreservation.interfaces.TenantInterface;

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
public class Server{
    public static void main(String[] args) {
        try {
            Landlord landlord = new Landlord();
            Tenant tenant = new Tenant();
            LandlordInterface landlordStub;
            landlordStub = (LandlordInterface) UnicastRemoteObject.exportObject(landlord, 0);
            TenantInterface tenantStub = (TenantInterface) UnicastRemoteObject.exportObject(tenant,0);

            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            registry.rebind("landlord", landlordStub);
            registry.rebind("tenant", tenantStub);
        } catch (SQLException ex) {
            System.out.println("Can't connect to the database!");
        } catch (RemoteException ex) {
            System.out.println("The registry is not yet running!");
        }

        
    }

}
