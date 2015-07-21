package me.twister915.tweens;

import org.junit.Test;

import java.math.BigInteger;

import static me.twister915.tweens.BaseInterpolateable.inRange;
import static org.junit.Assert.*;

public class BaseInterpolateableTest {

    @Test
    public void testRange() throws Exception {
        assertTrue(BaseInterpolateable.range(0, -1, 20) == 0);
        assertTrue(BaseInterpolateable.range(0, 150, 20) == 20);
        assertTrue(BaseInterpolateable.range(0, 10, 20) == 10);
        assertTrue(BaseInterpolateable.range(0, 20, 20) == 20);
    }

    @Test
    public void testInRange() throws Exception {
        //obviously true
        assertTrue(inRange(0, 2, 5, false));
        assertTrue(inRange(0, 2, 5, true));

        //inclusive, on edge of range
        assertTrue(inRange(0, 0, 5, true));
        assertTrue(inRange(0, 5, 5, true));

        //exclusive, on edge of range
        assertFalse(inRange(0, 0, 5, false));
        assertFalse(inRange(0, 5, 5, false));
        BigInteger.valueOf((long)Math.pow(2d, 1));
    }
}