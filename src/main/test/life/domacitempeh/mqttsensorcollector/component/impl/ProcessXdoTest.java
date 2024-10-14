package life.domacitempeh.mqttsensorcollector.component.impl;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.read;
import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.togglePlayYoutube;


public class ProcessXdoTest {

    @Test
    void testCanSendCommandsToTerminal() throws IOException, InterruptedException {
        togglePlayYoutube();
    }

    @Test
    void testCanMoveMouse() {

    }

    @Test
    void canListYoutubeWindows() throws IOException {
        String[] args = new String[]{"xdotool", "search", "--name", "youtube"};
//        String[] args = new String[]{"xdotool", "search", "--name", "youtube"};
        Process proc = new ProcessBuilder(args).start();

        List<Integer> read = read(proc.getInputStream());
        proc.destroy();
    }
}
