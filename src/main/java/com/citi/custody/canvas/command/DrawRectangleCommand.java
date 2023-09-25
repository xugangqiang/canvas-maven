package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.Coordinate;
import com.citi.custody.canvas.core.ICanvas;

public class DrawRectangleCommand extends AbstractCanvasOperationCommand {

    public DrawRectangleCommand(String[] params) {
        super(CanvasCommandTypeEnum.R.name(), params);
    }

    @Override
    public int getExpectedParameterCount() {
        return 5;
    }

    @Override
    public ICanvas execute(ICanvas canvas) {
        Coordinate from = getCoordinateFromParameter(1, 2);
        Coordinate to = getCoordinateFromParameter(3, 4);
        canvas.drawRectangle(from, to);
        return canvas;
    }


}
