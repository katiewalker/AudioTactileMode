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
    // We insert the statement at the beginning because it doesn't matter
    // where it is inserted. Import statements (what are usually at the
    // beginning of files) can be anywhere. This also works when the setup
    // function is defined elsewhere. This *may* be an issue for Java-style
    // files, but these files are outside the scope of the project, for now.
    // Similarly, this may break if Processing changes how it processes the
    // files (this would be true of any implementation, so the risk is
    // acceptable).
    Edit edit = Edit.insert(0, statement);
    add(edit);
  }
}
