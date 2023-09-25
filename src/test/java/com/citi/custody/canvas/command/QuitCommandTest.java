package com.citi.custody.canvas.command;

import com.citi.custody.canvas.exception.CommandCreationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuitCommandTest {

    @Test
    public void testGetExpectedParameterCount() {
        String[] args = {"Q"};
        QuitCommand command = new QuitCommand(args);
        assertEquals(1, command.getExpectedParameterCount());
    }

    @Test
    public void testCreateCanvas() {
        String[] args = {"Q"};
        QuitCommand command = new QuitCommand(args);
        command.execute(null);

        try {
            args = new String[] {};
            new QuitCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }

        try {
            args = new String[] {"q"};
            new QuitCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }

        try {
            args = new String[] {"aQ"};
            new QuitCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }

        try {
            args = new String[] {"Qabc"};
            new QuitCommand(args);
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CommandCreationException.class.isInstance(e));
        }
    }
}
