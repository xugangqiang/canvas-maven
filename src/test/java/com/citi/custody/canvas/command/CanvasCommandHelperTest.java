package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.ICanvas;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CanvasCommandHelperTest {

    @Test
    public void testRegisterCommand() {
        try {
            // force static initializer, registerCommand
            Class<CanvasCommandHelper> clz = CanvasCommandHelper.class;
        } catch (Exception e) {
            fail("should not ge here");
        }
    }

    @Test
    public void testInvalidCommand() {
        String[] args = {"c", "10", "2"};
        Optional<CanvasOperationCommand> cmd = CanvasCommandHelper.getCommand(args);
        assertFalse(cmd.isPresent());

        args = new String[] {"l", "10", "2", "10", "4"};
        cmd = CanvasCommandHelper.getCommand(args);
        assertFalse(cmd.isPresent());

        args = new String[] {"r", "10", "2", "10", "4"};
        cmd = CanvasCommandHelper.getCommand(args);
        assertFalse(cmd.isPresent());

        args = new String[] {"q"};
        cmd = CanvasCommandHelper.getCommand(args);
        assertFalse(cmd.isPresent());

        args = new String[] {};
        cmd = CanvasCommandHelper.getCommand(args);
        assertFalse(cmd.isPresent());

        args = null;
        cmd = CanvasCommandHelper.getCommand(args);
        assertFalse(cmd.isPresent());
    }

    @Test
    public void testCreateCanvasCommand() {
        String[] args = {"C", "10", "2"};
        Optional<CanvasOperationCommand> cmd = CanvasCommandHelper.getCommand(args);
        assertTrue(cmd.isPresent());
        assertEquals(CreateCanvasCommand.class, cmd.get().getClass());
    }

    @Test
    public void testDrawLineCommand() {
        String[] args = {"L", "10", "2", "10", "6"};
        Optional<CanvasOperationCommand> cmd = CanvasCommandHelper.getCommand(args);
        assertTrue(cmd.isPresent());
        assertEquals(DrawLineCommand.class, cmd.get().getClass());
    }

    @Test
    public void testDrawRectangleCommand() {
        String[] args = {"R", "1", "2", "10", "6"};
        Optional<CanvasOperationCommand> cmd = CanvasCommandHelper.getCommand(args);
        assertTrue(cmd.isPresent());
        assertEquals(DrawRectangleCommand.class, cmd.get().getClass());
    }

    @Test
    public void testQuitCommand() {
        String[] args = {"Q"};
        Optional<CanvasOperationCommand> cmd = CanvasCommandHelper.getCommand(args);
        assertTrue(cmd.isPresent());
        assertEquals(QuitCommand.class, cmd.get().getClass());
    }
}
