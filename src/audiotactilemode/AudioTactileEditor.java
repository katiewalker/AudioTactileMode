package audiotactilemode;

import java.awt.EventQueue;
import processing.app.Base;
import processing.app.Mode;
import processing.app.ui.EditorException;
import processing.app.ui.EditorState;
import processing.app.ui.EditorToolbar;
import processing.mode.java.JavaEditor;
import processing.mode.java.runner.Runner;

public class AudioTactileEditor extends JavaEditor {

  private boolean readLaunchRequested;
  private final Object readLock = new Object[0];
  private Runner runtime;

  AudioTactileMode atmode;

  protected AudioTactileEditor(Base base, String path,
      EditorState state, Mode mode)
      throws EditorException {
    super(base, path, state, mode);

    atmode = (AudioTactileMode) mode;
  }

  public EditorToolbar createToolbar() {
    return new AudioTactileToolbar(this);
  }


  public void handleRead() {
    ((AudioTactileToolbar) toolbar).activateRead();
    synchronized (readLock) {
      readLaunchRequested = true;
    }
    new Thread(() -> {
      try {
        synchronized (readLock) {
          if (readLaunchRequested) {
            readLaunchRequested = false;
            atmode.handleRead(sketch);
          }
        }
      } catch (Exception e) {
        EventQueue.invokeLater(() -> statusError(e));
      }
    }).start();
  }

  public void stopRead() {
    ((AudioTactileToolbar) toolbar).deactivateRead();
  }
}
