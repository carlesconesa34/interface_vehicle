/*
 * Car.java 2026-04-07
 *
 *
 * ©Copyright 2026 Carles Conesa Mañosa <a251158cc@correu.escoladeltreball.org>
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

package com.vehicleInterface;

/**
 * Class description
 *
 * @author Carles Conesa Mañosa
 * @version 1.0
 * @since 2026-04-07
 */
public class Car extends Vehicle{
    
    /** Description of attribute2 */
    private int petrol;

    /**
     * Default constructor.
     * Initializes all attributes with default values.
     */
    public Car(int posX, int posY, int money, boolean available, int petrol) {
        super(posX, posY, money, available);
        this.petrol = petrol;
    }

    // Getters and Setters
    
    /**
     * Gets the value of petrol.
     *
     * @return the petrol
     */
    public int getPetrol() {
        return petrol;
    }
    
    /**
     * Sets the value of petrol.
     *
     * @param petrol the petrol to set
     */
    public void setPetrol(int petrol) {
        this.petrol = petrol;
    }

    // Custom methods
    
    /**
     * Method description
     */
    public void methodName() {
        // TODO: Implement method
    }

    @Override
    public boolean collision(){
        if(isAvailable()){
            setAvailable(false);
            return true;
        }

        return false;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Car -> " + super.toString();
    }
}
