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

public class SoundPlayer {
	
	public URL testSong;
	
	public SoundPlayer() {
		testSong = SoundPlayer.class.getResource("/" + "sfx/client/test.aiff");//should play now?
	}
	
	public void playSound(URL soundURL) {
		//AudioClip sound = new AudioClip("file:///Downloads/notes/A3.aiff");
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
	}
}
