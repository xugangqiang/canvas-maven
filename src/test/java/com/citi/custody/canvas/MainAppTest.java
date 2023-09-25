package com.citi.custody.canvas;

import com.citi.custody.canvas.core.ICanvas;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MainAppTest {

    @Test
    public void testProcessQuitCommand() {
        assertFalse(MainApp.isShouldQuit());

        MainApp.processCommand("Q");
        assertTrue(MainApp.isShouldQuit());
    }

    @Test
    public void testProvidedExample() {
        String args = "C 20 4";
        MainApp.processCommand(args);

        args = "L 1 2 6 2";
        MainApp.processCommand(args);

        args = "L 1 2 6 2";
        MainApp.processCommand(args);

        args = "L 6 3 6 4";
        MainApp.processCommand(args);

        args = "R 16 1 20 3";
        ICanvas canvas = MainApp.processCommand(args);

        List<String> view = canvas.getCanvasView();
        for (int i=1; i<=6;i++) {
            assertEquals('x', view.get(2).charAt(i));
        }

        for (int i=1; i<=5;i++) {
            assertEquals('x', view.get(1).charAt(i + 15));
            assertEquals('x', view.get(3).charAt(i + 15));
        }

        assertEquals('x', view.get(3).charAt(6));
        assertEquals('x', view.get(4).charAt(6));
        assertEquals('x', view.get(2).charAt(16));
        assertEquals('x', view.get(2).charAt(20));
    }

    @Test
    public void testDrawRectangle() {
        String args = "C 17 8";
        MainApp.processCommand(args);

        args = "R 1 1 17 7";
        ICanvas canvas = MainApp.processCommand(args);
        List<String> view = canvas.getCanvasView();

        for (int i=1; i<=17;i++) {
            assertEquals('x', view.get(1).charAt(i));
            assertEquals('x', view.get(7).charAt(i));
        }

        for (int i=2; i<=6;i++) {
            assertEquals('x', view.get(i).charAt(1));
            assertEquals('x', view.get(i).charAt(17));
        }
        MainApp.showCanvas(canvas);
    }

    @Test
    public void testDrawLine() {
        String args = "C 17 2";
        MainApp.processCommand(args);

        args = "L 1 1 17 1";
        ICanvas canvas =  MainApp.processCommand(args);
        List<String> view = canvas.getCanvasView();

        for (int i=1; i<=17;i++) {
            assertEquals('x', view.get(1).charAt(i));
        }
    }

    @Test
    public void testCreateCanvas() {
        String args = "C 17 2";
        ICanvas canvas = MainApp.processCommand(args);

        List<String> view = canvas.getCanvasView();
        for (int i=0; i<=18;i++) {
            assertEquals('-', view.get(0).charAt(i));
            assertEquals('-', view.get(3).charAt(i));
        }
        assertEquals('|', view.get(1).charAt(0));
        assertEquals('|', view.get(2).charAt(0));
        assertEquals('|', view.get(1).charAt(18));
        assertEquals('|', view.get(2).charAt(18));
    }

}
