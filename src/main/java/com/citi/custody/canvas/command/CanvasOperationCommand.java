package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.ICanvas;

public interface CanvasOperationCommand {

    ICanvas execute(ICanvas canvas);
}
