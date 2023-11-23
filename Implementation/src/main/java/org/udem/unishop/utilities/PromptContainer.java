package org.udem.unishop.utilities;

import java.util.List;

interface PromptContainer {

  void addPromptComponent(PromptComponent promptComponent);

  void removePromptComponent(PromptComponent promptComponent);

  List<PromptComponent> getPromptComponents();

  List<String> getValuesFromUser();
}