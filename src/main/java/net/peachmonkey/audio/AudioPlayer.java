package net.peachmonkey.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.peachmonkey.Constants;
import net.peachmonkey.properties.ApplicationProperties;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AudioPlayer {

    private static final Logger LOGGER = LogManager.getLogger();
    private CountDownLatch latch = new CountDownLatch(1);
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Autowired
    private AudioUtils audioUtils;
    @Autowired
    private ApplicationProperties props;

    public void play(String filename) {
        play(audioUtils.getGameSoundFile(filename));
        //executorService.submit(() -> play(audioUtils.getGameSoundFile(filename)));
    }

    public synchronized void play(File audioFile) {
        if (!audioFile.exists()) {
            LOGGER.warn("Audio file [{}] missing.", audioFile.getAbsolutePath());
            audioFile = audioUtils.getSystemSoundFile(Constants.Sounds.MISSING);
        }
        if (FilenameUtils.getExtension(audioFile.getAbsolutePath()).equals("wav")) {
            try {
                playWavFile(audioFile);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        } else {
            playMp3File(audioFile);
        }
    }

    private void playWavFile(File audioFile) throws InterruptedException {
        latch = new CountDownLatch(1);
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile); Clip clip = AudioSystem.getClip()) {
            listenForEndOf(clip);
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(props.getSoundAnnounceVolumeAdjust());
            clip.start();
            latch.await();
            clip.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            LOGGER.error("Exception playing sound [{}]", audioFile.getAbsolutePath(), e);
            throw new AudioException(e);
        }
    }

    private void listenForEndOf(Clip clip) {
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                latch.countDown();
            }
        });
    }

    private void playMp3File(File audioFile) {
        Media hit = new Media(audioFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
}
