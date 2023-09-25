package com.citi.custody.canvas.core;

import com.citi.custody.canvas.exception.CoordinateOutOfBoundException;
import com.citi.custody.canvas.exception.IllegalCanvasBoundaryException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCanvas implements ICanvas {

    protected final char[][] board;

    public AbstractCanvas(int w, int h) {
        if (w <= 0 || h <= 0) {
            String msg = String.format("width: %s, height: %s", w, h);
            throw new IllegalCanvasBoundaryException(msg);
        }
        board = new char[h+2][w+2];
    }

    protected void validate(Coordinate coordinate) {
        if (coordinate.getX() < 1 || coordinate.getX() > board[0].length - 2
                || coordinate.getY() > board.length - 2
                || coordinate.getY() < 1) {
            throw new CoordinateOutOfBoundException();
        }
    }

    @Override
    public void drawLine(Coordinate from, Coordinate to) {
        validate(from);
        validate(to);
        drawLineInternally(from, to);
    }

    abstract protected void drawLineInternally(Coordinate from, Coordinate to);

    @Override
    public void drawRectangle(Coordinate upperLeft, Coordinate lowerRight) {
        validate(upperLeft);
        validate(lowerRight);
        drawRectangleInternally(upperLeft, lowerRight);
    }

    abstract protected void drawRectangleInternally(Coordinate from, Coordinate to);

    @Override
    public List<String> getCanvasView() {
        List<String> ans = new ArrayList<>(board.length);
        for (int i=0; i<board.length; i++) {
            ans.add(new String(board[i]));
        }
        return Collections.unmodifiableList(ans);
    }
}
