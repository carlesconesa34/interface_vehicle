/*
 * Vehicle.java 2026-04-07
 *
 *
 * ©Copyright 2026 Carles Conesa Mañosa <a251158cc@correu.escoladeltreball.org>
 *
 * This is free software, licensed under the MIT license.
 * See https://opensource.org/licenses/MIT for more information.
 */
package com.vehicleInterface;

/**
 * Class description
 *
 * @author Carles Conesa Mañosa
 * @version 1.0
 * @since 2026-04-07
 */
public abstract class Vehicle implements Movable {

    // Attributes
    /**
     * Description of attribute1
     */
    private int posX;

    /**
     * Description of attribute2
     */
    private int posY;

    /**
     * Description of attribute3
     */
    private int money;

    /**
     * Description of attribute3
     */
    private boolean available;

    /**
     * Default constructor. Initializes all attributes with default values.
     */
    public Vehicle(int posX, int posY, int money, boolean available) {
        this.posX = posX;
        this.posY = posY;
        this.money = money;
        this.available = available;
    }

    // Getters and Setters
    /**
     * Gets the value of attribute1.
     *
     * @return the attribute1
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the value of attribute1.
     *
     * @param posX the attribute1 to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Gets the value of attribute2.
     *
     * @return the attribute2
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the value of attribute2.
     *
     * @param posY the attribute2 to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Gets the value of attribute3.
     *
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the value of attribute3.
     *
     * @param money the attribute3 to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets the value of attribute3.
     *
     * @return available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the value of attribute3.
     *
     * @param available the attribute3 to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Custom methods
    /**
     * Method description
     */
    @Override
    public boolean collision() {
        if (available) {
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
        return "Vehicle ["
                + "posX=" + posX
                + ", posY=" + posY
                + ", money=" + money
                + ", available=" + available
                + ']';
    }
}
