package com.didacusabella.yaspl.template;

import java.util.Optional;

/**
 *
 * @author didacus
 * @param <T> template model type
 */
public interface Template<T> {
  
  /**
   * Write the template with a specified model in the file path
   * @param filePath the file path
   * @param model the model
   */
  void write(String filePath, T model);
  
  /**
   * Create the new model type
   * @return the model
   */
  Optional<T> create();

}
