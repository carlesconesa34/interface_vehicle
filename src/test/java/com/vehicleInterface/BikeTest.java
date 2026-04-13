package com.vehicleInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Bike class.
 */
class BikeTest {

    private Bike bike;

    @BeforeEach
    void setUp() {
        // posX=0, posY=0, money=500, available=true
        bike = new Bike(0, 0, 500, true);
    }

    // --- move() ---

    @Test
    @DisplayName("move('x') increases posX when available")
    void testMoveX_success() {
        assertTrue(bike.move('x'));
        assertEquals(Movable.BIKE, bike.getPosX());
    }

    @Test
    @DisplayName("move('y') increases posY when available")
    void testMoveY_success() {
        assertTrue(bike.move('y'));
        assertEquals(Movable.BIKE, bike.getPosY());
    }

    @Test
    @DisplayName("move() accumulates position over multiple calls")
    void testMove_accumulates() {
        bike.move('x');
        bike.move('x');
        assertEquals(Movable.BIKE * 2, bike.getPosX());
    }

    @Test
    @DisplayName("move() fails when bike is not available")
    void testMove_notAvailable() {
        bike.setAvailable(false);
        assertFalse(bike.move('x'));
        assertEquals(0, bike.getPosX());
    }

    @Test
    @DisplayName("move() throws AssertionError on invalid direction")
    void testMove_invalidDirection() {
        assertThrows(AssertionError.class, () -> bike.move('z'));
    }

    // --- collision() ---

    @Test
    @DisplayName("collision() sets available to false when bike is available")
    void testCollision_success() {
        assertTrue(bike.collision());
        assertFalse(bike.isAvailable());
    }

    @Test
    @DisplayName("collision() returns false when bike is already unavailable")
    void testCollision_alreadyUnavailable() {
        bike.setAvailable(false);
        assertFalse(bike.collision());
    }

    // --- repair() ---

    @Test
    @DisplayName("repair() restores availability and deducts cost")
    void testRepair_success() {
        bike.setAvailable(false);
        assertTrue(bike.repair());
        assertTrue(bike.isAvailable());
        assertEquals(500 - Repairable.BIKE, bike.getMoney());
    }

    @Test
    @DisplayName("repair() fails when bike is available (not broken)")
    void testRepair_alreadyAvailable() {
        assertFalse(bike.repair());
        assertEquals(500, bike.getMoney());
    }

    @Test
    @DisplayName("repair() fails when not enough money")
    void testRepair_notEnoughMoney() {
        bike.setAvailable(false);
        bike.setMoney(0); // BIKE repair costs 50
        assertFalse(bike.repair());
        assertFalse(bike.isAvailable());
    }

    @Test
    @DisplayName("full cycle: move -> collision -> repair -> move again")
    void testFullCycle() {
        assertTrue(bike.move('x'));
        assertEquals(Movable.BIKE, bike.getPosX());

        assertTrue(bike.collision());
        assertFalse(bike.isAvailable());
        assertFalse(bike.move('x')); // Can't move while unavailable

        assertTrue(bike.repair());
        assertTrue(bike.isAvailable());

        assertTrue(bike.move('x'));
        assertEquals(Movable.BIKE * 2, bike.getPosX());
    }

    // --- toString() ---

    @Test
    @DisplayName("toString() starts with 'Bike ->'")
    void testToString() {
        assertTrue(bike.toString().startsWith("Bike ->"));
    }
}