/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public class Reservation {
    private String name;
    private int roomNo;
    private String date;
    private String checkIn;
    private String checkOut;
    private String status;
    private int lodgerNo;

    public Reservation(String name, int roomNo, String date, String checkIn, String checkOut, String status, int lodgerNo) {
        this.name = name;
        this.roomNo = roomNo;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.lodgerNo = lodgerNo;
    }

    public String getName() {
        return name;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getDate() {
        return date;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public String getStatus() {
        return status;
    }

    public int getLodgerNo() {
        return lodgerNo;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLodgerNo(int lodgerNo) {
        this.lodgerNo = lodgerNo;
    }
    
}
