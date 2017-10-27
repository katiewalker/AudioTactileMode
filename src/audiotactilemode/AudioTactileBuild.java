package audiotactilemode;

import java.io.File;
import processing.app.Sketch;
import processing.app.SketchException;
import processing.mode.java.JavaBuild;

public class AudioTactileBuild extends JavaBuild {

  public AudioTactileBuild(Sketch sketch) {
    super(sketch);
  }

  @Override
  public String preprocess(File srcFolder, boolean sizeWarning) throws
      SketchException {
    String result =  preprocess(srcFolder, null, new AudioTactilePdePreprocessor
        (sketch
        .getName()), sizeWarning);
    System.out.println(result);
    return result;
  }

  @Override
  public String getClassPath() {
    String classpath = super.getClassPath();

    classpath += File.pathSeparator + mode.getFolder() +
        "/mode/AudioTactilePApplet.jar";
    classpath += File.pathSeparator + mode.findLibraryByName
        ("TactileGraphicsLibrary").getPath() +
        "/library/TactileGraphicsLibrary.jar";

    return classpath;
  }

}
