
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
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
            LandlordInterface landlordStub = (LandlordInterface) UnicastRemoteObject.exportObject(landlord, 0);
            TenantInterface tenantStub = (TenantInterface) UnicastRemoteObject.exportObject(tenant,0);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("landlord", landlordStub);
            registry.rebind("tenant", tenantStub);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
