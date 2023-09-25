package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.Coordinate;
import com.citi.custody.canvas.core.ICanvas;

public class DrawLineCommand extends AbstractCanvasOperationCommand {

    public DrawLineCommand(String[] params) {
        super(CanvasCommandTypeEnum.L.name(), params);
    }

    @Override
    public int getExpectedParameterCount() {
        return 5;
    }

    @Override
    public ICanvas execute(ICanvas canvas) {
        Coordinate from = getCoordinateFromParameter(1, 2);
        Coordinate to = getCoordinateFromParameter(3, 4);
        canvas.drawLine(from, to);
        return canvas;
    }
}
