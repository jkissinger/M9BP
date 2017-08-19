package net.peachmonkey.game;

import net.peachmonkey.audio.AudioPlayer;
import net.peachmonkey.ui.MainPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class GameState {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private AudioPlayer audioPlayer;
    @Autowired
    private MainPanel mainPanel;
    private volatile String waitingCode = "";
    private CountDownLatch latch;
    private List<String> successDialog = Arrays.asList("success-1", "success-2", "success-3");
    private List<String> failureDialog = Arrays.asList("failure-1", "failure-2");

    public void initialize() {
        testOne();
        testTwo();
        testThree();
        testFour();
        testFive();
        testSix();
        testSeven();
    }

    public void testOne() {
        mainPanel.addText("Hello.  This is the party enrichment application, I'm here to assist you, you can call me Bacchus.");
        doDelay(3000);
        audioPlayer.play("welcome");
        mainPanel.addText("How did she get in here?? She's confused, she thinks she's still in that other game.");
        audioPlayer.play("game_begin");
        mainPanel.addText("Uhh, that's true, we are ready to start.");
        audioPlayer.play("before_start");
        mainPanel.addText("She's just exaggerating, it's completely safe...I think.");
        audioPlayer.play("refrain");
        mainPanel.addText("Oh crap, I wonder what she was going to tell you not to do???");
        doDelay(1000);
        mainPanel.addText("Oh well, let's get started.  We're going to give you a series of tests.");
        mainPanel.addText("For each test you'll have to enter a code, click the 'Enter Code' button and type M9BP-101");
        waitForCode("M9BP-101");
    }

    public void testTwo() {
        audioPlayer.play("possession");
        mainPanel.addText("Like I said, she still thinks she's in that other game.");
        audioPlayer.play("device-0");
        audioPlayer.play("device-1");
        audioPlayer.play("device-2");
        audioPlayer.play("device-3");
        audioPlayer.play("device-4");
        audioPlayer.play("device-5");
        doDelay(1000);
        mainPanel.addText("How come that keeps happening when she's saying something important? I'm sure it's fine...");
        doDelay(1000);
        mainPanel.addText("The next code is on the back of the device.");
        waitForCode("M9BP-KB22");
    }

    public void testThree() {
        audioPlayer.play("impossible");
        mainPanel.addText("This next one is difficult, but not impossible.");
        audioPlayer.play("no_attempt");
        mainPanel.addText("Prometheus will help you find the code.");
        waitForCode("M9BP-FIRE-BRINGER");
    }

    public void testFour() {
        audioPlayer.play("test_four");
        audioPlayer.play("wellbeing");
        audioPlayer.play("cake_and_grief");
        audioPlayer.play("missed");
        mainPanel.addText("For some reason that sounded threatening.  Don't mind her though, she can't hurt you.");
        mainPanel.addText("Artemis knows where the fourth code is.  She's proud of you.");
        waitForCode("M9BP-DIYBOW");
    }

    public void testFive() {
        audioPlayer.play("companion_cube_arrival");
        mainPanel.addText("Since there is no Vital Apparatus Vent, you'll have to go find the companion cube.");
        mainPanel.addText("He has the next code.");
        waitForCode("M9BP-BROTHER-CUBE");
    }

    public void testSix() {
        audioPlayer.play("companion_cube_take_care");
        audioPlayer.play("inanimate_objects");
        audioPlayer.play("cannot_speak");
        audioPlayer.play("disregard_advice");
        doDelay(2000);
        audioPlayer.play("kill_cube");
        mainPanel.addText("Didn't she just tell you to take care of the cube?");
        audioPlayer.play("destroy");
        mainPanel.addText("--- I think she's right, the cube has to go.");
        audioPlayer.play("incinerator");
        audioPlayer.play("incinerate_cube");
        mainPanel.addText("Do what she says, burn the cube in the incinerator.");
        waitForCode("M9BP-ASHES");
    }

    public void testSeven() {
        audioPlayer.play("euthanized");
        audioPlayer.play("will_be_cake");
        audioPlayer.play("final_test");
        mainPanel.addText("Do you hear that ticking sound?");
        doDelay(3000);
        mainPanel.addText("I think she planted a bomb in the house! Hurry, find it!");
        doDelay(1000);
        audioPlayer.play("what_are_you_doing");
        audioPlayer.play("escaped");
        audioPlayer.play("right_way");
        doDelay(2000);
        audioPlayer.play("over_now");
        audioPlayer.play("where_do_you_think");
        audioPlayer.play("i_dont_think");
        doDelay(1000);
        audioPlayer.play("kill_you");
        audioPlayer.play("last_chance");
        waitForCode("M9BP-BOOM");
        audioPlayer.play("pretended");
        audioPlayer.play("happy");
        audioPlayer.play("tremendous_success");
        audioPlayer.play("device_on_ground");
        doDelay(3000);
        audioPlayer.play("associate");
        audioPlayer.play("no_attempt_leave");
        audioPlayer.play("escort_submission");
        waitForCode("M9BP-REALCAKE");
    }

    private void doDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    private void waitForCode(String code) {
        waitingCode = code;
        try {
            latch = new CountDownLatch(1);
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        waitingCode = "";
    }

    public void codeEntered(String code) {
        if (waitingCode.isEmpty()) {
            return;
        }
        if (waitingCode.equalsIgnoreCase(code)) {
            int index = (int) (Math.random() * successDialog.size());
            String filename = successDialog.get(index);
            audioPlayer.play(filename);
            latch.countDown();
        } else {
            int index = (int) (Math.random() * failureDialog.size());
            String filename = failureDialog.get(index);
            audioPlayer.play(filename);
        }
    }
}
