package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.AbstractCanvas;
import com.citi.custody.canvas.core.SimpleCanvas;
import com.citi.custody.canvas.exception.CommandCreationException;
import com.citi.custody.canvas.exception.CoordinateOutOfBoundException;
import com.citi.custody.canvas.exception.ParameterMissingException;
import com.citi.custody.canvas.exception.IllegalCoordinateException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DrawLineCommandTest {

    @Test
    public void testGetExpectedParameterCount() {
        String[] args = {"L", "10", "2", "10", "4"};
        DrawLineCommand command = new DrawLineCommand(args);
        assertEquals(5, command.getExpectedParameterCount());
    }

    @Test
    public void testDrawLine() {
        AbstractCanvas canvas = new SimpleCanvas(20, 4);
        String[] args = {"L", "1", "3", "6", "3"};
        DrawLineCommand command = new DrawLineCommand(args);
        command.execute(canvas);

        List<String> view = canvas.getCanvasView();
        for (int i=1; i<=6;i++) {
            assertEquals('x', view.get(3).charAt(i));
        }

        try {
            args = new String[] {"aL", "1", "3", "6"};
            new DrawLineCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }

        try {
            args = new String[] {"L", "1", "3", "6"};
            new DrawLineCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(ParameterMissingException.class.isInstance(e));
        }

        try {
            args = new String[] {"L", " ", "3", "6", "3"};
            command = new DrawLineCommand(args);
            command.execute(canvas);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

        try {
            args = new String[] {"L", "22", "3", "6", "3"};
            command = new DrawLineCommand(args);
            command.execute(canvas);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }
    }
}
