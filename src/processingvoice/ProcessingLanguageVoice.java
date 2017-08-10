package processingvoice;

import com.sun.speech.freetts.Age;
import com.sun.speech.freetts.Gender;
import com.sun.speech.freetts.en.us.CMUDiphoneVoice;
import com.sun.speech.freetts.en.us.CMULexicon;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProcessingLanguageVoice extends CMUDiphoneVoice {

  public ProcessingLanguageVoice() {
    super("processingLanguage", Gender.MALE,
        Age.YOUNGER_ADULT, "default 8-bit diphone voice",
        Locale.US, "general", "cmu", new CMULexicon("cmulex"),
        null);
    this.database = this.getClass().getResource("cmu_us_kal.bin");
  }

  @Override
  public void setupUtteranceProcessors() throws IOException {
    super.setupUtteranceProcessors();

    List processors = getUtteranceProcessors();
    processors.add(0, new SeparateTokens());
  }

}
