package audiotactilemode.pdex;

import java.util.Arrays;
import java.util.List;
import processing.mode.java.pdex.ImportStatement;
import processing.mode.java.pdex.SourceUtils;
import processing.mode.java.pdex.TextTransform;

public class ProcessingTextTransform extends TextTransform {

  String code;

  public ProcessingTextTransform(CharSequence input) {
    super(input);
    code = (String) input;
  }

  public void addImportClass(String class_name) {
    ImportStatement statement = ImportStatement.singleClass(class_name);
    List<Edit> edits = SourceUtils.insertImports(Arrays.asList(statement));
    addAll(edits);
  }

  public void addStatementToSetup(String statement) {
    // This doesn't really insert to setup, but that's the intent. It's not
    // necessary to actually insert the statement in the setup, at any rate.
    Edit edit = Edit.insert(0, statement);
    add(edit);
  }
}
