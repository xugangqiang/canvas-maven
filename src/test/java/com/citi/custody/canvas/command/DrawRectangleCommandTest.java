package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.AbstractCanvas;
import com.citi.custody.canvas.core.SimpleCanvas;
import com.citi.custody.canvas.exception.CommandCreationException;
import com.citi.custody.canvas.exception.CoordinateOutOfBoundException;
import com.citi.custody.canvas.exception.IllegalCoordinateException;
import com.citi.custody.canvas.exception.ParameterMissingException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DrawRectangleCommandTest {

    @Test
    public void testGetExpectedParameterCount() {
        String[] args = {"R", "10", "2", "10", "4"};
        DrawRectangleCommand command = new DrawRectangleCommand(args);
        assertEquals(5, command.getExpectedParameterCount());
    }

    @Test
    public void testDrawLine() {
        AbstractCanvas canvas = new SimpleCanvas(20, 4);
        String[] args = {"R", "1", "1", "19", "3"};
        DrawRectangleCommand command = new DrawRectangleCommand(args);
        command.execute(canvas);

        List<String> view = canvas.getCanvasView();
        for (int i=1; i<=19;i++) {
            assertEquals('x', view.get(1).charAt(i));
            assertEquals('x', view.get(3).charAt(i));
        }
        assertEquals('x', view.get(2).charAt(1));
        assertEquals('x', view.get(2).charAt(19));

        try {
            args = new String[] {"R", "1", "1", "19"};
            new DrawRectangleCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(ParameterMissingException.class.isInstance(e));
        }

        try {
            args = new String[] {"R", "1", "1", "19", "ab90"};
            command = new DrawRectangleCommand(args);
            command.execute(canvas);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

        try {
            args = new String[] {"R", "1", "1", "19", "200"};
            command = new DrawRectangleCommand(args);
            command.execute(canvas);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }

        try {
            args = new String[] {"Rabc", "1", "1", "19", "20"};
            new DrawRectangleCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }
    }
}
