package audiotactilemode;

import java.util.Arrays;
import processing.mode.java.preproc.PdePreprocessor;

public class AudioTactilePdePreprocessor extends PdePreprocessor {

  public AudioTactilePdePreprocessor(String sketchName) {
    super(sketchName);
  }

  public AudioTactilePdePreprocessor(String sketchName, int tabSize) {
    super(sketchName, tabSize);
  }

  @Override
  public String[] getCoreImports() {
    System.out.println("adding my thing to core imports");
    String[] original = super.getCoreImports();
    String[] imports = Arrays.copyOf(original, original.length + 2);

    // According to Java precedence, the more specific audiotactilemode
    // .PApplet will supersede the general processing.core.* that imports the
    // original PApplet.
    imports[imports.length - 2] = "audiotactilemode.PApplet";
    imports[imports.length - 1] = "tactilegraphics.*";

    return imports;
  }
}
