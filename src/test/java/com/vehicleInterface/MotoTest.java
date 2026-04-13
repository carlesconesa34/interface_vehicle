package com.vehicleInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Moto class.
 */
class MotoTest {

    private Moto moto;

    @BeforeEach
    void setUp() {
        // posX=0, posY=0, money=1000, available=true, petrol=50
        moto = new Moto(0, 0, 1000, true, 50);
    }

    // --- move() ---

    @Test
    @DisplayName("move('x') increases posX when available and has petrol")
    void testMoveX_success() {
        assertTrue(moto.move('x'));
        assertEquals(Movable.MOTO, moto.getPosX());
        assertEquals(50 - Movable.MOTO_CONSUM, moto.getPetrol());
    }

    @Test
    @DisplayName("move('y') increases posY when available and has petrol")
    void testMoveY_success() {
        assertTrue(moto.move('y'));
        assertEquals(Movable.MOTO, moto.getPosY());
        assertEquals(50 - Movable.MOTO_CONSUM, moto.getPetrol());
    }

    @Test
    @DisplayName("move() fails when moto is not available")
    void testMove_notAvailable() {
        moto.setAvailable(false);
        assertFalse(moto.move('x'));
        assertEquals(0, moto.getPosX());
    }

    @Test
    @DisplayName("move() fails when petrol is insufficient")
    void testMove_noPetrol() {
        moto.setPetrol(0);
        assertFalse(moto.move('x'));
    }

    @Test
    @DisplayName("move() throws AssertionError on invalid direction")
    void testMove_invalidDirection() {
        assertThrows(AssertionError.class, () -> moto.move('z'));
    }

    // --- collision() ---

    @Test
    @DisplayName("collision() sets available to false when moto is available")
    void testCollision_success() {
        assertTrue(moto.collision());
        assertFalse(moto.isAvailable());
    }

    @Test
    @DisplayName("collision() returns false when moto is already unavailable")
    void testCollision_alreadyUnavailable() {
        moto.setAvailable(false);
        assertFalse(moto.collision());
    }

    // --- repair() ---

    @Test
    @DisplayName("repair() restores availability and deducts cost")
    void testRepair_success() {
        moto.setAvailable(false);
        assertTrue(moto.repair());
        assertTrue(moto.isAvailable());
        assertEquals(1000 - Repairable.MOTO, moto.getMoney());
    }

    @Test
    @DisplayName("repair() fails when moto is available (not broken)")
    void testRepair_alreadyAvailable() {
        assertFalse(moto.repair());
    }

    @Test
    @DisplayName("repair() fails when not enough money")
    void testRepair_notEnoughMoney() {
        moto.setAvailable(false);
        moto.setMoney(100); // MOTO repair costs 300
        assertFalse(moto.repair());
        assertFalse(moto.isAvailable());
    }

    // --- refill() ---

    @Test
    @DisplayName("refill() fills petrol to max and deducts cost")
    void testRefill_success() {
        moto.setPetrol(20);
        int expectedCost = (Refillable.MOTO - 20) * Refillable.PRICE;
        assertTrue(moto.refill());
        assertEquals(Refillable.MOTO, moto.getPetrol());
        assertEquals(1000 - expectedCost, moto.getMoney());
    }

    @Test
    @DisplayName("refill() fails when petrol is already full")
    void testRefill_alreadyFull() {
        moto.setPetrol(Refillable.MOTO);
        assertFalse(moto.refill());
    }

    @Test
    @DisplayName("refill() fails when moto is not available")
    void testRefill_notAvailable() {
        moto.setAvailable(false);
        moto.setPetrol(0);
        assertFalse(moto.refill());
    }

    @Test
    @DisplayName("refill() fails when not enough money")
    void testRefill_notEnoughMoney() {
        moto.setPetrol(0);
        moto.setMoney(0);
        assertFalse(moto.refill());
    }

    // --- toString() ---

    @Test
    @DisplayName("toString() starts with 'Moto ->'")
    void testToString() {
        assertTrue(moto.toString().startsWith("Moto ->"));
    }
}
