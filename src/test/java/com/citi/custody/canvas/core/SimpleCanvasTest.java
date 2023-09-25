package com.citi.custody.canvas.core;

import com.citi.custody.canvas.exception.CoordinateOutOfBoundException;
import com.citi.custody.canvas.exception.IllegalCoordinateException;
import com.citi.custody.canvas.exception.OnlyVerticalOrHorizontalLineSupportedException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimpleCanvasTest {

    @Test
    public void testCanvas() {
        SimpleCanvas canvas = new SimpleCanvas(20, 4);
        List<String> content = canvas.getCanvasView();
        assertEquals(4 + 2, content.size());
        assertEquals(20 + 2, content.get(0).length());
    }

    @Test
    public void testInit() {
        SimpleCanvas canvas = new SimpleCanvas(20, 4);
        List<String> content = canvas.getCanvasView();
        for (int i=0; i<content.get(0).length(); i++) {
            assertEquals('-', content.get(0).charAt(i));
            assertEquals('-', content.get(content.size()-1).charAt(i));
        }

        for (int i=1; i<content.size()-1; i++) {
            assertEquals('|', content.get(i).charAt(0));
            assertEquals('|', content.get(i).charAt(content.get(i).length()-1));
        }
    }

    @Test
    public void testDrawLine() {
        SimpleCanvas canvas = new SimpleCanvas(20, 4);

        try {
            canvas.drawLine(new Coordinate(1, 4), new Coordinate(20, 4));
        } catch (Exception e) {
            fail("should not go here");
        }

        try {
            canvas.drawLine(new Coordinate(1, 4), new Coordinate(21, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }

        try {
            canvas.drawLine(new Coordinate(0, 6), new Coordinate(20, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }

        try {
            canvas.drawLine(new Coordinate(-1, 4), new Coordinate(20, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }

        try {
            canvas.drawLine(new Coordinate(1, 4), new Coordinate(20, 3));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(OnlyVerticalOrHorizontalLineSupportedException.class.isInstance(e));
        }
    }

    @Test
    public void testDrawRectangle() {
        SimpleCanvas canvas = new SimpleCanvas(20, 4);

        try {
            canvas.drawRectangle(new Coordinate(1, 1), new Coordinate(20, 4));
        } catch (Exception e) {
            fail("should not go here");
        }

        try {
            canvas.drawLine(new Coordinate(0, 4), new Coordinate(21, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }

        try {
            canvas.drawLine(new Coordinate(0, 6), new Coordinate(20, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }

        try {
            canvas.drawLine(new Coordinate(-1, 4), new Coordinate(20, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(IllegalCoordinateException.class.isInstance(e));
        }
    }

    @Test
    public void testExample() {
        SimpleCanvas canvas = new SimpleCanvas(20, 4);
        canvas.drawLine(new Coordinate(1, 2), new Coordinate(6, 2));
        List<String> view = canvas.getCanvasView();
        for (int i=1; i<=6;i++) {
            assertEquals('x', view.get(2).charAt(i));
        }

        canvas.drawLine(new Coordinate(6, 3), new Coordinate(6, 4));
        view = canvas.getCanvasView();
        assertEquals('x', view.get(3).charAt(6));
        assertEquals('x', view.get(4).charAt(6));

        canvas.drawRectangle(new Coordinate(16, 1), new Coordinate(20, 3));
        view = canvas.getCanvasView();
        for (int i=16; i<=20;i++) {
            assertEquals('x', view.get(1).charAt(i));
            assertEquals('x', view.get(3).charAt(i));
        }
        assertEquals('x', view.get(2).charAt(16));
        assertEquals('x', view.get(2).charAt(20));
    }
}
