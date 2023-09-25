package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.ICanvas;
import com.citi.custody.canvas.exception.CommandCreationException;
import com.citi.custody.canvas.exception.IllegalCanvasBoundaryException;
import com.citi.custody.canvas.exception.ParameterMissingException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CreateCanvasCommandTest {

    @Test
    public void testGetExpectedParameterCount() {
        String[] args = {"C", "10", "2"};
        CreateCanvasCommand command = new CreateCanvasCommand(args);
        assertEquals(3, command.getExpectedParameterCount());
    }

    @Test
    public void testCreateCanvas() {
        String[] args = {"C", "10", "2"};
        CreateCanvasCommand command = new CreateCanvasCommand(args);
        ICanvas canvas = command.execute(null);

        List<String> view = canvas.getCanvasView();
        for (int i=0; i<=11;i++) {
            assertEquals('-', view.get(0).charAt(i));
            assertEquals('-', view.get(3).charAt(i));
        }
        assertEquals('|', view.get(1).charAt(0));
        assertEquals('|', view.get(2).charAt(0));
        assertEquals('|', view.get(1).charAt(11));
        assertEquals('|', view.get(2).charAt(11));

        try {
            args = new String[] {"c", "10"};
            new CreateCanvasCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }

        try {
            args = new String[] {"C", "10"};
            new CreateCanvasCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(ParameterMissingException.class.isInstance(e));
        }

        try {
            args = new String[] {"C", "10", "2abc"};
            command = new CreateCanvasCommand(args);
            command.execute(null);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCanvasBoundaryException.class.isInstance(e));
        }
    }
}
