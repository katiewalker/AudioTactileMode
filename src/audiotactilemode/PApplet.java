package audiotactilemode;

import tactilegraphics.concept.ReadPixelsFromSketch;

public class PApplet extends processing.core.PApplet {
  private ReadPixelsFromSketch readPixelsFromSketch;

  @Override
  public void handleDraw() {
    System.out.println("Using my papplet");
    // Oh boy, I hate this and I'm sorry. See https://github
    // .com/processing/processing/pull/5235 for an explanation that will not
    // at all satisfy your concerns.

    int prevFrameCount = frameCount;
    super.handleDraw();

    if (readPixelsFromSketch == null && prevFrameCount == 0 && frameCount == 1) {
      readPixelsFromSketch = new ReadPixelsFromSketch(this);
    }
  }
}
