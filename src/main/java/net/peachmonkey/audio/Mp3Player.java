package net.peachmonkey.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Mp3Player {

    public synchronized void play(File audioFile) {
        Media hit = new Media(audioFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

}
