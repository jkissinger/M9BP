package net.peachmonkey.audio;

import java.io.File;
import java.nio.file.Paths;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.peachmonkey.Constants;
import net.peachmonkey.Constants.Sounds;
import net.peachmonkey.properties.ApplicationProperties;

@Component
public class AudioUtils {

    @Autowired
    private ApplicationProperties props;

    public File getGameSoundFile(String name) {
        return getFile(Sounds.GAME_DIR, name);
    }

    public File getSystemSoundFile(String name) {
        return getFile(Sounds.SYSTEM_DIR, name);
    }

    private File getFile(String directory, String filename) {
        File file = Paths.get(props.getSoundFilesLocation(), Sounds.GAME_DIR, filename + "." + "wav").toFile();
        if (!file.exists()) {
            file = Paths.get(props.getSoundFilesLocation(), Sounds.GAME_DIR, filename + "." + "mp3").toFile();
        }
        return file;
    }
}
