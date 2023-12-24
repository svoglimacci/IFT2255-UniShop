package org.udem.unishop.product;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.RatingValidator;
import org.udem.unishop.validation.StringValidator;

/**
 * The ReviewPrompt class provides a method to create a prompt for collecting user reviews.
 */
public class ReviewPrompt {

  /**
   * Creates a prompt for collecting user reviews, including a comment and a rating.
   *
   * @return The prompt for collecting user reviews.
   */
  public Prompt createReviewPrompt() {
    Prompt reviewPrompt = new Prompt();
    reviewPrompt.addPromptComponent(new PromptItem("Commentaire", new StringValidator()));
    reviewPrompt.addPromptComponent(new PromptItem("Note", new RatingValidator()));
    return reviewPrompt;
  }

}