/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transientreservation.constructors;

/**
 *
 * @author HP
 */
public class Payment {
    int reservationNo;
    private String date;
    private int amount;

    public Payment(int reservationNo, String date, int amount) {
        this.reservationNo = reservationNo;
        this.date = date;
        this.amount = amount;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getReservationNo() {
        return reservationNo;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
    
}
