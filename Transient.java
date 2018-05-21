/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transientreservation.constructors;

/**
 *
 * @author BAL
 */
public class Transient {
    private String name;
    private int capacity;

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    private int noRoom;
    private String location;

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public String getLocation() {
        return location;
    }

    public Transient(String name, int capacity, int noRoom, String location) {
        this.name = name;
        this.capacity = capacity;
        this.noRoom = noRoom;
        this.location = location;
    }
     
}
