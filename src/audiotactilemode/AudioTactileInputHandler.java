package audiotactilemode;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import processing.mode.java.JavaInputHandler;

public class AudioTactileInputHandler extends JavaInputHandler {

  public AudioTactileInputHandler(AudioTactileEditor editor) {
    super(editor);
  }

  public boolean handlePressed(KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
      ActionEvent stopAction = new ActionEvent(this, ActionEvent.ACTION_FIRST,
          "stop");
      ((AudioTactileEditor) editor).handleRead(stopAction);
      return true;
    }

    return super.handlePressed(event);
  }
}
