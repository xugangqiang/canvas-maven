package com.citi.custody.canvas.core;

import java.util.List;

public interface ICanvas {

    /**
     * draw a line on the canvas
     * @param from the starting point of the line
     * @param to the end point of the line
     */
    void drawLine(Coordinate from, Coordinate to);

    /**
     * draw a rectangle on the canvas
     * @param upperLeft the upper left corner of the rectangle
     * @param lowerRight the lower right corner of the rectangle
     */
    void drawRectangle(Coordinate upperLeft, Coordinate lowerRight);

    /**
     *
     * @return a readonly view of the canvas.
     */
    List<String> getCanvasView();
}
