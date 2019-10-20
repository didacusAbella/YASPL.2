package com.didacusabella.yaspl.template;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 * @author didacus
 */
public class CTemplate implements Template {
  
  private final Map<String, String> renderMap;

  public CTemplate( Map<String, String> renderMap) {
    this.renderMap = renderMap;
  }
  
  @Override
  public void render(String filePath) {
    try {
      String fileContent = new String(Files.readAllBytes(Paths.get(filePath)), 
              StandardCharsets.UTF_8);
      this.renderMap.forEach((key, value) -> fileContent.replace(key, value));
    } catch (IOException ex) {
      System.err.println("Input Output Exception");
    }
  }
  
}
