package processingvoice;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceDirectory;

public class ProcessingVoiceDirectory extends VoiceDirectory {

  /**
   * Print out information about this voice jarfile.
   */
  public static void main(String[] args) {
    System.out.println((new ProcessingVoiceDirectory()).toString());
  }

  /**
   * Gets the voices provided by this voice.
   *
   * @return an array of new Voice instances
   */
  public Voice[] getVoices() {
    Voice processing = new ProcessingLanguageVoice();

    Voice[] voices = {processing};
    return voices;
  }
}
