package org.udem.unishop.product;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.RatingValidator;
import org.udem.unishop.validation.StringValidator;

public class ReviewPrompt {

  public Prompt createReviewPrompt() {
    Prompt reviewPrompt = new Prompt();
    reviewPrompt.addPromptComponent(new PromptItem("Commentaire", new StringValidator()));
    reviewPrompt.addPromptComponent(new PromptItem("Note", new RatingValidator()));
    return reviewPrompt;
  }

}