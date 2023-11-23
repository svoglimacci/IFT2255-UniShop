package org.udem.unishop.utilities;

import java.util.ArrayList;
import java.util.List;

public class Prompt implements PromptContainer {

  private final List<PromptComponent> promptComponents;

  public Prompt() {
    this.promptComponents = new ArrayList<>();
  }

  @Override
  public void addPromptComponent(PromptComponent promptComponent) {
    promptComponents.add(promptComponent);
  }

  @Override
  public void removePromptComponent(PromptComponent promptComponent) {
    promptComponents.remove(promptComponent);
  }

  @Override
  public List<PromptComponent> getPromptComponents() {
    return promptComponents;
  }

  @Override
  public List<String> getValuesFromUser() {
    List<String> values = new ArrayList<>();
    for (PromptComponent promptComponent : promptComponents) {
      values.add(promptComponent.getValue());
    }
    return values;
  }
}