package tactilegraphics.concept;


import processing.core.PApplet;

/**
 * A proof of tactilegraphics.concept showing that we can read the color values of each pixel of the sketch each
 * time it is redrawn.
 *
 * @example ReadPixelsFromSketch
 */

public class ReadPixelsFromSketch {

  // applet is a reference to the parent sketch
  private PApplet applet;

  /**
   * a Constructor, usually called in the setup() method in your sketch to
   * initialize and start the Library.
   *
   * @param applet a PApplet that provides access to the sketch.
   */
  public ReadPixelsFromSketch(PApplet applet) {
    this.applet = applet;
    this.applet.registerMethod("draw", this);
  }


  public void draw() {
    System.out.println("read pixels draw");
    applet.loadPixels();
    int[] pixels = applet.pixels;

    for (int pix : pixels) {
      System.out.println(applet.red(pix) + ", " + applet.green(pix) + ", " + applet.blue(pix));
    }
  }
}

