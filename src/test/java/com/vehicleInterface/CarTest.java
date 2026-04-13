package com.vehicleInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Car class.
 */
class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        // posX=0, posY=0, money=1000, available=true, petrol=100
        car = new Car(0, 0, 1000, true, 100);
    }

    // --- move() ---

    @Test
    @DisplayName("move('x') increases posX when available and has petrol")
    void testMoveX_success() {
        assertTrue(car.move('x'));
        assertEquals(Movable.CAR, car.getPosX());
        assertEquals(100 - Movable.CAR_CONSUM, car.getPetrol());
    }

    @Test
    @DisplayName("move('y') increases posY when available and has petrol")
    void testMoveY_success() {
        assertTrue(car.move('y'));
        assertEquals(Movable.CAR, car.getPosY());
        assertEquals(100 - Movable.CAR_CONSUM, car.getPetrol());
    }

    @Test
    @DisplayName("move() fails when car is not available")
    void testMove_notAvailable() {
        car.setAvailable(false);
        assertFalse(car.move('x'));
        assertEquals(0, car.getPosX());
    }

    @Test
    @DisplayName("move() fails when petrol is insufficient")
    void testMove_noPetrol() {
        car.setPetrol(0);
        assertFalse(car.move('x'));
        assertEquals(0, car.getPosX());
    }

    @Test
    @DisplayName("move() throws AssertionError on invalid direction")
    void testMove_invalidDirection() {
        assertThrows(AssertionError.class, () -> car.move('z'));
    }

    // --- collision() ---

    @Test
    @DisplayName("collision() sets available to false when car is available")
    void testCollision_success() {
        assertTrue(car.collision());
        assertFalse(car.isAvailable());
    }

    @Test
    @DisplayName("collision() returns false when car is already unavailable")
    void testCollision_alreadyUnavailable() {
        car.setAvailable(false);
        assertFalse(car.collision());
    }

    // --- repair() ---

    @Test
    @DisplayName("repair() restores availability and deducts cost when unavailable and has enough money")
    void testRepair_success() {
        car.setAvailable(false);
        assertTrue(car.repair());
        assertTrue(car.isAvailable());
        assertEquals(1000 - Repairable.CAR, car.getMoney());
    }

    @Test
    @DisplayName("repair() fails when car is available (not broken)")
    void testRepair_alreadyAvailable() {
        assertFalse(car.repair());
        assertEquals(1000, car.getMoney());
    }

    @Test
    @DisplayName("repair() fails when not enough money")
    void testRepair_notEnoughMoney() {
        car.setAvailable(false);
        car.setMoney(100); // CAR repair costs 500
        assertFalse(car.repair());
        assertFalse(car.isAvailable());
    }

    // --- refill() ---

    @Test
    @DisplayName("refill() fills petrol to max and deducts cost")
    void testRefill_success() {
        car.setPetrol(50);
        int expectedCost = (Refillable.CAR - 50) * Refillable.PRICE;
        assertTrue(car.refill());
        assertEquals(Refillable.CAR, car.getPetrol());
        assertEquals(1000 - expectedCost, car.getMoney());
    }

    @Test
    @DisplayName("refill() fails when petrol is already full")
    void testRefill_alreadyFull() {
        car.setPetrol(Refillable.CAR);
        assertFalse(car.refill());
    }

    @Test
    @DisplayName("refill() fails when car is not available")
    void testRefill_notAvailable() {
        car.setAvailable(false);
        car.setPetrol(0);
        assertFalse(car.refill());
    }

    @Test
    @DisplayName("refill() fails when not enough money")
    void testRefill_notEnoughMoney() {
        car.setPetrol(0);
        car.setMoney(0);
        assertFalse(car.refill());
    }

    // --- toString() ---

    @Test
    @DisplayName("toString() starts with 'Car ->'")
    void testToString() {
        assertTrue(car.toString().startsWith("Car ->"));
    }
}
