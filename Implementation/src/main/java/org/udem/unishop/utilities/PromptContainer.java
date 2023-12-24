package org.udem.unishop.utilities;

import java.util.List;

/**
 * Represents a container for prompt components.
 */
interface PromptContainer {

  /**
   * Adds prompt component to the container.
   *
   * @param promptComponent The prompt component to be added.
   */
  void addPromptComponent(PromptComponent promptComponent);

  /**
   * Removes prompt component from the container.
   *
   * @param promptComponent The prompt component to be removed.
   */
  void removePromptComponent(PromptComponent promptComponent);

  /**
   * Gets the prompt component in the container.
   *
   * @return The prompt component.
   */
  List<PromptComponent> getPromptComponents();

  /**
   * Gets the values entered by the user for each prompt component in the container.
   *
   * @return The values entered by the user.
   */
  List<String> getValuesFromUser();
}