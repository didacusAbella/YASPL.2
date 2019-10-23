package com.didacusabella.yaspl.template;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author didacus
 */
public class SymbolTableTemplate implements Template<String> {

  @Override
  public void write(String filePath, String model) {
    filePath = filePath.replace(".yaspl", ".dsym");
    try (FileWriter fw = new FileWriter(filePath)) {
      fw.write(model);
    } catch (IOException ex) {
      System.err.println("Error during writing debug table" + ex);
    }
  }

  @Override
  public Optional<String> create() {
    return Optional.of("");
  }

}
