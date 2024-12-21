import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.initGame() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can the static variable SoundEffect.volume to SoundEffect.Volume.MUTE
 *    to mute the sound.
 *
 * For Eclipse, place the audio file under "src", which will be copied into "bin".
 */
public enum SoundEffect {
   EAT_FOOD("/sounds/whistle.wav"),
   EXPLODE("/sounds/pop.wav"),
   DIE("/sounds/bells.wav");

   /** Nested enumeration for specifying volume */
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.MEDIUM;

   /** Each sound effect has its own clip, loaded with its own sound file. */
   private Clip clip;
   private boolean isValid = false;

   /** Private Constructor to construct each element of the enum with its own sound file. */
   private SoundEffect(String soundFileName) {
      try {
         // Use URL (instead of File) to read from disk and JAR.
         URL url = this.getClass().getClassLoader().getResource(soundFileName);
         if (url == null) {
            System.err.println("Warning: Sound file not found: " + soundFileName);
            return;
        }
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
         isValid = true;
      } catch (UnsupportedAudioFileException e) {
         System.err.println("Error: Unsupported audio file format: " + soundFileName);
     } catch (IOException e) {
         System.err.println("Error: Could not read sound file: " + soundFileName);
     } catch (LineUnavailableException e) {
         System.err.println("Error: Audio line unavailable");
     } catch (Exception e) {
         System.err.println("Error: Could not load sound: " + e.getMessage());
     }
   }

   /** Play or Re-play the sound effect from the beginning, by rewinding. */
   public void play() {
      if (!isValid || volume == Volume.MUTE) {
         return;
      }
      
      try {
         if (clip.isRunning()) {
            clip.stop();
         }
         clip.setFramePosition(0);
         clip.start();
      } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
      }
   }

   /** Optional static method to pre-load all the sound files. */
   static void initGame() {
      values(); // calls the constructor for all the elements
   }
}