package processingvoice;

import com.sun.speech.freetts.Age;
import com.sun.speech.freetts.Gender;
import com.sun.speech.freetts.Tokenizer;
import com.sun.speech.freetts.en.us.CMUDiphoneVoice;
import com.sun.speech.freetts.en.us.CMULexicon;
import com.sun.speech.freetts.en.us.USEnglish;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import processing.app.Preferences;

public class ProcessingLanguageVoice extends CMUDiphoneVoice {

  public ProcessingLanguageVoice() {
    super("processingLanguage", Gender.MALE,
        Age.YOUNGER_ADULT, "default 8-bit diphone voice",
        Locale.US, "general", "cmu", new CMULexicon("cmulex"),
        null);
    this.database = this.getClass().getResource("cmu_us_kal.bin");
  }

  @Override
  public boolean speak(String text) {
    return super.speak(tagWithScope(text));
  }

  private String tagWithScope(String text) {
    String[] lines = text.split(System.getProperty("line.separator"));
    String tagged = "";
    int last_indent_level = 0;
    for (String line : lines) {
      // Count the number of preceding spaces.
      int num_spaces = 0;
      for (char c : line.toCharArray()) {
        if (c == ' ') {
          num_spaces++;
        } else {
          break;
        }
      }
      // Divide by Preferences.editor.tab.size
      int indent_level = num_spaces / Preferences.getInteger("editor.tabs"
          + ".size");

      // If different to last indentation level, tag and set.
      if (indent_level != last_indent_level) {
        tagged += "indent " + indent_level;
        last_indent_level = indent_level;
      }

      tagged += line;
    }

    return tagged;
  }

  @Override
  public void setupUtteranceProcessors() throws IOException {
    super.setupUtteranceProcessors();

    List processors = getUtteranceProcessors();
    processors.add(0, new SeparateTokens());
  }

  @Override
  public Tokenizer getTokenizer() {
    Tokenizer tokenizer = new CodeTokenizer();
    tokenizer.setWhitespaceSymbols(USEnglish.WHITESPACE_SYMBOLS);
    tokenizer.setSingleCharSymbols("{}[]();.+-/!*,");
    return tokenizer;
  }

}
