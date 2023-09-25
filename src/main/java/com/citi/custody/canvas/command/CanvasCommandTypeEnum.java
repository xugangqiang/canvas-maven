package com.citi.custody.canvas.command;

public enum CanvasCommandTypeEnum {
    C("Canvas", CreateCanvasCommand.class), L("Line", DrawLineCommand.class), R("Rectangle", DrawRectangleCommand.class), Q("Quit", QuitCommand.class);

    private final String description;

    private final Class<CanvasOperationCommand> commandClass;

    CanvasCommandTypeEnum(String str, Class clz) {
        description = str;
        commandClass = clz;
    }

    public Class<CanvasOperationCommand> getCommandClass() {
        return commandClass;
    }
}
