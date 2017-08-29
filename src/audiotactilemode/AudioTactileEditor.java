package audiotactilemode;

import audiotactilemode.audio.SpeechGenerator;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import processing.app.Base;
import processing.app.Mode;
import processing.app.syntax.JEditTextArea;
import processing.app.syntax.PdeTextAreaDefaults;
import processing.app.ui.EditorException;
import processing.app.ui.EditorState;
import processing.app.ui.EditorToolbar;
import processing.app.ui.Toolkit;
import processing.mode.java.JavaEditor;
import processing.mode.java.debug.LineID;

public class AudioTactileEditor extends JavaEditor {
  AudioTactileMode atmode;
  SpeechGenerator speechGenerator;

  protected AudioTactileEditor(Base base, String path,
      EditorState state, Mode mode)
      throws EditorException {
    super(base, path, state, mode);

    speechGenerator = new SpeechGenerator(this);
    atmode = (AudioTactileMode) mode;
  }

  protected JEditTextArea createTextArea() {
    return new AudioTactileTextArea(new PdeTextAreaDefaults(mode), this);
  }

  public EditorToolbar createToolbar() {
    return new AudioTactileToolbar(this);
  }

  public JMenu buildSketchMenu() {
    JMenu sketchMenu = super.buildSketchMenu();

    JMenuItem readLineItem = Toolkit.newJMenuItem("Read Line", 'Y');
    readLineItem.addActionListener(this::handleRead);
    sketchMenu.add(readLineItem);
    JMenuItem readFunctionItem = Toolkit.newJMenuItemShift("Read Function",
        'Y');
    readFunctionItem.addActionListener(this::handleRead);
    sketchMenu.add(readFunctionItem);
    JMenuItem readAllItem = Toolkit.newJMenuItemAlt("Read All", 'Y');
    readAllItem.addActionListener(this::handleRead);
    sketchMenu.add(readAllItem);
    sketchMenu.addSeparator();

    return sketchMenu;
  }

  // TODO: Figure out which key modifiers to use for each task -- we can't
  // use CTRL, because this also handles the keyboard shortcut Ctrl-Y
  public void handleRead(ActionEvent event) {
    if (Objects.equals(event.getActionCommand(), "stop")) {
      speechGenerator.stop();
      return;
    }
    if ((event.getModifiers() & ActionEvent.SHIFT_MASK) != 0) {
      speechGenerator.readCurrentFunction();
      return;
    }

    if ((event.getModifiers() & ActionEvent.ALT_MASK) != 0) {
      speechGenerator.readCurrentFile();
      return;
    }

    speechGenerator.readCurrentLine();
  }

  /**
   * Retrieve line of sketch where the cursor currently resides.
   * @return the current {@link LineID}
   */
  public LineID getCurrentLineID() {
    String tab = getSketch().getCurrentCode().getFileName();
    int lineNo = getTextArea().getCaretLine();
    return new LineID(tab, lineNo);
  }

  public String getCurrentLine() {
    LineID currentLineID = getCurrentLineID();
    return this.getLineText(currentLineID.lineIdx());
  }

}
