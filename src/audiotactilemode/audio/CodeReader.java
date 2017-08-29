package audiotactilemode.audio;

import com.sun.speech.freetts.Voice;
import processingvoice.ProcessingLanguageVoice;

public class CodeReader {
  private Voice voice;

  CodeReader() {
    voice = new ProcessingLanguageVoice();
    voice.allocate();
  }

  public boolean read(String code) {
    return voice.speak(code);
  }

  public void stop() {
    voice.getAudioPlayer().cancel();
  }
}
