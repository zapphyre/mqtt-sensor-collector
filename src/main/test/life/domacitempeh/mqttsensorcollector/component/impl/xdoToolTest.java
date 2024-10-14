package life.domacitempeh.mqttsensorcollector.component.impl;

import com.sun.jna.platform.unix.X11;
import io.github.kingpulse.XFacade;
import io.github.kingpulse.structs.xdo_search_t;
import io.github.kingpulse.structs.xdo_t;
import io.github.kingpulse.xdotool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class xdoToolTest {
    private final xdotool lib = xdotool.loadLib();
    private static xdo_t xdo = null;

    @BeforeEach
    public void init() {
        try {
            xdo = lib.xdo_new("0");

            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }




    @Test
    void testWindowSelect() {
        xdo_search_t search = new xdo_search_t();
//        search.pid = 120568;
        search.searchmask = (int) xdo_search_t.searchmask_SEARCH_PID;
        search.max_depth = -1;
//        search.require = xdo_search_t.searchmask_SEARCH_PID;

        XFacade fac = new XFacade();
        X11.Window[] windows = fac.searchWindows(xdo, search);
    }
}
