package org.udem.unishop.utilities;

import java.util.HashMap;
import java.util.Map;

public class IoCContainer {

  private final Map<Class<?>, Object> instances = new HashMap<>();

  public <T> void registerInstance(Class<T> type, T instance) {
    instances.put(type, instance);
  }

  public <T> T getInstance(Class<T> type) {
    return type.cast(instances.get(type));
  }
}