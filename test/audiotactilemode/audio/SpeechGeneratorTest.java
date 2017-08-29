package audiotactilemode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class SpeechGeneratorTest {
  SpeechGenerator speechGenerator;
  CodeReader codeReader;
  AudioTactileEditor editor;

  @Before
  public void setup() {
    editor = mock(AudioTactileEditor.class);
    codeReader = mock(CodeReader.class);
    speechGenerator = new SpeechGenerator(editor, codeReader);
  }
//
//  @Test
//  public void readCurrentFile() throws Exception {
//    SketchCode currentTab = mock(SketchCode.class);
//    when(currentTab.getProgram()).thenReturn("HELP");
//
//    when(editor.getCurrentTab()).thenReturn(currentTab);
//
//    speechGenerator.readCurrentFile();
//
//    verify(codeReader).read("HELP");
//  }
//
////  @Test
////  public void readCurrentFunction() throws Exception {
////
////  }
//
//  @Test
//  public void readCurrentLine() throws Exception {
//    when(editor.getCurrentLine()).thenReturn("Hello");
//
//    speechGenerator.readCurrentLine();
//
//    verify(codeReader).read("Hello");
//  }

  @Test
  public void setupFunctionSignatureIsIdentified() {
    String setup = "void setup() {";
    assertEquals(true, speechGenerator.isFunctionSignature(setup));
  }



}