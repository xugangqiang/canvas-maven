package com.citi.custody.canvas.command;

public enum CanvasCommandTypeEnum {
    C(CreateCanvasCommand.class), L( DrawLineCommand.class), R(DrawRectangleCommand.class), Q(QuitCommand.class);

    private final Class<? extends CanvasOperationCommand> commandClass;

    CanvasCommandTypeEnum(Class<? extends CanvasOperationCommand> clz) {
        commandClass = clz;
    }

    public Class getCommandClass() {
        return commandClass;
    }
}
