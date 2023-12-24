package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.validation.PhoneValidator;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

/**
 * The ReportPrompt class creates a prompt for reporting issues or problems.
 * It allows users to provide a description of the problem using the prompt component.
 */
public class ReportPrompt {

  /**
   * Creates a prompt to report an issue or problem.
   *
   * @return The prompt configured for reporting issues.
   */
  public Prompt createReportPrompt( ) {

    Prompt reportPrompt = new Prompt();

    reportPrompt.addPromptComponent(new PromptItem("Description du problème", new StringValidator()));
    return reportPrompt;
  }

}