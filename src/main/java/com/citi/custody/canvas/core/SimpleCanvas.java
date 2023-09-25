package com.citi.custody.canvas.core;

import com.citi.custody.canvas.exception.OnlyVerticalOrHorizontalLineSupportedException;

import java.util.Arrays;

public class SimpleCanvas extends AbstractCanvas {

    protected final char lineFillChar;

    protected final char xCoordinateRectangleFillChar;

    protected final char yCoordinateRectangleFillChar;

    protected final char xCoordinateBoundaryFillChar;

    protected final char yCoordinateBoundaryFillChar;

    private final boolean onlyVerticalOrHorizontalSupported;

    public SimpleCanvas(int w, int h, char lineFillChar, char xRectangleFillChar, char yRectangleFillChar, char xc, char yc, boolean flag) {
        super(w, h);
        this.lineFillChar = lineFillChar;
        this.xCoordinateRectangleFillChar = xRectangleFillChar;
        this.yCoordinateRectangleFillChar = yRectangleFillChar;
        this.xCoordinateBoundaryFillChar = xc;
        this.yCoordinateBoundaryFillChar = yc;
        onlyVerticalOrHorizontalSupported = flag;
        init();
    }

    public SimpleCanvas(int w, int h) {
        this(w, h, 'x', 'x', 'x', '-', '|', true);
    }

    protected void init() {
        // draw upper boundary of the canvas
        // drawHorizontalLine(0, board[0].length-1, 0, xCoordinateBoundaryFillChar);
        // draw lower boundary of the canvas
        // drawHorizontalLine(0, board[0].length-1, board.length-1, xCoordinateBoundaryFillChar);
        // draw left boundary of the canvas
        // drawVerticalLine(1, board.length-2, 0, yCoordinateBoundaryFillChar);
        // draw right boundary of the canvas
        // drawVerticalLine(1, board.length-2, 0, yCoordinateBoundaryFillChar);
        for (int i=0; i<board.length; i++) {
            Arrays.fill(board[i], ' ');
        }
        drawRectangleInternally(new Coordinate(0, 0), new Coordinate(board[0].length-1, board.length-1),
                xCoordinateBoundaryFillChar, yCoordinateBoundaryFillChar);
    }

    @Override
    public void drawLineInternally(Coordinate from, Coordinate to) {
        if (onlyVerticalOrHorizontalSupported && from.getX() != to.getX() && from.getY() != to.getY()) {
            throw new OnlyVerticalOrHorizontalLineSupportedException();
        }
        if (from.getX() == to.getX()) { // same X coordinate, draw vertical line
            drawVerticalLine(from.getY(), to.getY(), from.getX(), lineFillChar);
        } else if (from.getY() == to.getY()) { // same Y coordinate, draw horizontal line
            drawHorizontalLine(from.getX(), to.getX(), from.getY(), lineFillChar);
        } else {
            // not supported for now
            LogUtil.log("only vertical or horizontal line supported");
        }
    }

    @Override
    public void drawRectangleInternally(Coordinate upperLeft, Coordinate lowerRight) {
        drawRectangleInternally(upperLeft, lowerRight, this.xCoordinateRectangleFillChar, this.yCoordinateRectangleFillChar);
    }

    private void drawRectangleInternally(Coordinate upperLeft, Coordinate lowerRight, char horizontal, char vertical) {
        int smallerX = Math.min(upperLeft.getX(), lowerRight.getX());
        int biggerX = Math.max(upperLeft.getX(), lowerRight.getX());
        int fromY = Math.min(upperLeft.getY(), lowerRight.getY());
        int toY = Math.max(upperLeft.getY(), lowerRight.getY());

        // draw north line
        drawHorizontalLine(smallerX, biggerX, fromY, horizontal);
        // draw south line
        drawHorizontalLine(smallerX, biggerX, toY, horizontal);
        // draw west line
        drawVerticalLine(fromY + 1, toY - 1, smallerX, vertical);
        // draw east line
        drawVerticalLine(fromY + 1, toY - 1, biggerX, vertical);
    }

    private void drawHorizontalLine(int xfrom, int xto, int y, char fill) {
        int smaller = Math.min(xfrom, xto);
        int bigger = Math.max(xfrom, xto);
        for (int i=smaller; i<=bigger; i++) {
            board[y][i] = fill;
        }
    }

    private void drawVerticalLine(int yfrom, int yto, int x, char fill) {
        int smaller = Math.min(yfrom, yto);
        int bigger = Math.max(yfrom, yto);
        for (int i=smaller; i<=bigger; i++) {
            board[i][x] = fill;
        }
    }
}
