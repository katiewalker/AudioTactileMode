package audiotactilemode;

import java.awt.event.ActionEvent;
import java.util.List;
import processing.app.Language;
import processing.app.ui.EditorButton;
import processing.mode.java.JavaToolbar;

public class AudioTactileToolbar extends JavaToolbar {
  EditorButton readButton;

  public AudioTactileToolbar(AudioTactileEditor editor) {
    super(editor);
  }

  @Override
  public List<EditorButton> createButtons() {
    List<EditorButton> buttons = super.createButtons();

    readButton = new EditorButton(this,
        "theme/toolbar/read",
        "Read",
        Language.text("toolbar.read")) {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleRead(e.getModifiers());
      }
    };
    buttons.add(readButton);

    return buttons;
  }

  public void activateRead() {
    this.readButton.setSelected(true);
    this.repaint();
  }

  public void deactivateRead() {
    this.readButton.setSelected(false);
    this.repaint();
  }

  private void handleRead(int modifiers) {
    ((AudioTactileEditor) editor).handleRead();
  }
}
