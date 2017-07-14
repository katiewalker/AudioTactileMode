package audiotactilemode;

import processing.app.syntax.TextAreaDefaults;
import processing.mode.java.pdex.JavaTextArea;

public class AudioTactileTextArea extends JavaTextArea {

  public AudioTactileTextArea(TextAreaDefaults defaults,
      AudioTactileEditor editor) {
    super(defaults, editor);
    inputHandler = new AudioTactileInputHandler(editor);
  }
}
