package audiotactilemode.audio;

import audiotactilemode.AudioTactileEditor;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpeechGenerator {
  private AudioTactileEditor editor;
  private Lock readingLock;
  private CodeReader reader;
  private Pattern functionSignaturePattern;

  SpeechGenerator(AudioTactileEditor editor, CodeReader reader) {
    this.editor = editor;
    this.reader = reader;

    functionSignaturePattern = Pattern.compile(
        "[a-zA-Z0-9_]+(<[a-zA-Z0-9_]*>)?(\\[])?\\s+[a-zA-Z0-9_]+\\s*\\(");
    readingLock = new ReentrantLock();
  }

  public SpeechGenerator(AudioTactileEditor editor) {
    this(editor, new CodeReader());
  }

  public void stop() {
    reader.stop();
  }

  public void readCurrentFile() {
    read(editor.getCurrentTab().getProgram());
  }

  public void readCurrentFunction() {
    int currentLineIndex = editor.getCurrentLineID().lineIdx();
    int offset = 0;


    while (!isFunctionSignature(editor.getLineText(currentLineIndex - offset))) {
      if (currentLineIndex - offset <= 0) {
        break;
      }
      offset++;
    }

    String currentFunction = "";
    Stack<Character> brackets = new Stack<>();
    int lineIndex = currentLineIndex - offset;
    currentFunction += editor.getLineText(lineIndex);
    processLineForBrackets(editor.getLineText(lineIndex), brackets);


    while (!brackets.isEmpty()) {
      lineIndex++;

      if (lineIndex >= editor.getCurrentTab().getLineCount()) {
        break;
      }

      String line = editor.getLineText(lineIndex);
      currentFunction += line;
      processLineForBrackets(line, brackets);
    }

    read(currentFunction);
  }

  private void processLineForBrackets(String line, Stack<Character> brackets) {
    for (char c : line.toCharArray()) {
      if (c == '{' || c == '(' ) {
        brackets.push(c);
      } else if (c == '}' || c == ')') {
        brackets.pop();
      }
    }
  }

  /* TODO: This doesn't really work, the pattern needs to be improved. It
  currently matches things like type x = new type().
  This isn't as simple as checking for { because that may not be on the same
  line. There is also the broader issue of how much to support Java (vs.
  Processing, which is simpler).
   */
  boolean isFunctionSignature(String line) {
    Matcher matcher = functionSignaturePattern.matcher(line);
    return matcher.find();
  }

  public void readCurrentLine() {
    read(editor.getCurrentLine());
  }

  private void read(String text) {
    new Thread(()->{
      readingLock.lock();
      try {
        reader.read(text);
      } finally {
        readingLock.unlock();
      }
    }).start();
  }
}
