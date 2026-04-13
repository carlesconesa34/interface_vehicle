package com.vehicleInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Truck class.
 */
class TruckTest {

    private Truck truck;

    @BeforeEach
    void setUp() {
        // posX=0, posY=0, money=1000, available=true, petrol=200, load=500
        truck = new Truck(0, 0, 1000, true, 200, 500);
    }

    // --- move() ---

    @Test
    @DisplayName("move('x') increases posX when available and has petrol")
    void testMoveX_success() {
        assertTrue(truck.move('x'));
        assertEquals(Movable.TRUCK, truck.getPosX());
        assertEquals(200 - Movable.TRUCK_CONSUM, truck.getPetrol());
    }

    @Test
    @DisplayName("move('y') increases posY when available and has petrol")
    void testMoveY_success() {
        assertTrue(truck.move('y'));
        assertEquals(Movable.TRUCK, truck.getPosY());
        assertEquals(200 - Movable.TRUCK_CONSUM, truck.getPetrol());
    }

    @Test
    @DisplayName("move() fails when truck is not available")
    void testMove_notAvailable() {
        truck.setAvailable(false);
        assertFalse(truck.move('x'));
        assertEquals(0, truck.getPosX());
    }

    @Test
    @DisplayName("move() fails when petrol is insufficient")
    void testMove_noPetrol() {
        truck.setPetrol(0);
        assertFalse(truck.move('x'));
    }

    @Test
    @DisplayName("move() throws AssertionError on invalid direction")
    void testMove_invalidDirection() {
        assertThrows(AssertionError.class, () -> truck.move('z'));
    }

    // --- collision() ---

    @Test
    @DisplayName("collision() sets available to false when truck is available")
    void testCollision_success() {
        assertTrue(truck.collision());
        assertFalse(truck.isAvailable());
    }

    @Test
    @DisplayName("collision() returns false when truck is already unavailable")
    void testCollision_alreadyUnavailable() {
        truck.setAvailable(false);
        assertFalse(truck.collision());
    }

    // --- repair() ---

    @Test
    @DisplayName("repair() restores availability and deducts cost")
    void testRepair_success() {
        truck.setAvailable(false);
        assertTrue(truck.repair());
        assertTrue(truck.isAvailable());
        assertEquals(1000 - Repairable.TRUCK, truck.getMoney());
    }

    @Test
    @DisplayName("repair() fails when truck is available (not broken)")
    void testRepair_alreadyAvailable() {
        assertFalse(truck.repair());
    }

    @Test
    @DisplayName("repair() fails when not enough money")
    void testRepair_notEnoughMoney() {
        truck.setAvailable(false);
        truck.setMoney(100); // TRUCK repair costs 400
        assertFalse(truck.repair());
        assertFalse(truck.isAvailable());
    }

    // --- refill() ---

    @Test
    @DisplayName("refill() fills petrol to max and deducts cost")
    void testRefill_success() {
        truck.setPetrol(100);
        int expectedCost = (Refillable.TRUCK - 100) * Refillable.PRICE;
        assertTrue(truck.refill());
        assertEquals(Refillable.TRUCK, truck.getPetrol());
        assertEquals(1000 - expectedCost, truck.getMoney());
    }

    @Test
    @DisplayName("refill() fails when petrol is already full")
    void testRefill_alreadyFull() {
        truck.setPetrol(Refillable.TRUCK);
        assertFalse(truck.refill());
    }

    @Test
    @DisplayName("refill() fails when truck is not available")
    void testRefill_notAvailable() {
        truck.setAvailable(false);
        truck.setPetrol(0);
        assertFalse(truck.refill());
    }

    @Test
    @DisplayName("refill() fails when not enough money")
    void testRefill_notEnoughMoney() {
        truck.setPetrol(0);
        truck.setMoney(0);
        assertFalse(truck.refill());
    }

    // --- toString() ---

    @Test
    @DisplayName("toString() starts with 'Truck ->'")
    void testToString() {
        assertTrue(truck.toString().startsWith("Truck ->"));
    }
}
