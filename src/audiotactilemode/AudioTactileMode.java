package audiotactilemode;

import java.io.File;
import processing.app.Base;
import processing.app.RunnerListener;
import processing.app.Sketch;
import processing.app.SketchException;
import processing.app.ui.Editor;
import processing.app.ui.EditorException;
import processing.app.ui.EditorState;
import processing.mode.java.JavaMode;
import processing.mode.java.runner.Runner;

public class AudioTactileMode extends JavaMode {

  public AudioTactileMode(Base base, File folder) {
    super(base, folder);
  }

  @Override
  public Editor createEditor(Base base, String path,
      EditorState state) throws EditorException {
    return new AudioTactileEditor(base, path, state, this);
  }

  @Override
  public String getTitle() {
    return "Audio Tactile Mode";
  }

  public Runner handleLaunch(Sketch sketch, RunnerListener listener,
      final boolean present) throws SketchException {
    System.out.println("Mode.getFolder()");
    System.out.println(this.getFolder());
    AudioTactileBuild build = new AudioTactileBuild(sketch);


//    String appletClassName = build.build(false);
    String appletClassName = build.build(true);

    if (appletClassName != null) {
      final Runner runtime = new Runner(build, listener);
      new Thread(() -> {
        // these block until finished
        if (present) {
          runtime.present(null);
        } else {
          runtime.launch(null);
        }
      }).start();
      return runtime;
    }
    return null;
  }
}