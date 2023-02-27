package dev.blue.isoFlerth.sfx;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFileConverter {
	public static File ConvertFileToAIFF(String inputPath) {
		AudioFileFormat inFileFormat;
		File inFile;
		File outFile;
		try {
			inFile = new File(inputPath);
			outFile = new File(inputPath);
		} catch (NullPointerException ex) {
			System.out.println("Error: one of the ConvertFileToAIFF" + " parameters is null!");
			return null;
		}
		try {
			// query file type
			inFileFormat = AudioSystem.getAudioFileFormat(inFile);
			if (inFileFormat.getType() != AudioFileFormat.Type.AIFF) {
				// inFile is not AIFF, so let's try to convert it.
				AudioInputStream inFileAIS = AudioSystem.getAudioInputStream(inFile);
				inFileAIS.reset(); // rewind
				if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFF, inFileAIS)) {
					// inFileAIS can be converted to AIFF.
					// so write the AudioInputStream to the
					// output file.
					AudioSystem.write(inFileAIS, AudioFileFormat.Type.AIFF, outFile);
					System.out.println("Successfully made AIFF file, " + outFile.getPath() + ", from " + inFileFormat.getType() + " file, " + inFile.getPath() + ".");
					inFileAIS.close();
					return outFile; // All done now
				} else
					System.out.println("Warning: AIFF conversion of " + inFile.getPath()
							+ " is not currently supported by AudioSystem.");
			} else
				System.out.println("Input file " + inFile.getPath() + " is AIFF." + " Conversion is unnecessary.");
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Error: " + inFile.getPath() + " is not a supported audio file type!");
			return null;
		} catch (IOException e) {
			System.out.println("Error: failure attempting to read " + inFile.getPath() + "!");
			return null;
		}
		return null;
	}
}
