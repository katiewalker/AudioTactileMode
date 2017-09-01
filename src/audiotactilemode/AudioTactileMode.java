package audiotactilemode;

import audiotactilemode.pdex.ProcessingTextTransform;
import java.io.File;
import java.util.regex.Pattern;
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

  private Pattern setupPattern;

  public AudioTactileMode(Base base, File folder) {
    super(base, folder);

    setupPattern = Pattern.compile("size\\([0-9]+,\\s*[0-9]*\\);");
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

  @Override
  public Runner handleLaunch(Sketch sketch, RunnerListener listener,
                             final boolean present) throws SketchException {
    String code = sketch.getMainProgram();
    sketch.getCode(0).setProgram(modifyCode(code));

    System.out.println(sketch.getCode(0).getProgram());

    return super.handleLaunch(sketch, listener, present);
  }

  private String modifyCode(String code) {
    ProcessingTextTransform transform = new ProcessingTextTransform(code);
    transform.addImportClass("tactilegraphics.concept.ReadPixelsFromSketch");
    transform.addStatementToSetup("ReadPixelsFromSketch writer = new "
        + "ReadPixelsFromSketch(this);\n");
    return transform.apply();
  }
}