package audiotactilemode;

import java.io.File;
import java.util.regex.Matcher;
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

    String librarySetUp = "import tactilegraphics.concept.*;\n"
        + "ReadPixelsFromSketch reader;\n";

    int indexToAdd = findStartOfSetup(code);

    String modifiedCode = new StringBuilder(librarySetUp).append(code).insert
        (indexToAdd + librarySetUp.length(),
        "\nreader = new ReadPixelsFromSketch(this);\n").toString();
    sketch.getCode(0).setProgram(modifiedCode);

    return super.handleLaunch(sketch, listener, present);
  }

  private int findStartOfSetup(String code) {
    Matcher matcher = setupPattern.matcher(code);
    if (matcher.find()) {
      return matcher.end();
    }

    return -1;
  }
}