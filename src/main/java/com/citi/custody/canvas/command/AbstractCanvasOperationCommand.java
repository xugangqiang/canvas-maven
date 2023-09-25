package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.Coordinate;
import com.citi.custody.canvas.exception.CommandCreationException;
import com.citi.custody.canvas.exception.ParameterMissingException;
import com.citi.custody.canvas.exception.IllegalCoordinateException;

public abstract class AbstractCanvasOperationCommand implements CanvasOperationCommand {

    protected final String[] parameters;

    protected final String expectedFirstParameter;

    public AbstractCanvasOperationCommand(String first, String[] params) {
        expectedFirstParameter = first;
        parameters = params;
        validateFirstParameter();
        validateParameterSize();
    }

    abstract protected int getExpectedParameterCount();

    public void validateFirstParameter() {
        if (expectedFirstParameter == null || expectedFirstParameter.isEmpty()) {
            return;
        }
        if (parameters == null || parameters.length == 0) {
            String msg = String.format("command type missing, expected: %s", expectedFirstParameter);
            throw new CommandCreationException(msg);
        }
        if (!expectedFirstParameter.equals(parameters[0])) {
            String msg = String.format("command type incorrect, expected: %s, actual:%s", expectedFirstParameter, parameters[0]);
            throw new CommandCreationException(msg);
        }
    }

    public void validateParameterSize() {
        final int size = parameters == null ? 0 : parameters.length;
        final int count = getExpectedParameterCount();
        if (size < count) {
            String msg = String.format("expected: %d parameters, but only: %d", count, size);
            throw new ParameterMissingException(msg);
        }
    }

    public static Integer safeParseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

    public Coordinate getCoordinateFromParameter(int xIndex, int yIndex) {
        Integer x = safeParseInteger(parameters[xIndex]);
        if (x == null) {
            throw new IllegalCoordinateException(parameters[xIndex]);
        }

        Integer y = safeParseInteger(parameters[yIndex]);
        if (y == null) {
            throw new IllegalCoordinateException(parameters[yIndex]);
        }

        return new Coordinate(x, y);
    }
}
