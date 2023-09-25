package com.citi.custody.canvas.core;

import com.citi.custody.canvas.exception.CoordinateOutOfBoundException;
import com.citi.custody.canvas.exception.IllegalCanvasBoundaryException;
import com.citi.custody.canvas.exception.IllegalCoordinateException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AbstractCanvasTest {

    private static class AbstractCanvasInner extends AbstractCanvas {
        public AbstractCanvasInner(int w, int h) {
            super(w, h);
        }

        @Override
        protected void drawLineInternally(Coordinate from, Coordinate to) {

        }

        @Override
        protected void drawRectangleInternally(Coordinate from, Coordinate to) {

        }
    }

    @Test
    public void testCanvas() {
        AbstractCanvas canvas = new AbstractCanvasInner(20, 4);
        List<String> content = canvas.getCanvasView();
        assertEquals(4 + 2, content.size());
        assertEquals(20 + 2, content.get(0).length());

        try {
            new AbstractCanvasInner(20, -1);
        } catch (Exception e) {
            assertTrue(IllegalCanvasBoundaryException.class.isInstance(e));
        }
    }

    @Test
    public void testDrawLine() {
        AbstractCanvas canvas = new AbstractCanvasInner(20, 4);

        try {
            canvas.drawLine(new Coordinate(20, 1), new Coordinate(20, 4));
            canvas.drawLine(new Coordinate(1, 1), new Coordinate(20, 4));
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
            canvas.drawLine(new Coordinate(0, 4), new Coordinate(20, 4));
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
    public void testDrawRectangle() {
        AbstractCanvas canvas = new AbstractCanvasInner(20, 4);

        try {
            canvas.drawRectangle(new Coordinate(1, 1), new Coordinate(19, 4));
        } catch (Exception e) {
            fail("should not go here");
        }

        try {
            canvas.drawLine(new Coordinate(0, 4), new Coordinate(20, 4));
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(CoordinateOutOfBoundException.class.isInstance(e));
        }

        try {
            canvas.drawLine(new Coordinate(0, 6), new Coordinate(20, 6));
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
    public void testGetCanvasView() {
        SimpleCanvas canvas = new SimpleCanvas(20, 4);
        List<String> view = canvas.getCanvasView();

        try {
            view.add("test");
            fail("should not go here");
        } catch (Exception e) {
            assertTrue(UnsupportedOperationException.class.isInstance(e));
        }

        assertEquals(4 + 2, view.size());
    }
}
