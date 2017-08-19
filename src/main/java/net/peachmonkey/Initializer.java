package net.peachmonkey;

import net.peachmonkey.audio.AudioPlayer;
import net.peachmonkey.audio.AudioUtils;
import net.peachmonkey.game.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Initializer implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private GameState gameState;
    private LocalDateTime lastCompletionAnnouncement = LocalDateTime.now();

    @Scheduled(fixedDelay = 5000)
    public void init() {

    }

    @Override
    public void run(String... args) throws Exception {
        gameState.initialize();
    }
}
