package com.debuggeandoideas.api_collections.stack;

import com.debuggeandoideas.api_collections.dtos.ShipmentStatus;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShipmentStatusHistoryTest {

    @Test
    void currentStatusReflectsTheMostRecentChange() {
        ShipmentStatusHistory history = new ShipmentStatusHistory();

        history.recordChange(ShipmentStatus.CREATED);
        history.recordChange(ShipmentStatus.IN_TRANSIT);

        assertEquals(ShipmentStatus.IN_TRANSIT, history.currentStatus());
    }

    @Test
    void undoLastChangeReturnsToThePreviousStatus() {
        ShipmentStatusHistory history = new ShipmentStatusHistory();
        history.recordChange(ShipmentStatus.CREATED);
        history.recordChange(ShipmentStatus.IN_TRANSIT);
        history.recordChange(ShipmentStatus.OUT_FOR_DELIVERY);

        history.undoLastChange();

        assertEquals(ShipmentStatus.IN_TRANSIT, history.currentStatus());
    }

    @Test
    void undoOnlyReversesTheMostRecentChangeNotAnEarlierOne() {
        ShipmentStatusHistory history = new ShipmentStatusHistory();
        history.recordChange(ShipmentStatus.CREATED);
        history.recordChange(ShipmentStatus.IN_TRANSIT);
        history.recordChange(ShipmentStatus.OUT_FOR_DELIVERY);
        history.recordChange(ShipmentStatus.DELIVERED);

        history.undoLastChange(); // quita DELIVERED, nada más

        assertEquals(ShipmentStatus.OUT_FOR_DELIVERY, history.currentStatus());
    }

    @Test
    void undoLastChangeThrowsWhenHistoryIsEmpty() {
        ShipmentStatusHistory history = new ShipmentStatusHistory();

        assertThrows(NoSuchElementException.class, history::undoLastChange);
    }
}