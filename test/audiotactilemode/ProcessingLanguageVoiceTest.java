package audiotactilemode;

import com.sun.speech.freetts.Tokenizer;
import org.junit.Test;
import processingvoice.ProcessingLanguageVoice;

public class ProcessingLanguageVoiceTest {
  @Test
  public void printTokens() {
    ProcessingLanguageVoice voice = new ProcessingLanguageVoice();
    Tokenizer tokenizer = voice.getTokenizer();
    tokenizer.setInputText("int main () {   }");

    while (tokenizer.hasMoreTokens()) {
      System.out.println(tokenizer.getNextToken());
    }
  }
}