package processingvoice;

import com.sun.speech.freetts.Token;
import com.sun.speech.freetts.en.TokenizerImpl;
import java.util.HashMap;
import java.util.Map;

public class CodeTokenizer extends TokenizerImpl {

  public CodeTokenizer() {
    punctuation = new HashMap<>();
    punctuation.put("-", "minus");
    punctuation.put(",", "comma");
    punctuation.put("!", "not");
    punctuation.put("\"", "double quote");
    punctuation.put("'", "single quote");
    punctuation.put(".", "dot");
    punctuation.put("/", "divided by");
    punctuation.put("<", "less than");
    punctuation.put(">", "greater than");
    punctuation.put("<=", "less than or equal to");
    punctuation.put(">=", "greater than or equal to");
    punctuation.put("(", "left bracket");
    punctuation.put(")", "right bracket");
    punctuation.put("{", "left brace");
    punctuation.put("}", "right brace");
    punctuation.put(";", "semicolon");
  }

  private Map<String, String> punctuation;

  private String translatePunctuation(String str) {
    if (punctuation.containsKey(str)) {
      return punctuation.get(str);
    }

    return str;
  }

  @Override
  public Token getNextToken() {
    Token t =  super.getNextToken();
    t.setWord(translatePunctuation(t.getWord()));
    return t;
  }

}
