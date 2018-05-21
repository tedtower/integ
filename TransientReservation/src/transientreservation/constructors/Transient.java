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
    private String location;

    public Transient(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    public Transient(){
        name = getName();
        location = getLocation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
   
}
