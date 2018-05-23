
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class LandlordClient {

    public static void main(String[] args) throws NotBoundException {
        try {
            Registry registry = LocateRegistry.getRegistry();
            LandlordInterface landlordStub = (LandlordInterface) registry.lookup("landlord");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transient_house","root","");
            showMenu(con, landlordStub);
        } catch (RemoteException ex) {
            System.out.println("Registry may not be running!");
        } catch (SQLException ex) {
            System.out.println("Cannot connect to the database!");
        }

    }

    public static void showMenu(Connection con, LandlordInterface stub) {

        System.out.println("Welcome Landlord!");

        System.out.println("Menu");
        System.out.println("<1>     Set Room Capacity");
        System.out.println("<2>     Set Room Price");
        System.out.println("<3>     Add Room");
        System.out.println("<4>     Add Payment");
        System.out.println("<5>     Close the Application");

        Scanner in = new Scanner(System.in);
        int choice = 0;
        while (choice != 5) {
            choice = choose();
            switch (choice) {
                case 1:
                    try {
                        System.out.println("Room Number: ");
                        int roomNo = in.nextInt();
                        System.out.println("Room Capacity: ");
                        int capacity = in.nextInt();

                        stub.setRoomCapacity(con, roomNo, capacity);
                    } catch (RemoteException ex) {
                        System.out.println("The registry may not be running.");
                    } catch (SQLException ex) {
                        System.out.println("Can't connect to the database.");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Room Number: ");
                        int roomNo2 = in.nextInt();
                        System.out.println("Room Price: ");
                        int roomPrice = in.nextInt();
                        stub.setRoomPrice(con, roomNo2, roomPrice);
                    } catch (RemoteException ex) {
                        System.out.println("The registry may not be running.");
                    } catch (SQLException ex) {
                        System.out.println("Can't connect to the database.");
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Room Number: ");
                        int roomNo3 = Integer.parseInt(in.nextLine());
                        System.out.print("Capacity: ");
                        int capacity2 = Integer.parseInt(in.nextLine());
                        System.out.print("Price: ");
                        int price2 = Integer.parseInt(in.nextLine());
                        stub.addRoom(con, roomNo3, capacity2, price2);
                    } catch (RemoteException ex) {
                        System.out.println("The registry may not be running.");
                    } catch (SQLException ex) {
                        System.out.println("Can't connect to the database.");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Reservation Number: ");
                        int reservationNo = in.nextInt();
                        System.out.println("Date: ");
                        String date = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                        System.out.println("Total Amount: ");
                        int amount = in.nextInt();

                        Payment payment = new Payment(reservationNo, date, amount);

                        stub.addPayment(con, payment);
                    } catch (RemoteException ex) {
                        System.out.println("The registry may not be running.");
                    } catch (SQLException ex) {
                        System.out.println("Can't connect to the database.");
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }

    }

    public static int choose() {
        Scanner kbd = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                System.out.print("Your choice: ");
                choice = Integer.parseInt(kbd.nextLine());
                break;
            } catch (Exception ex) {
                System.out.println("You have entered an invalid input! Please try again");
            }
        }
        return choice;
    }

}
