package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.ICanvas;

public class QuitCommand extends AbstractCanvasOperationCommand {

    public QuitCommand(String[] params) {
        super(CanvasCommandTypeEnum.Q.name(), params);
    }

    @Override
    public int getExpectedParameterCount() {
        return 1;
    }

    @Override
    public ICanvas execute(ICanvas canvas) {
        // do nothing
        return canvas; // make compiler happy
    }
}
