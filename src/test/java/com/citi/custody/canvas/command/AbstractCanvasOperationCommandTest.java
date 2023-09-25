package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.AbstractCanvas;
import com.citi.custody.canvas.core.Coordinate;
import com.citi.custody.canvas.core.ICanvas;
import com.citi.custody.canvas.core.SimpleCanvas;
import com.citi.custody.canvas.exception.IllegalCoordinateException;
import com.citi.custody.canvas.exception.ParameterMissingException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractCanvasOperationCommandTest {

    @Test
    public void testValidateParameterSize() {
        AbstractCanvas canvas = new SimpleCanvas(20, 4);
        String[] args = {"L", "1", "2", "6", "2"};
        try {
            new AbstractCanvasOperationCommand("L", args) {
                @Override
                protected int getExpectedParameterCount() {
                    return 6;
                }

                @Override
                public ICanvas execute(ICanvas canvas) {
                    return null;
                }
            };
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(ParameterMissingException.class.isInstance(e));
        }

        try {
            new AbstractCanvasOperationCommand("L", args) {
                @Override
                protected int getExpectedParameterCount() {
                    return 5;
                }

                @Override
                public ICanvas execute(ICanvas canvas) {
                    return null;
                }
            };
        } catch (Exception e) {
            fail("should not go here");
        }
    }

    @Test
    public void testSafeParseInteger() {
        Integer v = AbstractCanvasOperationCommand.safeParseInteger(null);
        assertTrue(v == null);

        v = AbstractCanvasOperationCommand.safeParseInteger("");
        assertTrue(v == null);

        v = AbstractCanvasOperationCommand.safeParseInteger("abc");
        assertTrue(v == null);

        v = AbstractCanvasOperationCommand.safeParseInteger("1a");
        assertTrue(v == null);

        v = AbstractCanvasOperationCommand.safeParseInteger("123");
        assertEquals(123, v.intValue());
    }

    @Test
    public void testGetCoordinateFromParameter() {
        AbstractCanvas canvas = new SimpleCanvas(20, 4);
        String[] args = {"L", "1", "2", "6", "2"};
        AbstractCanvasOperationCommand command = new AbstractCanvasOperationCommand("L", args) {
            @Override
            protected int getExpectedParameterCount() {
                return 5;
            }

            @Override
            public ICanvas execute(ICanvas canvas) {
                return null;
            }
        };
        Coordinate from = command.getCoordinateFromParameter(1, 2);
        assertEquals(1, from.getX());
        assertEquals(2, from.getY());

        Coordinate to = command.getCoordinateFromParameter(3, 4);
        assertEquals(6, to.getX());
        assertEquals(2, to.getY());

        String[] args2 = {"L", "1", "2ab", "6", "2"};
        command = new AbstractCanvasOperationCommand("L", args2) {
            @Override
            protected int getExpectedParameterCount() {
                return 5;
            }

            @Override
            public ICanvas execute(ICanvas canvas) {
                return null;
            }
        };
        try {
            command.getCoordinateFromParameter(1, 2);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

    }
}
