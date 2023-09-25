package com.citi.custody.canvas.core;

import com.citi.custody.canvas.exception.IllegalCoordinateException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testValidate() {
        Coordinate coordinate = new Coordinate(10, 20);

        try {
            coordinate.validate(-10);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

        try {
            coordinate.validate(-1);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

        coordinate.validate(1);
    }

    @Test
    public void testCtor() {
        Coordinate coordinate = new Coordinate(10,20);
        assertEquals(10, coordinate.getX());
        assertEquals(20, coordinate.getY());

        try {
            coordinate = new Coordinate(-10,20);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

        try {
            coordinate = new Coordinate(10,-20);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }
    }

}
