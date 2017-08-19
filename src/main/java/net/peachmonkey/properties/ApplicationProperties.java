package net.peachmonkey.properties;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sound")
public class ApplicationProperties {

	@Value("${spring.application.name}")
	private String applicationName;
	@Value("${application.api.host:localhost}")
	private String apiHost;
	@Value("${application.api.port:8079}")
	private int apiPort;
	@Value("${sound.announce.volumeAdjust:-15.0f}")
	private float soundAnnounceVolumeAdjust;
	@Value("${sound.announce.seconds.beforeDue:300}")
	private int soundAnnounceSecondsBeforeDue;
	@Value("${sound.announce.seconds.afterDue:3600}")
	private int soundAnnounceSecondsAfterDue;
	@Value("${task.status.overdue.seconds:600}")
	private int taskStatusOverdueSeconds;
	@Value("${sound.delay.pending:150}")
	private int soundDelayPending;
	@Value("${sound.delay.due:60}")
	private int soundDelayDue;
	@Value("${sound.delay.overdue:15}")
	private int soundDelayOverdue;
	@Value("${sound.files.location:Sounds}")
	private String soundFilesLocation;
	@Value("${icon.tray:icon.png}")
	private String trayIconImage;
	@Value("${sound.quietTime.start:11:00:00}")
	private String soundQuietTimeStart;
	@Value("${sound.quietTime.end:07:30:00}")
	private String soundQuietTimeEnd;

	public String getApplicationName() {
		return applicationName;
	}

	public String getApiHost() {
		return apiHost;
	}

	public int getApiPort() {
		return apiPort;
	}

	public float getSoundAnnounceVolumeAdjust() {
		return soundAnnounceVolumeAdjust;
	}

	public int getSoundAnnounceSecondsBeforeDue() {
		return soundAnnounceSecondsBeforeDue;
	}

	public int getSoundAnnounceSecondsAfterDue() {
		return soundAnnounceSecondsAfterDue;
	}

	public int getTaskStatusOverdueSeconds() {
		return taskStatusOverdueSeconds;
	}

	public int getSoundDelayPending() {
		return soundDelayPending;
	}

	public int getSoundDelayDue() {
		return soundDelayDue;
	}

	public int getSoundDelayOverdue() {
		return soundDelayOverdue;
	}

	public String getSoundFilesLocation() {
		return soundFilesLocation;
	}

	public String getTrayIconImage() {
		return trayIconImage;
	}

	public LocalTime getSoundQuietTimeStart() {
		return LocalTime.parse(soundQuietTimeStart);
	}

	public LocalTime getSoundQuietTimeEnd() {
		return LocalTime.parse(soundQuietTimeEnd);
	}

}