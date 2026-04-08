/*
 * Bike.java 2026-04-07
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
public class Bike extends Vehicle implements Movable, Repairable {

    /**
     * Default constructor. Initializes all attributes with default values.
     */
    public Bike(int posX, int posY, int money, boolean available) {
        super(posX, posY, money, available);
    }

    @Override
    public boolean move(char pos) {
        if (isAvailable()) {
            switch (pos) {
                case 'x':
                    setPosX(getPosX() + Movable.BIKE);
                    break;
                case 'y':
                    setPosY(getPosY() + Movable.BIKE);
                    break;
                default:
                    throw new AssertionError();
            }

            return true;
        }

        return false;
    }


    // Custom methods
    /**
     * Method description
     */
    @Override
    public boolean repair() {
        if (!isAvailable() && getMoney() >= Repairable.BIKE) {
            setMoney(getMoney() - Repairable.BIKE);
            setAvailable(true);
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
        return "Bike -> " + super.toString();
    }
}
