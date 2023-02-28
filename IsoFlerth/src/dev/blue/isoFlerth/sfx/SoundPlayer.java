package dev.blue.isoFlerth.sfx;

//import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	
	public URL testSong;
	
	public SoundPlayer() {
		testSong = SoundPlayer.class.getResource("/" + "sfx/client/test.aiff");//should play now?
	}
	
	public void playSound(URL soundURL) {
		Media hit = new Media(soundURL.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
	
	public void playASound(URL soundURL) {
		File sound = new File(soundURL.getPath());
		try {
			AudioFileFormat format = AudioSystem.getAudioFileFormat(sound);
			if (format.getType() != AudioFileFormat.Type.AIFF) {
				sound = SoundFileConverter.ConvertFileToAIFF(soundURL.getPath());
			}
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    //MediaPlayer media
	}
}
