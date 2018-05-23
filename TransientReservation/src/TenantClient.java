
import java.rmi.AccessException;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class TenantClient {
    
    public static void main(String [] args){
        try {
            
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            TenantInterface tenantStub = (TenantInterface) registry.lookup("tenant");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transient_house","root","");
            showMenu(con, tenantStub);
        } catch (RemoteException ex) {
            System.out.println("Registry may not be running!");
        } catch (NotBoundException ex) {
            System.out.println("The stub may has not been bound yet!");
        } catch (SQLException ex) {
            System.out.println("Cannot connect to the database!");
        }
           
    }
    
    public static void showMenu(Connection con, TenantInterface stub){
        
        System.out.println("Welcome Tenant To Aguila's Transient House!");
        int choice = 0;
        while(choice != 6){
            System.out.println("Menu");
            System.out.println("<1>     View vacant rooms");
            System.out.println("<2>     View occupied rooms");
            System.out.println("<3>     View days that rooms are occupied");
            System.out.println("<4>     Make a reservation");
            System.out.println("<5>     Check-in a reservation");
            System.out.println("<6>     Check-out a reservation");
            System.out.println("<7>     Close the application");

            System.out.println("If you want to pay for your reservation. Please contact the landlord/landlady of the transient house.");

            choice = choose();
            switch(choice){
                case 1:  try{
                            stub.viewVacant(con);
                        }catch(RemoteException ex){
                            System.out.println("The registry may not be running.");
                        } catch (SQLException ex) {
                            System.out.println("Can' connect to the database.");
                        }
                        break;
                case 2: try{
                            stub.viewVacant(con);
                        }catch(RemoteException ex){
                            System.out.println("The registry may not be running.");
                        } catch (SQLException ex) {
                            System.out.println("Can' connect to the database.");
                        }
                        break;
                case 3: try{
                            stub.viewOccupiedDays(con);
                        }catch(RemoteException ex){
                            System.out.println("The registry may not be running.");
                        } catch (SQLException ex) {
                            System.out.println("Can' connect to the database.");
                        }
                        break;
                case 4: try{
                            performReservation(con, stub);
                        }catch(RemoteException ex){
                            System.out.println("The registry may not be running.");
                        } catch (SQLException ex) {
                            System.out.println("Can't connect to the database.");
                        }
                        break;
                case 5: try{
                            performCheckIn(con, stub);
                        }catch(RemoteException ex){
                            System.out.println("The registry may not be running.");
                        } catch (SQLException ex) {
                            System.out.println("Can't connect to the database.");
                        }
                        break;
                case 6: try{
                            performCheckOut(con, stub);
                        }catch(RemoteException ex){
                            System.out.println("The registry may not be running.");
                        } catch (SQLException ex) {
                            System.out.println("Can't connect to the database.");
                        }
                        break;
                case 7: System.exit(0);
            }
        }
        
    }
    
    public static int choose(){
        Scanner kbd = new Scanner(System.in);
        int choice = 0;
        while(true){
            try{
                System.out.print("Your choice: ");
                choice = Integer.parseInt(kbd.nextLine());
                break;
            }catch(Exception ex){
                System.out.println("You have entered an invalid input! Please try again");
            }
        }
        return choice;
    }
    
    public static void performReservation(Connection con, TenantInterface stub) throws RemoteException, SQLException{
        Reservation reservation = null;
        SimpleDateFormat fmt = new SimpleDateFormat();
        
        String name = null;
        int roomNo = 0;
        String date = null;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        String checkIn = null;
        String checkOut = null;
        int lodgerNo = 1;
        
        Scanner kbd = new Scanner(System.in);
        System.out.print("Name: ");
        name = kbd.nextLine();
        System.out.print("Room Number: ");
        roomNo = Integer.parseInt(kbd.nextLine());
        System.out.println("Please enter the following values for your Check-in date:");
        System.out.print("Year (YYYY): ");
        year = Integer.parseInt(kbd.nextLine());
        System.out.print("Month [00-11]: ");
        month = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        System.out.print("Day (DD): ");
        day = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        System.out.print("Hour (24-hour format): ");
        hour = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        System.out.print("Minute (mm): ");
        minute = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        checkIn = String.format("%s%s%s%s%s%s", year, month, day, hour, minute, 0);
        
        System.out.println("Please enter the following values for your Check-in date:");
        System.out.print("Year (YYYY): ");
        year = Integer.parseInt(kbd.nextLine());
        System.out.print("Month [00-11]: ");
        month = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        System.out.print("Day (DD): ");
        day = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        System.out.print("Hour (24-hour fomat): ");
        hour = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        System.out.print("Minute (mm): ");
        minute = Integer.parseInt(String.format("%02s",kbd.nextLine()));
        checkOut = String.format("%s%s%s%s%s%s", year, month, day, hour, minute, 0);
        
        System.out.print("Number of Lodgers: ");
        lodgerNo = Integer.parseInt(kbd.nextLine());
        
        date = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        reservation = new Reservation(name,roomNo,date,checkIn,checkOut,"unpaid",lodgerNo);
        stub.makeReservation(con, reservation);
    }
    
    public static void performCheckIn(Connection con, TenantInterface stub) throws RemoteException, SQLException{
        Scanner kbd = new Scanner(System.in);
        System.out.print("Reservation Number: ");
        int reservationNo = Integer.parseInt(kbd.nextLine());
        stub.checkin(con, reservationNo);  
    }
    
    public static void performCheckOut(Connection con, TenantInterface stub) throws RemoteException, SQLException{
        Scanner kbd = new Scanner(System.in);
        System.out.print("Reservation Number: ");
        int reservationNo = Integer.parseInt(kbd.nextLine());
        stub.checkout(con, reservationNo);  
    }
            
}
