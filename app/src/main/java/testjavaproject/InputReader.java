package testjavaproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputReader {
  public static Path inputPath(int day) {
    return Path.of(System.getProperty("user.dir")).resolve("input" + day + ".txt");
  }

  public static List<String> readAllLines(int day) throws IOException {
    return Files.readAllLines(inputPath(day));
  }

  public static List<String> readNormalizedLines(int day) throws IOException {
    return Files.readAllLines(inputPath(day)).stream()
        .map(s -> s.replace("\r", ""))
        .collect(Collectors.toList());
  }

  public static List<String> readNonEmptyLines(int day) throws IOException {
    return Files.readAllLines(inputPath(day)).stream()
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());
  }

  public static Scanner scannerFor(int day) throws FileNotFoundException {
    return new Scanner(inputPath(day).toFile());
  }

  public static BufferedReader readerFor(int day) throws FileNotFoundException {
    return new BufferedReader(new FileReader(inputPath(day).toFile()));
  }

  public static Scanner scannerForFilename(String filename) throws FileNotFoundException {
    return new Scanner(Path.of(System.getProperty("user.dir")).resolve(filename).toFile());
  }
}
