package org.udem.unishop.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a prompt container containing various prompt components.
 */
public class Prompt implements PromptContainer {

  private final List<PromptComponent> promptComponents;

  /**
   * Constructs new Prompt with an empty list of prompt components.
   */
  public Prompt() {
    this.promptComponents = new ArrayList<>();
  }

  /**
   * Adds a component to the prompt.
   *
   * @param promptComponent The prompt component to be added.
   */
  @Override
  public void addPromptComponent(PromptComponent promptComponent) {
    promptComponents.add(promptComponent);
  }

  /**
   * Removes a component from the prompt.
   *
   * @param promptComponent The prompt component to be removed.
   */
  @Override
  public void removePromptComponent(PromptComponent promptComponent) {
    promptComponents.remove(promptComponent);
  }

  /**
   * Gets the list of prompt components in the prompt.
   *
   * @return the list of prompt components.
   */
  @Override
  public List<PromptComponent> getPromptComponents() {
    return promptComponents;
  }

  /**
   * Collects and returns the values entered by the user for each prompt component.
   *
   * @return The list of user-entered values.
   */
  @Override
  public List<String> getValuesFromUser() {
    List<String> values = new ArrayList<>();
    for (PromptComponent promptComponent : promptComponents) {
      values.add(promptComponent.getValue());
    }
    return values;
  }
}