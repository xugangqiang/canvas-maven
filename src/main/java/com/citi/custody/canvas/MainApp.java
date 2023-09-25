package com.citi.custody.canvas;

import com.citi.custody.canvas.command.CanvasCommandHelper;
import com.citi.custody.canvas.command.CanvasOperationCommand;
import com.citi.custody.canvas.command.QuitCommand;
import com.citi.custody.canvas.core.ICanvas;
import com.citi.custody.canvas.core.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class MainApp {

    private static ICanvas canvas = null;

    public static void main(String[] args) {
        process();
    }

    private static void process() {
        String commandStr;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                commandStr = reader.readLine();
                processCommand(commandStr);
                showCanvas(canvas);
            } catch (Exception e) {
                String msg = String.format("%s, message:%s", e.getClass().getCanonicalName(), e.getMessage());
                LogUtil.log(msg);
                LogUtil.log("Please type correct command");
            }
        }
    }

    public static ICanvas processCommand(String commandStr) {
        Optional<CanvasOperationCommand> cmd = CanvasCommandHelper.getCommand(commandStr.split(" "));
        if (!cmd.isPresent()) {
            LogUtil.log("invalid command:" + commandStr);
            return canvas;
        }
        CanvasOperationCommand command = cmd.get();
        if (QuitCommand.class.isInstance(command)) {
            processQuit();
        }
        canvas = command.execute(canvas);
        return canvas;
    }

    private static void processQuit() {
        LogUtil.log("receive quit command, exit...");
        System.exit(0);
    }

    public static void showCanvas(ICanvas canvas) {
        List<String> view = canvas.getCanvasView();
        for (String str : view) {
            System.out.println(str);
        }
    }
}
