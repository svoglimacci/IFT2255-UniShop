package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.validation.PhoneValidator;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

public class ReportPrompt {

  public Prompt createReportPrompt( ) {

    Prompt reportPrompt = new Prompt();

    reportPrompt.addPromptComponent(new PromptItem("Description du problème", new StringValidator()));
    return reportPrompt;
  }

}