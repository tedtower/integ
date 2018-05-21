package transientreservation.constructors;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Room {
    private int roomNo;
    private int capacity;
    private String status;
    private double price;
    
    public Room(int roomNo, int capacity, String status, double price){
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }
    
    public Room(){
        roomNo = 0;
        capacity = 0;
        status = "vacant";
        price = 0.00;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }
    
}
