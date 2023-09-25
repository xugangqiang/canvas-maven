package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.ICanvas;
import com.citi.custody.canvas.core.SimpleCanvas;
import com.citi.custody.canvas.exception.IllegalCanvasBoundaryException;

public class CreateCanvasCommand extends AbstractCanvasOperationCommand {

    public CreateCanvasCommand(String[] params) {
        super(CanvasCommandTypeEnum.C.name(), params);
    }

    @Override
    public int getExpectedParameterCount() {
        return 3;
    }

    @Override
    public ICanvas execute(ICanvas canvas) {
        Integer width = safeParseInteger(parameters[1]);
        Integer height = safeParseInteger(parameters[2]);
        if (width == null || height == null) {
            String msg = String.format("width: %s, height: %s", parameters[1], parameters[2]);
            throw new IllegalCanvasBoundaryException(msg);
        }
        return new SimpleCanvas(width, height);
    }
}
