package org.udem.unishop.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * A IoC container that manages different types of instances.
 */
public class IoCContainer {

  // map to store instances with their corresponding types.
  private final Map<Class<?>, Object> instances = new HashMap<>();

  /**
   * Registers an instance of a specific type in the IoC container.
   *
   * @param type The class representing the type of the instance.
   * @param instance The instance to be registered
   * @param <T> The type of instance.
   */
  public <T> void registerInstance(Class<T> type, T instance) {
    instances.put(type, instance);
  }

  /**
   * Gets an instance of a specific type from the IoC container.
   *
   * @param type The class representing the type of the instance.
   * @return The instance of the specified type, null if not found.
   * @param <T> The type of the instance.
   */
  public <T> T getInstance(Class<T> type) {
    return type.cast(instances.get(type));
  }
}