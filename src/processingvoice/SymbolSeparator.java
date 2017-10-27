package processingvoice;

import com.sun.speech.freetts.FeatureSet;
import com.sun.speech.freetts.Item;
import com.sun.speech.freetts.ProcessException;
import com.sun.speech.freetts.Relation;
import com.sun.speech.freetts.Utterance;
import com.sun.speech.freetts.UtteranceProcessor;

public class SymbolSeparator implements UtteranceProcessor {

  private Item tokenItem;
  private static String camelCaseRegex = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";


  /*
  Okay, so we don't have to create a new relation, but we can insert into the
  existing one, by inserting a new item (via an existing item, which is such
  a fucking scree, but whatever).
  */

  @Override
  public void processUtterance(Utterance utterance) throws ProcessException {
    Relation tokenRelation = utterance.getRelation("Token");
    if (tokenRelation == null) {
      throw new IllegalStateException(
          "SymbolSeparator: Token relation does not exist");
    } else {

      for (tokenItem = tokenRelation.getHead(); tokenItem != null;
          tokenItem = tokenItem.getNext()) {
        separate();
      }
    }
  }

  private void separate() {
    // Get the name of the current token.
    FeatureSet tokenFeatures = tokenItem.getFeatures();
    String name = tokenFeatures.getString("name");
    // Split it into the separate tokens we desire.
    String[] newTokensContent = name.split(camelCaseRegex);

    // Set the current token to the first partial token
    tokenItem.getFeatures().setString("name", newTokensContent[0]);
    Item lastAddedItem = tokenItem;

    // For each of the remaining tokens, add a new item.
    for (int i = 1; i < newTokensContent.length; i++) {
      Item newItem = lastAddedItem.appendItem(null);
      newItem.getFeatures().setString("name", newTokensContent[i]);
      lastAddedItem = newItem;
    }
  }
}
