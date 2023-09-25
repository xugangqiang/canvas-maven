package com.citi.custody.canvas.command;

import com.citi.custody.canvas.core.LogUtil;
import com.citi.custody.canvas.exception.CommandCreationException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class CanvasCommandHelper {

    private static final Map<String, Class<CanvasOperationCommand>> REGISTER = new HashMap<>();

    static {
        for (CanvasCommandTypeEnum cmd  : CanvasCommandTypeEnum.values()) {
            REGISTER.put(cmd.name(), cmd.getCommandClass());
        }
    }

    private CanvasCommandHelper() {

    }

    public static final Optional<CanvasOperationCommand> getCommand(String[] args) {
        if (args == null || args.length == 0) {
            LogUtil.log("no command provided");
            return Optional.empty();
        }
        String cmdString = args[0];
        Class<CanvasOperationCommand> clz = REGISTER.get(cmdString);
        if (clz == null) {
            LogUtil.log("invalid command:" + cmdString);
            return Optional.empty();
        }
        try {
            Constructor<CanvasOperationCommand> constructor = clz.getConstructor(String[].class);
            CanvasOperationCommand cmd = constructor.newInstance(new Object[]{args});
            return Optional.of(cmd);
        } catch (Exception e) {
            throw new CommandCreationException(e.getMessage());
        }
    }
}
