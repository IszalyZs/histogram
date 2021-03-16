package com.mycompany.histogram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {
    private Range range;

    @Test
    public void range_fromBelowZeroOrToBelowFrom_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Range(-1, 3));
    }

    @Test
    public void isInRange_wordLengthInRange_true() {
        range = new Range(1, 3);
        assertTrue(range.isInRange("to"));
    }

    @Test
    public void isRange_wordLengthEqualsToRangeFrom_true() {
        range = new Range(1, 5);
        assertTrue(range.isInRange("toooo"));
    }

    @Test
    public void isRange_wordLengthEqualsToRangeTo_true() {
        range = new Range(1, 2);
        assertTrue(range.isInRange("t"));
    }

    @Test
    public void isRange_wordLengthIsOutOfRange_false() {
        range = new Range(1, 5);
        assertFalse(range.isInRange("toooooo"));
    }

    @Test
    public void toString_haveTwoArguments_true() {
        range = new Range(1, 3);
        assertEquals("1 - 3", range.toString());

    }

    @Test
    public void Range_equalsWithItself() {
        range = new Range(1, 3);
        assertEquals(range, range);
    }

    @Test
    public void Range_equalsWithElse() {
        range = new Range(1, 3);
        Range range2 = new Range(1, 3);
        assertEquals(range, range2);
    }

    @Test
    void Range_notEqualsWithElse() {
        range = new Range(1, 321);
        assertNotEquals(range, new Range(123, 321));
    }
}
