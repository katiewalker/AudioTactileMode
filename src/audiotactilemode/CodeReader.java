package audiotactilemode;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class CodeReader {
  private Voice voice;
  private VoiceManager manager;

  CodeReader() {
    manager = VoiceManager.getInstance();
    voice = manager.getVoice("kevin");
    voice.allocate();
  }

  public boolean read(String code) {
    return voice.speak(code);
  }

  public void stop() {
    voice.getAudioPlayer().cancel();
  }
}
