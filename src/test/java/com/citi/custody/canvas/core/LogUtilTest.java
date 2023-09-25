package com.citi.custody.canvas.core;

import com.citi.custody.canvas.core.LogUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogUtilTest {

    @Test
    public void testLog() {
        try {
            LogUtil.log(null);
        } catch (Exception e) {
            fail("should not go here");
        }

        try {
            LogUtil.log("test");
        } catch (Exception e) {
            fail("should not go here");
        }
    }

}
